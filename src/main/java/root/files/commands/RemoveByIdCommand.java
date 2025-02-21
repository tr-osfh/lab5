package root.files.commands;

import root.files.collection.CollectionManagerRecever;
import root.files.seClasses.Dragon;

import java.util.Iterator;
import java.util.PriorityQueue;

public class RemoveByIdCommand implements Command {

    private final CollectionManagerRecever manager;

    public RemoveByIdCommand(CollectionManagerRecever manager) {
        this.manager = manager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            try {
                Long dragonId = Long.valueOf(args[1]);
                boolean inCollection = false;

                PriorityQueue<Dragon> dragons = manager.getDragons();
                Iterator<Dragon> iterator = dragons.iterator();

                while (iterator.hasNext()) {
                    Dragon dragonToRemove = iterator.next();
                    if (dragonToRemove.getId() == dragonId) {
                        iterator.remove();
                        inCollection = true;
                        break;
                    }
                }

                if (inCollection) {
                    System.out.println("Дракон с ID " + dragonId + " успешно удален.");
                } else {
                    System.out.println("Дракона с ID " + dragonId + " нет в коллекции.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: ID должен быть числом.");
            }
        } else {
            throw new IllegalArgumentException("Неверное количество аргументов. Используйте: remove_by_id <id>");
        }
    }

    @Override
    public String getDescription() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}
