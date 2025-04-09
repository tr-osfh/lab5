package commands;


import connection.Response;
import seClasses.Dragon;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда условного добавления элемента в коллекцию.
 * Добавляет новый элемент только если его значение (по координате X)
 * меньше минимального значения в текущей коллекции.
 */
public class AddIfMinCommand implements Command, Serializable {

    @Serial
    private static final long serialVersionUID  = 2L;

    private Dragon dragon;

    public AddIfMinCommand(Dragon dragon) {
        this.dragon = dragon;
    }

    @Override
    public Response execute() {
        return null;
    }

    @Override
    public String getDescription() {
        return "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
    }
}