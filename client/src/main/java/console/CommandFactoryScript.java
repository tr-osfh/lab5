package console;

import commands.*;
import objectsCreation.CreateDragon;
import seClasses.Dragon;

public class CommandFactoryScript {

    private static final ReadController rc = new ReadController();

    public static Command createCommand(CommandsList command, String[] args){
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
                if (args.length != 2){
                    rc.printLine("Не верное колличество аргументов.");
                    yield null;
                } try {
                    Long id = Long.parseLong(args[1]);
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
                if (args.length != 2){
                    rc.printLine("Не верное колличество аргументов.");
                    yield null;
                } try {
                    Long id = Long.parseLong(args[1]);
                    yield new RemoveByIdCommand(id);
                } catch (NumberFormatException e){
                    rc.printLine("Проверьте ID.");
                    yield null;
                }
            }
            case CLEAR -> new ClearCommand();
            case EXIT -> new ExitCommand();
            case HEAD -> new HeadCommand();
            case ADD_IF_MIN -> {
                Dragon dragon;
                CreateDragon dragonCreator = new CreateDragon(rc);
                dragon = dragonCreator.create();
                yield new AddIfMinCommand(dragon);
            }
            case REMOVE_LOWER -> {
                Dragon dragon;
                CreateDragon dragonCreator = new CreateDragon(rc);
                dragon = dragonCreator.create();
                yield new RemoveLowerCommand(dragon);
            }
            case SUM_OF_AGE -> new SumOfAgeCommand();
            case FILTER_CONTAINS_NAME -> {
                if (args.length != 2){
                    rc.printLine("Не верное колличество аргументов.");
                    yield null;
                } try {
                    String link = args[1];
                    yield new FilterContainsNameCommand(link);
                } catch (Exception e){
                    rc.printLine("Проверьте ввод.");
                    yield null;
                }
            }
            case FILTER_STARTS_WITH_NAME -> {
                if (args.length != 2){
                    rc.printLine("Не верное колличество аргументов.");
                    yield null;
                } try {
                    String link = args[1];
                    yield new FilterStartsWithNameCommand(link);
                } catch (Exception e){
                    rc.printLine("Проверьте ввод.");
                    yield null;
                }
            }
            default -> {
                yield null;
            }
        };
    }
}
