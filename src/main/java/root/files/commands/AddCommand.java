package root.files.commands;

import root.files.collection.CollectionManager;
import root.files.seClasses.Dragon;

public class AddCommand implements Command{

    private CollectionManager manager;

    public AddCommand(CollectionManager manager){
        this.manager = manager;
    }

    @Override
    public void execute(String[] args){
        if (args.length == 1) {
            try {
                Dragon dragon = manager.getDragonManager().setDragon();
                manager.add(dragon);
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
