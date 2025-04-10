//package connection;//package connection;
//
//import collection.ServerLogger;
//import commands.Command;
//
//import java.io.EOFException;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import collection.CollectionManager;
//import commands.CommandSerializer;
//
//import java.net.Socket;
//
//
//public class ClientHandler extends Thread {
//    private final Socket clientSocket;
//    private final CollectionManager collectionManager;
//
//    public ClientHandler(Socket socket, CollectionManager manager) {
//        this.clientSocket = socket;
//        this.collectionManager = manager;
//    }
//
//    @Override
//    public void run() {
//        try (
//                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
//                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())
//        ) {
//            while (true) {
//                // Получение команды
//                Command command = (Command) in.readObject(); // Только один readObject()
//                System.out.println("[Сервер] Получена команда: " + command.toString());
//                Response response = command.execute();
//                System.out.println(response.getResponse());
//                out.writeObject(response);
//                out.flush();
//                clientSocket.close();
//            }
//        } catch (EOFException e) {
//            ServerLogger.getLogger().info("Клиент отключился: " + clientSocket.getRemoteSocketAddress());
//        } catch (IOException e) {
//            ServerLogger.getLogger().warning("Ошибка обработки команды: " + e.getMessage());
//        } catch (ClassNotFoundException e){
//            ServerLogger.getLogger().warning("Класс не найден: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            try {
//                clientSocket.close();
//            } catch (IOException e) {
//                ServerLogger.getLogger().warning("Ошибка закрытия сокета: " + e.getMessage());
//            }
//        }
//    }
//}

package connection;

import collection.ServerLogger;
import commands.Command;
import collection.CollectionManager;
import commands.CommandSerializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

public class ClientHandler extends Thread {
    private final SocketChannel clientChannel;
    private final Selector selector;
    private ByteBuffer buffer;

    public ClientHandler(SocketChannel channel) throws IOException {
        this.clientChannel = channel;
        this.clientChannel.configureBlocking(false); // Неблокирующий режим
        this.selector = Selector.open();
        this.clientChannel.register(selector, SelectionKey.OP_READ);
        this.buffer = ByteBuffer.allocate(8124*8124);
    }

    @Override
    public void run() {
        try {
            while (true) {
                selector.select(); // Ожидание событий
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();

                    if (key.isReadable()) {
                        handleRead(key);
                    } else if (key.isWritable()) {
                        handleWrite(key);
                    }
                }
            }
        } catch (IOException e) {
            ServerLogger.getLogger().warning("Ошибка обработки клиента: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        buffer.clear();
        int bytesRead = channel.read(buffer);

        if (bytesRead == -1) {
            ServerLogger.getLogger().info("Клиент отключился: " + channel.getRemoteAddress());
            channel.close();
            return;
        }

        if (bytesRead > 0) {
            buffer.flip();
            byte[] data = new byte[buffer.limit()];
            buffer.get(data);

            try {
                Command command = CommandSerializer.deserialize(data);
                ServerLogger.getLogger().info("[Сервер] Получена команда: " + command.toString());
                Response response = command.execute();

                // Подготовка ответа
                byte[] responseData = CommandSerializer.serialize(response);
                buffer.clear();
                buffer.put(responseData);
                buffer.flip();
                key.interestOps(SelectionKey.OP_WRITE); // Переключаем на запись
            } catch (ClassNotFoundException e) {
                ServerLogger.getLogger().warning("Ошибка десериализации команды: " + e.getMessage());
            }
        }
    }

    private void handleWrite(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        channel.write(buffer);
        if (!buffer.hasRemaining()) {
            key.interestOps(SelectionKey.OP_READ); // Возвращаемся к чтению
        }
    }

    private void closeConnection() {
        try {
            if (clientChannel != null) {
                ServerLogger.getLogger().info("Закрытие соединения с клиентом: " + clientChannel.getRemoteAddress());
                clientChannel.close();
            }
            if (selector != null) {
                selector.close();
            }
        } catch (IOException e) {
            ServerLogger.getLogger().warning("Ошибка закрытия соединения: " + e.getMessage());
        }
    }
}