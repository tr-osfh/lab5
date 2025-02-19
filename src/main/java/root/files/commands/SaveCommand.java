package root.files.commands;

import root.files.collection.CollectionManagerRecever;

public class SaveCommand implements Command{
    private CollectionManagerRecever manager;

    public SaveCommand(CollectionManagerRecever manager) {
        this.manager = manager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            manager.save();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
