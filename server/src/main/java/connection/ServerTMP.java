package connection;

import collection.CollectionManager;
import collection.ServerLogger;
import console.ConsoleManager;
import file.FileManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ServerTMP {
    private final ConsoleManager consoleManager;
    private final int port;
    private final FileManager fm;
    private ServerSocketChannel serverChannel;
    private Selector selector;

    public ServerTMP(int port, FileManager fileManager, ConsoleManager consoleManager) {
        this.port = port;
        this.fm = fileManager;
        this.consoleManager = consoleManager;
    }

    public void run() {
        try {
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.bind(new InetSocketAddress(port));

            selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            ServerLogger.getLogger().info("Сервер запущен на порте " + port);

            startConsoleListener();

            while (true) {
                selector.select();
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();

                    if (key.isAcceptable()) {
                        handleAccept(key);
                    }
                }
            }
        } catch (IOException e) {
            ServerLogger.getLogger().severe("Ошибка запуска сервера: " + e.getMessage());
        } finally {
            closeResources();
        }
    }

    private void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = serverChannel.accept();
        clientChannel.configureBlocking(false);
        ServerLogger.getLogger().info("Подключен клиент: " + clientChannel.getRemoteAddress());

        new ClientHandler(clientChannel).start();
    }

    private void startConsoleListener() {
        new Thread(() -> {
            try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
                while (true) {
                    String line = consoleReader.readLine();
                    if (line.equals("exit")) {
                        fm.saveCSV(CollectionManager.getDragons());
                        System.exit(0);
                    } else if (line.equals("save")) {
                        fm.saveCSV(CollectionManager.getDragons());
                    } else {
                        consoleManager.write("Доступны только команды save, exit");
                    }
                }
            } catch (IOException e) {
                ServerLogger.getLogger().warning("Ошибка чтения с консоли: " + e.getMessage());
            }
        }).start();
    }

    private void closeResources() {
        try {
            if (serverChannel != null) serverChannel.close();
            if (selector != null) selector.close();
        } catch (IOException e) {
            ServerLogger.getLogger().warning("Ошибка закрытия ресурсов: " + e.getMessage());
        }
    }
}
