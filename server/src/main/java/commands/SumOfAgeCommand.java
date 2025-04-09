package commands;


import collection.CollectionManager;
import connection.Response;
import connection.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда вычисления суммарного возраста элементов коллекции.
 * Суммирует значения поля age всех драконов, игнорируя элементы с неустановленным возрастом (null).
 */
public class SumOfAgeCommand implements Command, Serializable {
    @Serial
    private final static long serialVersionUID  = 15L;

    public SumOfAgeCommand(){
    }

    @Override
    public Response execute() {
        return new Response(ResponseStatus.OK, CollectionManager.sumOfAge());
    }

    @Override
    public String getCommandName() {
        return "sum_of_age";
    }
}
