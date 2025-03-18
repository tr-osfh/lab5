package root.files.commands;

import root.files.collection.CollectionManager;
import root.files.collection.IdGenerator;
import root.files.console.DragonManager;
import root.files.seClasses.Dragon;

/**
 * Команда добавления нового элемента в коллекцию.
 */
public class AddCommand implements Command {

    private CollectionManager manager;

    /**
     * Конструктор команды добавления
     * @param manager Менеджер коллекции для выполнения операций
     */
    public AddCommand(CollectionManager manager){
        this.manager = manager;
    }

    /**
     * Выполняет логику команды:
     * 1. Проверяет отсутствие аргументов
     * 2. Запрашивает данные дракона через консоль
     * 3. Добавляет элемент в коллекцию
     *
     * @param args Аргументы команды (должны отсутствовать)
     * @throws IllegalArgumentException Если:
     * <li>Присутствуют аргументы
     * <li>Ошибка ввода данных
     * <li>Некорректные параметры дракона
     */
    @Override
    public void execute(String[] args){
        if (args.length == 1) {
            try {
                Dragon dragon = manager.getDragonManager().setDragon();
                manager.add(dragon);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e);
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
    public String getDescription() {
        return "add {element} : добавить новый элемент в коллекцию";
    }
}