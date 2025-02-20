package root.files.commands;

import root.files.collection.CollectionManagerRecever;

public class ClearCommand implements Command{

    private CollectionManagerRecever manager;

    public ClearCommand(CollectionManagerRecever manager) {
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
