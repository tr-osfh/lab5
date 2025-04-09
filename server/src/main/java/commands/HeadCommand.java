package commands;


import collection.CollectionManager;
import connection.Response;
import connection.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда вывода первого элемента коллекции.
 * Отображает первого элемент PriorityQueue без изменения коллекции.
 */
public class HeadCommand implements Command, Serializable {
    @Serial
    private final static long serialVersionUID  = 9L;
    public HeadCommand() {
    }


    @Override
    public Response execute() {
        return new Response(ResponseStatus.OK, CollectionManager.head());
    }

    @Override
    public String getCommandName() {
        return "head";
    }
}