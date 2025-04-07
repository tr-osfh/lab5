package connection;

import commands.Command;
import commands.CommandsList;
import console.*;

import java.io.IOException;
import java.net.InetAddress;

public class Client {
    private final Request request;

    private final ReadController rc = new ReadController();
    private final ConsoleReader consoleReader = new ConsoleReader(rc);

    public Client(InetAddress address, int port){
        try {
            request = new Request(address, port, 4000);
            request.setBufferSize(8192 * 8192);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        for (;;){
            String[] commandAndArgs = consoleReader.readCommand();
            if (commandAndArgs.length < 1){
                consoleReader.printLine("Введите команду");
                continue;
            }
            if (!commandAndArgs[0].equals("execute_script")){
                CommandsList type = CommandDecoder.getCommandType(commandAndArgs[0]);
                Command command = CommandFactory.createCommand(type, commandAndArgs);
                if (command != null) continue;
                try {
                    request.send(CommandSerializer.serialize(command));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
