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
                Dragon dragon = manager.getDragonManager().setDragon();
                boolean inCollection = false;
                for (Dragon dragonTmp : manager.getDragons()) {
                    if (dragonTmp.equals(dragon)) {
                        inCollection = true;
                    }
                }
                if (inCollection) {
                    System.out.println("Этот дракон уже есть в коллекции.");
                } else {
                    manager.add(dragon);
                    System.out.println("Дракон успешно добавлен.");
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
        return "add {element} : добавить новый элемент в коллекцию";
    }
}
