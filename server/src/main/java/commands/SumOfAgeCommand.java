package commands;


import collection.CollectionManager;
import connection.Response;
import connection.ResponseStatus;

import java.io.Serial;

/**
 * Команда вычисления суммарного возраста элементов коллекции.
 * Суммирует значения поля age всех драконов, игнорируя элементы с неустановленным возрастом (null).
 */
public class SumOfAgeCommand implements Command {
    @Serial
    private final static long serialID = 15L;

    public SumOfAgeCommand(){
    }

    @Override
    public Response execute() {
        return new Response(ResponseStatus.OK, CollectionManager.sumOfAge());
    }

    @Override
    public String getCommandName() {
        return "sum_of_age : вывести сумму значений поля age для всех элементов коллекции (игнорирует null)";
    }
}
