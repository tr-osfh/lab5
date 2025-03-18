package root.files.commands;

import root.files.collection.CollectionManager;
import root.files.seClasses.Dragon;

/**
 * Команда удаления элементов, меньших чем заданный.
 * Удаляет все элементы коллекции, значение координаты X которых меньше,
 * чем у указанного дракона. Требует интерактивного ввода параметров дракона.
 */
public class RemoveLowerCommand implements Command {

    private CollectionManager manager;

    /**
     * Конструктор команды условного удаления
     * @param manager Менеджер коллекции для выполнения операций сравнения и удаления
     */
    public RemoveLowerCommand(CollectionManager manager){
        this.manager = manager;
    }

    /**
     * @param args Аргументы команды (должны отсутствовать)
     * @throws IllegalArgumentException Если:
     * <li>Присутствуют аргументы
     * <li>Ошибка ввода данных
     * <li>Некорректные параметры дракона
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            Dragon dragon = manager.getDragonManager().setDragon();
            manager.removeLower(dragon);
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
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }
}