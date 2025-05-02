package connection;

import collection.CollectionManager;
import collection.ServerLogger;
import commands.Command;
import commands.CommandSerializer;
import console.ConsoleManager;
import file.FileManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server {
    private final ConsoleManager consoleManager;
    private final int port;
    private final FileManager fm;
    private ServerSocketChannel serverChannel;
    private Selector selector;
    private volatile boolean running = true;


    public Server(int port, FileManager fileManager, ConsoleManager consoleManager) {
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

            while (running) {
                checkConsoleInput();
                int readyChannels = selector.select(100);

                if (readyChannels > 0) {
                    Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                    while (keys.hasNext()) {
                        SelectionKey key = keys.next();
                        keys.remove();

                        try {
                            if (key.isAcceptable()) {
                                acceptConnection(key);
                            } else if (key.isReadable()) {
                                read(key);
                            } else if (key.isWritable()) {
                                write(key);
                            }
                        } catch (IOException e) {
                            ServerLogger.getLogger().info("Клиент отключился");
                            key.channel().close();
                        }
                    }
                }
            }
        } catch (IOException e) {
            ServerLogger.getLogger().severe("Ошибка в работе сервера");
        } finally {
            closeResources();
        }
    }

    private void checkConsoleInput() {
        try {
            if (System.in.available() > 0) {
                String line = consoleManager.read();
                if (line.equals("exit")) {
                    fm.saveCSV(CollectionManager.getDragons());
                    running = false;
                } else if (line.equals("save")) {
                    fm.saveCSV(CollectionManager.getDragons());
                } else {
                    consoleManager.write("Доступны только команды save, exit");
                }
            }
        } catch (IOException e) {
            ServerLogger.getLogger().warning("Ошибка ввода");
        }
    }


    private void acceptConnection(SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = serverChannel.accept();
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ);
        ServerLogger.getLogger().info("Подключен клиент: " + clientChannel.getRemoteAddress());
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer readBuffer = ByteBuffer.allocate(8124 * 8124);
        int bytesRead = channel.read(readBuffer);

        if (bytesRead == -1) {
            try {
                ServerLogger.getLogger().info("Клиент отключен: " + channel.getRemoteAddress());
                channel.close();
            } catch (IOException e) {
                ServerLogger.getLogger().warning("Ошибка закрытия канала");
            }
            return;
        }

        if (bytesRead > 0) {
            readBuffer.flip();
            byte[] data = new byte[readBuffer.limit()];
            readBuffer.get(data);

            try {
                Command command = CommandSerializer.deserialize(data);
                Response response = command.execute();
                ServerLogger.getLogger().info("От клиента " + channel.getRemoteAddress() + " получена команда: " + command.getCommandName());
                ByteBuffer responseBuffer = ByteBuffer.wrap(CommandSerializer.serialize(response));
                key.attach(responseBuffer);
                key.interestOps(SelectionKey.OP_WRITE);
            } catch (ClassNotFoundException e) {
                ServerLogger.getLogger().warning("Ошибка десериализации команды");
            }
        }
    }

    private void write(SelectionKey key) throws IOException {
        try {
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            if (buffer != null) {
                channel.write(buffer);
                if (!buffer.hasRemaining()) {
                    key.interestOps(SelectionKey.OP_READ);
                    key.attach(null);
                }
            }
        } catch (IOException e) {
            ServerLogger.getLogger().warning("Ошибка записи");
            key.channel().close();
        }
    }



    private void closeResources() {
        try {
            if (serverChannel != null) serverChannel.close();
            if (selector != null) selector.close();
        } catch (IOException e) {
            ServerLogger.getLogger().warning("Ошибка закрытия ресурсов");
        }
    }
}