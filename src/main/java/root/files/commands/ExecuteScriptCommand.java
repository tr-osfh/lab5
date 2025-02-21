package root.files.commands;

import root.files.collection.CollectionManager;

public class ExecuteScriptCommand implements Command{

    private CollectionManager manager;

    public ExecuteScriptCommand(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 2){
            manager.executeScript(args[1]);
            System.out.println();
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getDescription() {
        return "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же";
    }
}
