package root.files.commands;

import root.files.collection.CollectionManager;
import root.files.seClasses.Dragon;

public class AddIfMinCommand implements Command{
    private CollectionManager manager;

    public AddIfMinCommand(CollectionManager manager) {
        this.manager = manager;
    }



    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            try {
                Dragon dragon = manager.getDragonManager().setDragon();
                manager.addIfMin(dragon);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getDescription() {
        return "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
    }
}
