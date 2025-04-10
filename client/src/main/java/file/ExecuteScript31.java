
package file;

import commands.*;
import console.CommandDecoder;
import console.CommandFactory;
import console.CommandFactoryScript;
import objectsCreation.CreateDragonFromScr;
import seClasses.Dragon;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class ExecuteScript31 {
    private final ArrayList<Command> commandQueue = new ArrayList<>();
    private final File scriptFile;
    private final ArrayDeque<File> fileMemory = new ArrayDeque<>();

    public ExecuteScript31(File scriptFile){
        this.scriptFile = scriptFile;
    }

    public ArrayList<Command> getCommandQueue(){
        return commandQueue;
    }

    private ExecuteScript31 readScript(File scriptFile){
        List<String> lines;
        try {
            lines = Files.readAllLines(scriptFile.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Невозможно выполнить файл.");
            return this;
        }

        fileMemory.add(scriptFile);
        for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++){
            String[] line = lines.get(lineIndex).split(" ");
            Command command = null;
            CommandsList cmd = CommandDecoder.getCommandType(line[0]);
            if (cmd.equals(CommandsList.EXECUTE_SCRIPT)){
                if (fileMemory.contains(new File(line[1]))){
                    System.out.println("Рекурсия недоступна, строка пропущена.");
                    continue;
                }
                this.readScript(new File(line[1]));
            } else if (Set.of(CommandsList.ADD, CommandsList.ADD_IF_MIN, CommandsList.REMOVE_LOWER).contains(cmd)) {
                if (lines.size() >= lineIndex + 8 && Objects.equals(lines.get(lineIndex + 8), "n")) {
                    String[] DragonFields = lines.subList(lineIndex + 1, lineIndex + 9).toArray(new String[0]);
                    Dragon dragon = CreateDragonFromScr.createDragon(DragonFields);
                    if (cmd.equals(CommandsList.ADD)) {
                        command = new AddCommand(dragon);
                    } else if (cmd.equals(CommandsList.REMOVE_LOWER)) {
                        command = new RemoveLowerCommand(dragon);
                    } else {
                        command = new AddIfMinCommand(dragon);
                    }
                    lineIndex += 8;
                }else if (lines.size() >= lineIndex + 16 && Objects.equals(lines.get(lineIndex + 8), "y")) {
                    String[] DragonFields = lines.subList(lineIndex + 1, lineIndex + 17).toArray(new String[0]);
                    Dragon dragon = CreateDragonFromScr.createDragon(DragonFields);
                    if (cmd.equals(CommandsList.ADD)) {
                        command = new AddCommand(dragon);
                    } else if (cmd.equals(CommandsList.REMOVE_LOWER)) {
                        command = new RemoveLowerCommand(dragon);
                    } else {
                        command = new AddIfMinCommand(dragon);
                    }
                    lineIndex += 16;
                    }
            } else if (cmd.equals(CommandsList.UPDATE)) {
                if (lines.size() >= lineIndex + 8 && Objects.equals(lines.get(lineIndex + 8), "n")) {
                    String[] DragonFields = lines.subList(lineIndex + 1, lineIndex + 9).toArray(new String[0]);
                    Dragon dragon = CreateDragonFromScr.createDragon(DragonFields);
                    command = new UpdateIdCommand(Long.parseLong(line[1]), dragon);
                    lineIndex += 8;
                }else if (lines.size() >= lineIndex + 16 && Objects.equals(lines.get(lineIndex + 8), "y")) {
                    String[] DragonFields = lines.subList(lineIndex + 1, lineIndex + 17).toArray(new String[0]);
                    Dragon dragon = CreateDragonFromScr.createDragon(DragonFields);
                    command = new UpdateIdCommand(Long.parseLong(line[1]), dragon);
                    lineIndex += 16;
                }

            } else {
                command = CommandFactoryScript.createCommand(cmd, line);
            }
            if (command != null){
                commandQueue.add(command);
            } else {
                System.out.println("Команда с ошибкой "+ line[0] +" пропущена.");
                for (int i = lineIndex + 1; i <= lines.size(); i++){
                    if (CommandDecoder.getCommandType(lines.get(i)) != CommandsList.DEFAULT){
                        lineIndex = i - 1;
                        break;
                    }
                }
            }
        }
        fileMemory.pop();
        return this;
    }
    public ExecuteScript31 readScript(){
        return this.readScript(scriptFile);
    }
}


