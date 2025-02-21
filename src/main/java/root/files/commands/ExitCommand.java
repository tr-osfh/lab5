package root.files.commands;

import root.files.collection.CollectionManager;

public class ExitCommand implements Command{

    private CollectionManager manager;

    public ExitCommand(CollectionManager manager){
        this.manager = manager;
    }
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            manager.exit();
        }
    }
    @Override
    public String getDescription() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}
