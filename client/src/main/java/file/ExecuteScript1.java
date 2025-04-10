//package file;
//
//import commands.*;
//import console.CommandDecoder;
//import console.CommandFactory;
//import objectsCreation.CreateDragonFromScr;
//import seClasses.Dragon;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.util.*;
//
//public class ExecuteScript1 {
//    private final ArrayList<Command> commandQueue = new ArrayList<>();
//    private final File scriptFile;
//    private final ArrayDeque<File> fileMemory = new ArrayDeque<>();
//
//    public ExecuteScript1(File scriptFile){
//        this.scriptFile = scriptFile;
//    }
//
//    public ArrayList<Command> getCommandQueue(){
//        return commandQueue;
//    }
//
//    private ExecuteScript1 readScript(File scriptFile){
//        List<String> lines;
//        try {
//            lines = Files.readAllLines(scriptFile.toPath(), StandardCharsets.UTF_8);
//        } catch (IOException e) {
//            System.out.println("Невозможно выполнить файл.");
//            return this;
//        }
//
//        fileMemory.add(scriptFile);
//        for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++){
//            String[] line = lines.get(lineIndex).split(" ");
//            if (line[0] == "execute_script" && fileMemory.contains(line[1])){
//                System.out.println("рекурсия невозможна, строка пропущена.");
//            }
//            CommandsList cmd = CommandsList.valueOf(line[0]);
//            if (Set.of(CommandsList.ADD, CommandsList.ADD_IF_MIN).contains(cmd)){
//                if (lines.size() >= lineIndex + 8 && Objects.equals(lines.get(lineIndex + 8), "n")){
//                    String[] DragonFields = lines.subList(lineIndex + 1, lineIndex + 17).toArray(new String[0]);
//                    for (String element : DragonFields) {
//                        System.out.println(element);
//                    }
//
//                }
//            } else {
//                CommandFactory.createCommand(cmd, line);
//            }
//        }
//        fileMemory.pop();
//        return this;
//    }
//    public ExecuteScript1 readScript(){
//        return this.readScript(scriptFile);
//    }
//}
//
//
//