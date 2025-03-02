package root.files.commands;

import root.files.collection.CollectionManager;
import root.files.console.ConsoleManager;

public class infoCommand implements Command{

    private CollectionManager manager;

    public infoCommand(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            manager.info();
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getDescription() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}