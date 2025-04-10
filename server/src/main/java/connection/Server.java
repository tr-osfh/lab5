//package connection;
//
//import collection.CollectionManager;
//import collection.ServerLogger;
//import file.FileManager;
//import file.Parser;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.nio.channels.SocketChannel;
//
//public class Server {
//
//    private final int port;;
//    private CollectionManager cm;
//    private FileManager fm;
//    public Server(int port, FileManager fileManager, CollectionManager cm) {
//        this.fm = fileManager;
//        this.port = port;
//    }
//    public void run(){
//        try (ServerSocket serverSocket = new ServerSocket(port)) {
//            ServerLogger.getLogger().info("Сервер запущен на порте " + port);
//
//            new Thread(() -> {
//                try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
//                    while (true) {
//                        if (consoleReader.ready()) {
//                            String line = consoleReader.readLine();
//                            if (line.equals("exit")) {
//                                fm.saveCSV(CollectionManager.getDragons());
//                                System.exit(0);
//                            }
//                        }
//                    }
//                } catch (IOException e) {
//                    ServerLogger.getLogger().warning("Ошибка чтения с консоли: " + e.getMessage());
//                }
//            }).start();
//            while (true) {
//                try {
//                    Socket clientSocket = serverSocket.accept();
//                    new ClientHandler(clientSocket).start();
//                } catch (IOException e) {
//                    ServerLogger.getLogger().warning("Ошибка при подключении клиента: " + e.getMessage());
//                }
//            }
//        } catch (IOException e){
//            ServerLogger.getLogger().severe("Ошибка запуска сервера: " + e.getMessage());
//        }
//    }
//}

package connection;
import collection.CollectionManager;
import collection.ServerLogger;
import file.FileManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;

public class Server {
    private final int port;
    private final FileManager fm;
    private ServerSocketChannel serverChannel;
    private Selector selector;

    public Server(int port, FileManager fileManager) {
        this.port = port;
        this.fm = fileManager;
    }

    public void run() {
        try {
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false); // Неблокирующий режим
            serverChannel.bind(new InetSocketAddress(port));

            selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT); // Слушаем подключения
            ServerLogger.getLogger().info("Сервер запущен на порте " + port);

            // Поток для обработки команды "exit"
            startConsoleListener();

            while (true) {
                selector.select(); // Ожидание событий
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
        clientChannel.configureBlocking(false); // Неблокирующий режим для клиента
        ServerLogger.getLogger().info("Подключен клиент: " + clientChannel.getRemoteAddress());

        // Создаем обработчик клиента и регистрируем его в селекторе
        new ClientHandler(clientChannel).start();
    }

    private void startConsoleListener() {
        new Thread(() -> {
            try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
                while (true) {
                    if (consoleReader.ready() && consoleReader.readLine().equals("exit")) {
                        fm.saveCSV(CollectionManager.getDragons());
                        System.exit(0);
                    } else if (consoleReader.ready() && consoleReader.readLine().equals("save")) {
                        fm.saveCSV(CollectionManager.getDragons());
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
