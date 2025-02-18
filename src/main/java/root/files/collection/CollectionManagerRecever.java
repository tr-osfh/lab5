package root.files.collection;

import root.files.console.DragonManager;
import root.files.seClasses.Dragon;

import java.util.PriorityQueue;

public class CollectionManagerRecever {

    PriorityQueue<Dragon> dragons = new PriorityQueue<>();

    public PriorityQueue<Dragon> getDragons() {
        return dragons;
    }

    private DragonManager dragonManager = new DragonManager();

    public DragonManager getDragonManager(){
        return dragonManager;
    }

    public void add(Dragon dragon){
        dragons.add(dragon);
    }
}
