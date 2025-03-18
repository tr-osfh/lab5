package root.files.commands;

import root.files.collection.CollectionManager;
import root.files.console.ConsoleManager;
import root.files.seClasses.Dragon;
import root.files.console.DragonManager;

/**
 * Команда обновления элемента коллекции по ID.
 * Заменяет данные существующего элемента новыми значениями, полученными через интерактивный ввод.
 */
public class UpdateIdCommand implements Command {

    private CollectionManager manager;
    ConsoleManager cm = new ConsoleManager();
    private DragonManager dragonManager = new DragonManager(cm);

    /**
     * Конструктор команды обновления
     * @param manager Менеджер коллекции для доступа к методам обновления
     */
    public UpdateIdCommand(CollectionManager manager) {
        this.manager = manager;
    }

    /**
     * @param args Аргументы команды (должен быть ровно 1 аргумент - ID)
     * @throws IllegalArgumentException Если:
     * <li>Количество аргументов неверное
     * <li>Аргумент ID не является числом
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            try {
                Long dragonId = Long.parseLong(args[1]);
                Dragon dragon = dragonManager.setDragon();
                manager.updateById(dragonId, dragon);
            } catch (NumberFormatException e) {
                System.out.println("ID должен быть числом.");
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Возвращает описание команды для справки
     * @return Форматированная строка с синтаксисом и назначением
     */
    @Override
    public String getDescription(){
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}