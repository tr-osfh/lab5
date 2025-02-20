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
    private CommandManager commandManager;
    private DragonManager dragonManager = new DragonManager();
    private java.time.LocalDateTime creationDate = java.time.LocalDateTime.now();
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
        FileWriterManager fm = new FileWriterManager(fileName);
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
}
