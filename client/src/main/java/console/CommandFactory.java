package console;

import commands.*;
import objectsCreation.CreateDragon;
import seClasses.Dragon;

public class CommandFactory {

    private ReadController rc = new ReadController();

    public Command createCommand(CommandsList command, String[] args){
        return switch (command){
            case HELP -> new HelpCommand();
            case INFO -> new InfoCommand();
            case SHOW -> new ShowCommand();
            case ADD -> {
                Dragon dragon;
                CreateDragon dragonCreator = new CreateDragon(rc);
                dragon = dragonCreator.create();
                yield new AddCommand(dragon);}
            case UPDATE -> {
                if (args.length != 1){
                    rc.printLine("Не верное колличество аргументов.");
                    yield null;
                } try {
                    Long id = Long.parseLong(args[0]);
                    Dragon dragon;
                    CreateDragon dragonCreator = new CreateDragon(rc);
                    dragon = dragonCreator.create();
                    dragon.setId(id);
                    yield new UpdateIdCommand(id, dragon);
                } catch (NumberFormatException e){
                    rc.printLine("Проверьте ID.");
                    yield null;
                }
            }
            case REMOVE_BY_ID -> {
                if (args.length != 1){
                    rc.printLine("Не верное колличество аргументов.");
                    yield null;
                } try {
                    Long id = Long.parseLong(args[0]);
                    yield new RemoveByIdCommand(id);
                } catch (NumberFormatException e){
                    rc.printLine("Проверьте ID.");
                    yield null;
                }
            }
            case CLEAR -> new ClearCommand();
            case EXECUTE_SCRIPT -> null;
            case EXIT -> null;
            case HEAD -> null;
            case ADD_IF_MIN -> null;
            case REMOVE_LOWER -> null;
            case SUM_OF_AGE -> null;
            case FILTER_CONTAINS_NAME -> null;
            case FILTER_STARTS_WITH_NAME -> null;
        }
    }
}
