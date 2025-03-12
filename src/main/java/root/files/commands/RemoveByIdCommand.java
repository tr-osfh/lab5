package root.files.commands;

import root.files.collection.CollectionManager;
import root.files.seClasses.Dragon;

import java.util.Iterator;
import java.util.PriorityQueue;

public class RemoveByIdCommand implements Command {

    private final CollectionManager manager;

    public RemoveByIdCommand(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            manager.removeById(Long.valueOf(args[1]));
        } else {
            throw new IllegalArgumentException("Неверное количество аргументов. спользуйте: remove_by_id");
        }
    }

    @Override
    public String getDescription() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}
