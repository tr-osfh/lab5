package connection;

import commands.Command;
import commands.CommandsList;
import commands.ExecuteScriptCommand;
import console.*;
import file.ExecuteScript;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Client {
    private final String serverAddress;
    private final int port;
    private SocketChannel socketChannel;
    private Selector selector;
    private ConsoleReader cr;
    private Command pendingCommand;
    private boolean isConnected = false;
    private boolean isWaitingForResponse = false;
    private boolean running = true;

    public Client(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
        this.cr = new ConsoleReader(new ReadController());
    }

    public void connect() throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(serverAddress, port));

        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
    }

    public void run() {
        while (true) {
            try {
                connect();
                while (running) {
                    int readyChannels = selector.select(100);
                    if (readyChannels > 0) {
                        Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                        while (keys.hasNext()) {
                            SelectionKey key = keys.next();
                            keys.remove();
                            if (key.isConnectable()) {
                                successConnect(key);
                            } else if (key.isReadable()) {
                                read(key);
                            } else if (key.isWritable()) {
                                write(key);
                            }
                        }
                    }
                    processConsoleInput();
                }
            } catch (IOException e) {
                try {
                    System.out.print("Ошибка подключения. Повторная попытка");
                    Thread.sleep(1000);
                    System.out.print('.');
                    Thread.sleep(1000);
                    System.out.print('.');
                    Thread.sleep(1000);
                    System.out.println('.');
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    break;
                }
            } catch (ClassNotFoundException e) {
                System.out.println("Ошибка десериализации");
                break;
            }
        }
    }

    private void processConsoleInput() {
        if (isConnected && !isWaitingForResponse) {
            try {
                if (System.in.available() > 0) {
                    Command cmd = readCommand();
                    if (cmd != null) {
                        pendingCommand = cmd;
                        SelectionKey key = socketChannel.keyFor(selector);
                        if (key != null) key.interestOps(SelectionKey.OP_WRITE);
                    }
                }
            } catch (IOException e) {
                cr.printLine("Ошибка ввода: " + e.getMessage());
            }
        }
    }



    private void successConnect(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        if (channel.finishConnect()) {
            channel.register(selector, SelectionKey.OP_READ);
            cr.printLine("Подключено к серверу. \n");
            cr.printLine("Введите команду: \n");
            isConnected = true;
        }
    }

    private void read(SelectionKey key) throws IOException, ClassNotFoundException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(8124 * 8124);
        int bytesRead = channel.read(buffer);

        if (bytesRead > 0) {
            buffer.flip();
            byte[] data = new byte[buffer.limit()];
            buffer.get(data);
            Response response = CommandSerializer.deserialize(data);
            System.out.println(response.getResponse());
            isWaitingForResponse = false;
                cr.printLine("Введите команду: \n");
        } else if (bytesRead == -1) {
            channel.close();
            isConnected = false;
        }
        buffer.clear();
    }

    private void write(SelectionKey key) throws IOException {
        if (pendingCommand != null) {
            ByteBuffer buffer = ByteBuffer.wrap(CommandSerializer.serialize(pendingCommand));
            SocketChannel channel = (SocketChannel) key.channel();
            channel.write(buffer);
            key.interestOps(SelectionKey.OP_READ);
            pendingCommand = null;
            isWaitingForResponse = true;
        }
    }

    private Command readCommand() {
        String[] input = cr.readCommand();

        if (input == null || input.length == 0) return null;

        CommandsList type = CommandDecoder.getCommandType(input[0]);
        if (type == CommandsList.EXIT) {
            running = false;
            return null;
        } else if (type == CommandsList.EXECUTE_SCRIPT && input.length > 1) {
            ExecuteScript script = new ExecuteScript(new File(input[1]));
            script.readScript();
            return new ExecuteScriptCommand(script.getCommandQueue());
        }
        return CommandFactory.createCommand(type, input);
    }
}
