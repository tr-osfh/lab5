package connection;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Request implements Serializable {
    @Serial
    private static final long serialID = 0L;

    private SocketChannel channel;
    private final InetSocketAddress serverAdress;
    private final int socketTimeout;
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024 * 1024);
    private ByteBuffer writeBuffer;

    public Request(InetAddress address, int port, int timeout) throws IOException {
        this.serverAdress = new InetSocketAddress(address, port);
        this.socketTimeout = timeout;
        initializeChannel();
    }

    private void initializeChannel() throws IOException {
        channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(serverAdress);

        long startTime = System.currentTimeMillis();
        while (!channel.finishConnect()){
            if (System.currentTimeMillis() - startTime > socketTimeout){
                throw new IOException("Connection timeout");
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored){}
        }
    }

    public int getPort() throws IOException {
        try {
            return ((InetSocketAddress) channel.getLocalAddress()).getPort();
        } catch (IOException e){
            throw new IOException("Ошибка подключения.");
        }
    }

    public void setBufferSize(int size) throws SocketException{
        readBuffer = ByteBuffer.allocate(size);
        try {
            channel.setOption(java.net.StandardSocketOptions.SO_SNDBUF, size);
            channel.setOption(java.net.StandardSocketOptions.SO_RCVBUF, size);
        } catch (IOException e) {
            throw new SocketException();
        }
    }

    public void send(byte[] data) throws IOException{
        writeBuffer = ByteBuffer.wrap(data);
        while (writeBuffer.hasRemaining()){
            int bytesWritten = channel.write(writeBuffer);
            if (bytesWritten == 0){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {}
            }
        }
    }

    public String receive() throws IOException{
        readBuffer.clear();
        StringBuilder response = new StringBuilder();

        while (true){
            int bytesRead = channel.read(readBuffer);
            if (bytesRead == -1){
                throw new IOException();
            }
            if (bytesRead == 0){
                if (response.length() > 0) break;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {}
            }
            readBuffer.flip();
            response.append(StandardCharsets.UTF_8.decode(readBuffer));
            readBuffer.clear();

            if (!isMoreDataPending()) break;
        }
        return response.toString();
    }

    private boolean isMoreDataPending() throws IOException{
        return channel.socket().getInputStream().available() > 0;
    }

    public void close(){
        try {
            if (channel != null && channel.isOpen()) {
                channel.close();
            }
        } catch (IOException ignored) {
        }
    }
}
