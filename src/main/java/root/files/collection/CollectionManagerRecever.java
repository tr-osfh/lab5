package root.files.collection;

import root.files.console.DragonManager;
import root.files.file.FileWriterManager;
import root.files.seClasses.Dragon;

import java.util.PriorityQueue;

public class CollectionManagerRecever {

    PriorityQueue<Dragon> dragons = new PriorityQueue<>();
    private String fileName;

    public PriorityQueue<Dragon> getDragons() {
        return dragons;
    }

    public void setDragons(PriorityQueue<Dragon> dragons){
        this.dragons = dragons;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public DragonManager getDragonManager(){
        return dragonManager;
    }

    private DragonManager dragonManager = new DragonManager();


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
}
