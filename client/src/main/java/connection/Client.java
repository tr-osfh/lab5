package connection;

import commands.Command;
import commands.CommandsList;
import commands.ExecuteScriptCommand;
import console.*;
import file.ExecuteScript31;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.util.Iterator;

public class Client {
    private final String serverAddress;
    private final int port;
    private SocketChannel socketChannel;
    private Selector selector;
    private ConsoleReader consoleReader;
    private volatile boolean isCommandReady = false;
    private Command pendingCommand;
    private boolean isConnected = false;
    private boolean isWaitingForResponse = false;
    private Thread consoleInputThread;

    public Client(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
        this.consoleReader = new ConsoleReader(new ReadController());
    }

    public void connect() throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(serverAddress, port));

        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
    }

    public void run() {
        try {
            connect();
            while (true) {
                selector.select();
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();
                    if (key.isConnectable()) {
                        handleConnect(key);
                        // Запускаем ввод команд только после подключения
                        if (isConnected) {
                            startConsoleInputThread();
                        }
                    } else if (key.isReadable()) {
                        handleRead(key);
                    } else if (key.isWritable()) {
                        handleWrite(key);
                    }
                }
                if (isCommandReady) {
                    sendPendingCommand();
                }
            }
        } catch (IOException e) {
            handleReconnect();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void startConsoleInputThread() {
        if (consoleInputThread != null && consoleInputThread.isAlive()) {
            consoleInputThread.interrupt();
        }
        consoleInputThread = new Thread(() -> {
            while (true) {
                Command command = readCommand();
                if (command == null){
                    continue;
                }
                synchronized (this) {
                    pendingCommand = command;
                    isCommandReady = true;
                    isWaitingForResponse = true;
                }
                selector.wakeup();
            }
        });
        consoleInputThread.start();
    }

    private void sendPendingCommand() {
        try {
            SelectionKey key = socketChannel.keyFor(selector);
            if (key != null) {
                key.interestOps(SelectionKey.OP_WRITE);
            }
        } catch (Exception e) {
            System.out.println("Ошибка");
        } finally {
            synchronized (this) {
                isCommandReady = false;
            }
        }
    }

    private void handleConnect(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        if (channel.finishConnect()) {
            channel.register(selector, SelectionKey.OP_READ);
            System.out.println("Подключено к серверу.");
            System.out.println("Введите команду: ");
            isConnected = true;
        }
    }

    private void handleRead(SelectionKey key) throws IOException, ClassNotFoundException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(8124*8124);
        int bytesRead = channel.read(buffer);
        if (bytesRead > 0) {
            buffer.flip();
            byte[] data = new byte[buffer.limit()];
            buffer.get(data);
            Response response = CommandSerializer.deserialize(data);
            System.out.println(response.getResponse());
            buffer.clear();
            isWaitingForResponse = false;
            commandRq();
        }
    }

    private void handleWrite(SelectionKey key) throws IOException {
        if (pendingCommand != null) {
            ByteBuffer buffer = ByteBuffer.wrap(CommandSerializer.serialize(pendingCommand));
            SocketChannel channel = (SocketChannel) key.channel();
            channel.write(buffer);
            key.interestOps(SelectionKey.OP_READ);
            pendingCommand = null;
        }
    }

    private void handleReconnect() {
        isConnected = false;
        while (!isConnected) {
            System.out.println("Сервер недоступен. Повторная попытка через 5 секунд...");
            try {
                Thread.sleep(5000);

                if (socketChannel != null && socketChannel.isOpen()) socketChannel.close();
                if (selector != null && selector.isOpen()) selector.close();

                selector = Selector.open();
                socketChannel = SocketChannel.open();
                socketChannel.configureBlocking(false);
                socketChannel.connect(new InetSocketAddress(serverAddress, port));
                socketChannel.register(selector, SelectionKey.OP_CONNECT);

                if (socketChannel.finishConnect()) {
                    isConnected = true;
                    isWaitingForResponse = false;
                    System.out.println("Подключение восстановлено.\nВведите команду:");
                    socketChannel.register(selector, SelectionKey.OP_READ);

                    if (consoleInputThread != null && consoleInputThread.isAlive()) {
                        consoleInputThread.interrupt();
                    }
                    startConsoleInputThread();
                    selector.wakeup();
                }

            } catch (InterruptedException e) {
                System.err.println("Поток прерван");
                Thread.currentThread().interrupt();
                return;
            } catch (IOException e) {
                System.err.println("Ошибка подключения: " + e.getMessage());
            }
        }
    }


    private void commandRq(){
        if (!isWaitingForResponse) {
            System.out.println("Введите команду:");
        }
    }

    private Command readCommand(){
        if (isConnected) {
                String[] commandAndArgs = consoleReader.readCommand();

                CommandsList type = CommandDecoder.getCommandType(commandAndArgs[0]);
                if (type == CommandsList.EXIT) {
                    System.exit(0);
                } else if (type == CommandsList.EXECUTE_SCRIPT) {
                    ExecuteScript31 ex = new ExecuteScript31(new File(commandAndArgs[1]));
                    ex.readScript();
                    return new ExecuteScriptCommand(ex.getCommandQueue());
                }
            Command command = CommandFactory.createCommand(type, commandAndArgs);
                return command;
        }
        return null;

    }

}
