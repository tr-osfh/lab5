package file;

import commands.*;
import console.CommandDecoder;
import console.CommandFactory;
import objectsCreation.CreateDragonFromScr;
import seClasses.Dragon;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class ExecuteScript {
    private final ArrayList<Command> commandQueue = new ArrayList<>();
    private final File scriptFile;
    private final ArrayDeque<File> fileMemory = new ArrayDeque<>();

    public ExecuteScript(File scriptFile){
        this.scriptFile = scriptFile;
    }

    public ArrayList<Command> getCommandQueue(){
        return commandQueue;
    }

    private ExecuteScript readScript(File scriptFile){
        List<String> lines;
        try {
            lines = Files.readAllLines(scriptFile.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Невозможно выполнить файл.");
            return this;
        }
        fileMemory.add(scriptFile);
        for (int index = 0; index < lines.size(); index++){
            String line = lines.get(index);
            CommandsList commandType = CommandDecoder.getCommandType(line.split(" ")[0]);
            String[] args = Arrays.copyOfRange(line.split(" "), 1, line.split(" ").length);
            Command cmd = null;
            if (commandType == CommandsList.EXECUTE_SCRIPT){
                if (fileMemory.contains(new File(args[0]))){
                    System.out.println("Рекурсия невозможна.");
                    continue;
                }
            }
            if (Set.of(CommandsList.UPDATE, CommandsList.REMOVE_LOWER).contains(commandType)) {
                if (args.length < 1 || index + 16 >= lines.size()) {
                    System.out.println("не верные аргументы для команды" + commandType + ".");
                    continue;
                }

                Dragon dragon = null;

                if (Objects.equals(args[index + 8], "y")) {
                    String[] DragonFields = lines.subList(index + 1, index + 17).toArray(new String[0]);
                    args[0] = line.split(" ")[1];
                    System.arraycopy(DragonFields, 0, args, 1, DragonFields.length);
                    index += 16;
                    dragon = CreateDragonFromScr.createDragon(DragonFields);
                } else if (Objects.equals(args[index + 8], "n")) {
                    String[] DragonFields = lines.subList(index + 1, index + 9).toArray(new String[0]);
                    args[0] = line.split(" ")[1];
                    System.arraycopy(DragonFields, 0, args, 1, DragonFields.length);
                    index += 8;
                    dragon = CreateDragonFromScr.createDragon(DragonFields);
                }

                if (dragon != null) {
                    if (commandType == CommandsList.UPDATE) {
                        cmd = new UpdateIdCommand(Long.parseLong(args[0]), dragon);
                    } else {
                        cmd = new RemoveLowerCommand(dragon);
                    }
                }
            }
            else if (Set.of(CommandsList.ADD, CommandsList.ADD_IF_MIN).contains(commandType)){
                Dragon dragon = null;

                if (Objects.equals(args[index + 8], "y")) {
                    String[] DragonFields = lines.subList(index + 1, index + 17).toArray(new String[0]);
                    args[0] = line.split(" ")[1];
                    System.arraycopy(DragonFields, 0, args, 1, DragonFields.length);
                    index += 16;
                    dragon = CreateDragonFromScr.createDragon(DragonFields);
                } else if (Objects.equals(args[index + 8], "n")) {
                    String[] DragonFields = lines.subList(index + 1, index + 9).toArray(new String[0]);
                    args[0] = line.split(" ")[1];
                    System.arraycopy(DragonFields, 0, args, 1, DragonFields.length);
                    index += 8;
                    dragon = CreateDragonFromScr.createDragon(DragonFields);
                }

                if (dragon != null){
                    switch (commandType){
                        case ADD -> cmd = new AddCommand(dragon);
                        case ADD_IF_MIN -> cmd = new AddIfMinCommand(dragon);
                    }
                } else {
                    System.out.println("не верные аргументы для команды" + commandType + ".");
                    continue;
                }
            } else {
                cmd = CommandFactory.createCommand(commandType, line.split(" "));
            }
            if (cmd != null) commandQueue.add(cmd);
        }
        fileMemory.pop();
        return this;
    }
    public ExecuteScript readScript(){
        return this.readScript(scriptFile);
    }
}


