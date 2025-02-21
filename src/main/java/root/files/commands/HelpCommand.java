package root.files.commands;

import root.files.collection.CollectionManager;

public class HelpCommand implements Command{

    private CollectionManager manager;

    public HelpCommand(CollectionManager manager) {
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
