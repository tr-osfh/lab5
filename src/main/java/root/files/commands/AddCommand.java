package root.files.commands;

import root.files.collection.CollectionManagerRecever;
import root.files.seClasses.Dragon;

public class AddCommand implements Command{

    private CollectionManagerRecever manager;

    public AddCommand(CollectionManagerRecever manager){
        this.manager = manager;
    }

    @Override
    public void execute(String[] args){
        if (args.length == 1) {
            try {
                Dragon dragon = manager.getDragonManager().getDragon();
                boolean flag
            } catch (IllegalArgumentException e){
                System.out.println("Проверьте введенные данные.");
            }
        }
    }
}
