package connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.util.Iterator;

public class Request implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    private SocketChannel channel;
    private Selector selector;
    private ByteBuffer writeBuffer;
    private ByteBuffer readBuffer;
    private final InetSocketAddress serverAddress;
    private final int socketTimeout;

    public Request(InetAddress address, int port, int timeout) throws IOException {
        this.serverAddress = new InetSocketAddress(address, port);
        this.socketTimeout = timeout;
        initialize();
    }


    public void initialize() throws IOException {
        channel = SocketChannel.open();
        channel.configureBlocking(false); // Неблокирующий режим
        channel.connect(serverAddress);

        selector = Selector.open();
        channel.register(selector, SelectionKey.OP_CONNECT);

        long startTime = System.currentTimeMillis();
        while (!channel.finishConnect()) {
            if (System.currentTimeMillis() - startTime > socketTimeout) {
                throw new IOException("Connection timeout");
            }
            selector.select(100);
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();
                if (key.isConnectable()) {
                    finishConnection(key);
                }
            }
        }
    }

    private void finishConnection(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        if (channel.isConnectionPending()) {
            channel.finishConnect();
        }
        channel.register(selector, SelectionKey.OP_WRITE);
    }

    // В методе send()
    public void send(byte[] data) throws IOException {
        writeBuffer = ByteBuffer.wrap(data);
        while (writeBuffer.hasRemaining()) {
            channel.write(writeBuffer);
        }
        System.out.println("Отправлено: " + new String(data));
    }

    // В методе receive()
    public byte[] receive() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int bytesRead;

        try {
            while ((bytesRead = channel.read(buffer)) != -1) {
                if (bytesRead == 0) {
                    continue;
                }
                buffer.flip();
                byteStream.write(buffer.array(), 0, bytesRead);
                buffer.clear();
            }
        } catch (IOException e) {
            throw new IOException("Ошибка чтения данных: " + e.getMessage());
        }

        return byteStream.toByteArray();
    }
    public void close() {
        try {
            if (channel != null) channel.close();
            if (selector != null) selector.close();
        } catch (IOException ignored) {}
    }

    public void reconnect() throws IOException {
        close();
        initialize();
    }
}