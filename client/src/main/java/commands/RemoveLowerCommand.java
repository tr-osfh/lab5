package commands;

import seClasses.Dragon;

/**
 * Команда удаления элементов, меньших чем заданный.
 * Удаляет все элементы коллекции, значение координаты X которых меньше,
 * чем у указанного дракона. Требует интерактивного ввода параметров дракона.
 */
public class RemoveLowerCommand implements Command {

    private Dragon dragon;

    public RemoveLowerCommand(Dragon dragon){
        this.dragon = dragon;
    }

    @Override
    public void execute(String[] args) {
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