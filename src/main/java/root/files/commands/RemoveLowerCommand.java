package root.files.commands;

import root.files.collection.CollectionManager;
import root.files.seClasses.Dragon;

public class RemoveLowerCommand implements Command{

    private CollectionManager manager;

    public RemoveLowerCommand(CollectionManager manager){
        this.manager = manager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            Dragon dragon = manager.getDragonManager().setDragon();
            manager.removeLower(dragon);

        } else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public String getDescription() {
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }
}
