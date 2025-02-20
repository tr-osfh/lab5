package root.files.commands;

import root.files.collection.CollectionManagerRecever;

public class HelpCommand implements Command{

    private CollectionManagerRecever manager;

    public HelpCommand(CollectionManagerRecever manager) {
        this.manager = manager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            CommandManager cm = manager.getCommandManager();
            manager.help(cm.getCommands());
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getDescription() {
        return "help : вывести справку по доступным командам";
    }
}
