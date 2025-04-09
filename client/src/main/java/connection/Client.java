package connection;

import commands.Command;
import commands.CommandsList;
import console.*;
import file.ExecuteScript;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.util.ArrayList;

//public class Client {
//    private final Request request;
//
//    private final ReadController rc = new ReadController();
//    private final ConsoleReader consoleReader = new ConsoleReader(rc);
//
//    public Client(InetAddress address, int port) {
//        try {
//            request = new Request(address, port, 4000);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void run() {
//        for (; ; ) {
//            String[] commandAndArgs = consoleReader.readCommand();
//            if (commandAndArgs.length < 1) {
//                consoleReader.printLine("Введите команду: \n");
//                continue;
//            }
//            if (!commandAndArgs[0].equals("execute_script")) {
//                CommandsList type = CommandDecoder.getCommandType(commandAndArgs[0]);
//                Command command = CommandFactory.createCommand(type, commandAndArgs);
//                if (command == null) continue;
//                try {
//                    byte[] data = CommandSerializer.serialize(command);
//                    request.send(data);
//                } catch (IOException e) {
//                    System.out.println("Ошибка отправки запроса." + e.getMessage());
//                }
//                try {
//                    String response = CommandSerializer.deserialize(request.receive());
//                    if (!response.isEmpty()) System.out.println(response);
//                } catch (IOException e) {
//                    System.out.println("Ошибка получения ответа." + e.getMessage());
//                } catch (ClassNotFoundException e) {
//                    throw new RuntimeException(e);
//                }
//            } else if (commandAndArgs[0].equals("execute_script") && commandAndArgs.length == 2) {
//                try {
//                    ExecuteScript es = new ExecuteScript(new File(commandAndArgs[0])).readScript();
//                    ArrayList<Command> commands = es.getCommandQueue();
//                    commands.forEach(command -> {
//                        try {
//                            request.send(CommandSerializer.serialize(command));
//                        } catch (IOException e) {
//                            System.out.println("Ошибка отправки запроса." + e.getMessage());
//                        }
//                        try {
//                            String response = CommandSerializer.deserialize(request.receive());
//                            if (!response.isEmpty()) System.out.println(response);
//                        } catch (IOException e) {
//                            System.out.println("Ошибка получения ответа." + e.getMessage());
//                        } catch (ClassNotFoundException e) {
//                            throw new RuntimeException(e);
//                        }
//                    });
//                } catch (Exception e) {
//                    System.out.println(e.getMessage());
//                }
//            } else {
//                System.out.println("Ошибка ввода команды. Введите команду: ");
//            }
//
//        }
//    }
//
//}

import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private final String serverAddress;
    private final int port;
    private final ConsoleReader consoleReader;

    public Client(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
        this.consoleReader = new ConsoleReader(new ReadController());
    }

    public void run() {
        while (true) {
            try {
                // Установка соединения
                try (Socket socket = new Socket(serverAddress, port);
                     ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                    // Чтение команды от пользователя
                    String[] commandAndArgs = consoleReader.readCommand();
                    if (commandAndArgs.length == 0) continue;

                    // Создание команды
                    CommandsList type = CommandDecoder.getCommandType(commandAndArgs[0]);
                    Command command = CommandFactory.createCommand(type, commandAndArgs);
                    if (command == null) continue;

                    // Отправка команды
                    out.writeObject(command);
                    out.flush();

                    // Получение ответа
                    Response response = (Response) in.readObject();
                    System.out.println("Ответ сервера: " + response.getResponse());

                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }

            } catch (Exception e) {
                System.out.println("Критическая ошибка: " + e.getMessage());
                break;
            }
        }
    }
}
