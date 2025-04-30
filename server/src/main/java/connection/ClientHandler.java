package connection;

import collection.ServerLogger;
import commands.Command;
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
        this.clientChannel.configureBlocking(false);
        this.selector = Selector.open();
        this.clientChannel.register(selector, SelectionKey.OP_READ);
        this.buffer = ByteBuffer.allocate(8124*8124);
    }

    @Override
    public void run() {
        try {
            while (true) {
                selector.select();
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

                byte[] responseData = CommandSerializer.serialize(response);
                buffer.clear();
                buffer.put(responseData);
                buffer.flip();
                key.interestOps(SelectionKey.OP_WRITE);
            } catch (ClassNotFoundException e) {
                ServerLogger.getLogger().warning("Ошибка десериализации команды: " + e.getMessage());
            }
        }
    }

    private void handleWrite(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        channel.write(buffer);
        if (!buffer.hasRemaining()) {
            key.interestOps(SelectionKey.OP_READ);
        }
    }
}