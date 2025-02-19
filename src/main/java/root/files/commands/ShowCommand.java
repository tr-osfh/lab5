package root.files.commands;

import root.files.collection.CollectionManagerRecever;
import root.files.seClasses.Dragon;

public class ShowCommand implements Command {

    private CollectionManagerRecever manager;

    public ShowCommand(CollectionManagerRecever manager) {
        this.manager = manager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            manager.show();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
