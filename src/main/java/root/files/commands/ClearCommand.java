package root.files.commands;

import root.files.collection.CollectionManager;

public class ClearCommand implements Command{

    private CollectionManager manager;

    public ClearCommand(CollectionManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            manager.clear();
            System.out.println("Коллекция успешно удалена.");
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getDescription(){
        return "clear : очистить коллекцию";
    }
}
