package root.files.commands;

import root.files.collection.CollectionManager;

public class HeadCommand implements Command{

    private CollectionManager manager;

    public HeadCommand(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            manager.head();
        }
    }

    @Override
    public String getDescription() {
        return "head : вывести первый элемент коллекции";
    }
}