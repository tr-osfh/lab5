package commands;


import connection.Response;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда вывода первого элемента коллекции.
 * Отображает первого элемент PriorityQueue без изменения коллекции.
 */
public class HeadCommand implements Command, Serializable {


    @Serial
    private static final long serialVersionUID  = 9L;

    public HeadCommand() {

    }

    @Override
    public Response execute() {
        return null;
    }

    @Override
    public String getDescription() {
        return "head : вывести первый элемент коллекции";
    }
}