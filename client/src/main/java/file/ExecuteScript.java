//package file;
//
//import commands.Command;
//import commands.CommandsList;
//import console.CommandDecoder;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.util.*;
//
//public class ExecuteScript {
//    private final ArrayList<Command> commandQueue = new ArrayList<>();
//    private final File scriptFile;
//    private final ArrayDeque<File> fileMemory = new ArrayDeque<>();
//
//    public ExecuteScript(File scriptFile){
//        this.scriptFile = scriptFile;
//    }
//
//    public ArrayList<Command> getCommandQueue(){
//        return commandQueue;
//    }
//
//    private ExecuteScript readScript(File scriptFile){
//        List<String> lines;
//        try {
//            lines = Files.readAllLines(scriptFile.toPath(), StandardCharsets.UTF_8);
//        } catch (IOException e) {
//            System.out.println("Невозможно выполнить файл.");
//            return this;
//        }
//        fileMemory.add(scriptFile);
//        for (int index = 0; index < lines.size(); index++){
//            String line = lines.get(index);
//            CommandsList commandType = CommandDecoder.getCommandType(line.split(" ")[0]);
//            String[] args = Arrays.copyOfRange(line.split(" "), 1, line.split(" ").length);
//            Command cmd = null;
//            if (commandType == CommandsList.EXECUTE_SCRIPT){
//                if (fileMemory.contains(new File(args[0]))){
//                    System.out.println("Рекурсия невозможна.");
//                    continue;
//                }
//            }
//            if (Set.of(CommandsList.UPDATE, CommandsList.ADD_IF_MIN, CommandsList.REMOVE_LOWER).contains(commandType)){
//                if (args.length < 1 || index + )
//            }
//
//        }
//    }
//}