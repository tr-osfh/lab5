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
            try {
                Dragon dragon = manager.getDragonManager().setDragon();
                boolean inCollection = false;
                for (Dragon dragonTmp : manager.getDragons()) {
                    if (dragonTmp.equals(dragon)) {
                        inCollection = true;
                    } else {
                        manager.removeLower(dragon);
                    }
                }
                if (inCollection) {
                    System.out.println("Этот дракон уже есть в коллекции.");
                } else {
                    System.out.println("Элементы меньшие чем заданый удалены.");
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public String getDescription() {
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }
}
