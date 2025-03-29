package commands;

import collection.CollectionManager;
import seClasses.Dragon;

/**
 * Команда условного добавления элемента в коллекцию.
 * Добавляет новый элемент только если его значение (по координате X)
 * меньше минимального значения в текущей коллекции.
 */
public class AddIfMinCommand implements Command {
    private CollectionManager manager;

    /**
     * Конструктор команды условного добавления
     * @param manager Менеджер коллекции для выполнения операций сравнения и добавления
     */
    public AddIfMinCommand(CollectionManager manager) {
        this.manager = manager;
    }

    /**
     * @param args Аргументы команды (должны отсутствовать)
     * @throws IllegalArgumentException Если:
     * <li>Присутствуют аргументы
     * <li>Ошибка ввода данных
     * <li>Коллекция пуста
     * <li>Некорректные параметры дракона
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            try {
                Dragon dragon = manager.getDragonManager().setDragon();
                manager.addIfMin(dragon);
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
        return "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
    }
}