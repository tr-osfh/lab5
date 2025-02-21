package root.files.collection;

import root.files.commands.Command;
import root.files.commands.CommandManager;
import root.files.console.DragonManager;
import root.files.file.FileWriterManager;
import root.files.seClasses.Dragon;

import java.util.HashMap;
import java.util.PriorityQueue;

public class CollectionManagerRecever {

    PriorityQueue<Dragon> dragons = new PriorityQueue<>();
    private String fileName;
    FileWriterManager fm;
    private CommandManager commandManager;
    private DragonManager dragonManager = new DragonManager();
    private java.time.LocalDateTime creationDate = java.time.LocalDateTime.now();

    public void setFileManager(FileWriterManager fm){
        this.fm = fm;
    };

    public PriorityQueue<Dragon> getDragons() {
        return dragons;
    }

    public void setDragons(PriorityQueue<Dragon> dragons){
        this.dragons = dragons;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public DragonManager getDragonManager(){
        return dragonManager;
    }


    public void show(){
        if (!dragons.isEmpty()){
            for (Dragon dragon : dragons){
                System.out.println(dragon);
            }
        } else {
            System.out.println("Коллекция пуста.");
        }
    }

    public void save(){
        try {
            fm.saveCSV(dragons);
            System.out.println("Коллекция сохранена в файл.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Запись в файл не возможна");
        }
    }

    public void add(Dragon dragon){
        dragons.add(dragon);
    }

    public void help(HashMap<String, Command> commands){
        for (Command command : commands.values()){
            System.out.println(command.getDescription());
        }
    }

    public void info(){
        System.out.println("Тип хранимых данных в коллекции: Dragon");
        System.out.println("Дата и время инициализации: " + creationDate);
        System.out.println("Колличество элементов в коллеции: " + dragons.size());
    }

    public void updateById(Dragon dragon){
        dragons.add(dragon);
    }

    public void removeById(Dragon dragon){
        dragons.remove(dragon);
    }

    public void exit(){
        System.exit(0);
    }

    public void clear(){
        dragons.clear();
    }

    public void executeScript(String file){
        commandManager.executeScriptCommand(fm.loadScript(file));
    }

    public void head(){
        if (!dragons.isEmpty()){
            System.out.println(dragons.peek());
        } else {
            System.out.println("Коллекция пуста.");
        }
    }

    public void addIfMin(Dragon dragon){
        dragons.add(dragon);
    }

    public void removeLower(Dragon dragon){
        for (Dragon dragonToRemove : dragons){
            if (dragonToRemove.getCoordinates().getX() < dragon.getCoordinates().getX()){
                dragons.remove(dragonToRemove);
            }
        }
    }

    public void sumOfAge(){
        Long sAge = 0L;
        if (!dragons.isEmpty()){
            for (Dragon dragon : dragons){
                sAge += dragon.getAge();
            }
            if (sAge == 0L){
                System.out.println("Нет данных о возрасте драконов.");
            } else {
                System.out.println("Суммарный возраст всех драконов: " + sAge);
            }
        } else {
            System.out.println("В коллекции нет драконов.");
        }
    }

    public void filterContainsName(String name){
        boolean flag = false;
        for (Dragon dragon : dragons){
            if (dragon.getName().contains(name)){
                System.out.println(dragon);
                flag = true;
            }
        }
        if (!flag){
            System.out.println("Поиск не дал результатов.");
        }
    }

    public void filterStartsWithName(String name){
        boolean flag = false;
        int len = name.length();
        for (Dragon dragon : dragons){
            if (dragon.getName().substring(0, len).equals(name)){
                System.out.println(dragon);
                flag = true;
            }
        }
        if (!flag){
            System.out.println("Поиск не дал результатов.");
        }
    }
}
