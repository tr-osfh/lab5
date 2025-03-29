package commands;


import seClasses.Dragon;

/**
 * Команда условного добавления элемента в коллекцию.
 * Добавляет новый элемент только если его значение (по координате X)
 * меньше минимального значения в текущей коллекции.
 */
public class AddIfMinCommand implements Command {
    private Dragon dragon;

    public AddIfMinCommand(Dragon dragon) {
        this.dragon = dragon;
    }

    @Override
    public void execute(String[] args) {
    }

    @Override
    public String getDescription() {
        return "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
    }
}