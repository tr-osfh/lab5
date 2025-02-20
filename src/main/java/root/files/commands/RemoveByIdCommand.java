package root.files.commands;

import root.files.collection.CollectionManagerRecever;
import root.files.seClasses.Dragon;

public class RemoveByIdCommand implements Command{

    private CollectionManagerRecever manager;

    public RemoveByIdCommand(CollectionManagerRecever manager){
        this.manager = manager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            for (;;){
                try {
                    Long dragonId = manager.getDragonManager().getDragonId();
                    boolean inCollection = false;
                    for (Dragon dragonToRemove : manager.getDragons()) {
                        if (dragonToRemove.getId() == dragonId) {
                            manager.removeById(dragonToRemove);
                            inCollection = true;
                        }
                    }
                    if (inCollection) {
                        System.out.println("Дракон с ID" + dragonId + " успешно удален.");
                        break;
                    } else {
                        System.out.println("Дракона с ID " + dragonId + " нет в коллекции.");
                    }
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getDescription() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}
