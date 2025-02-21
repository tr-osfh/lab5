package root.files.commands;

import root.files.collection.CollectionManagerRecever;

public class HeadCommand implements Command{

    private CollectionManagerRecever manager;

    public HeadCommand(CollectionManagerRecever manager) {
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