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
            try {
                Long dragonId = Long.parseLong(args[1]);
                manager.updateById(dragonId);
            } catch (NumberFormatException e) {
                System.out.println("ID должен быть числом.");
            }
        } else {
            throw new IllegalArgumentException("Неверное количество аргументов. Используйте: remove_by_id <id>");
        }
    }

    @Override
    public String getDescription(){
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}
