package root.files.commands;

import root.files.collection.CollectionManager;
import root.files.seClasses.Dragon;

public class UpdateIdCommand implements Command{

    private CollectionManager manager;

    public UpdateIdCommand(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            for (;;){
                try {
                    Long dragonId = Long.parseLong(args[1]);
                    boolean inCollection = false;
                    for (Dragon dragonToRemove : manager.getDragons()) {
                        if (dragonToRemove.getId() == dragonId) {
                            Dragon dragon = manager.getDragonManager().setDragon();
                            manager.removeById(dragonToRemove);
                            dragon.setId(dragonId);
                            manager.updateById(dragon);
                            inCollection = true;
                        }
                    }
                    if (inCollection) {
                        System.out.println("Данные дракона успешно обновлены.");
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
    public String getDescription(){
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}
