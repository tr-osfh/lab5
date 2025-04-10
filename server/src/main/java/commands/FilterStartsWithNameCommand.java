package commands;


import collection.CollectionManager;
import connection.Response;
import connection.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда фильтрации элементов коллекции по началу имени.
 * Выводит все элементы, значение поля name которых начинается с указанной подстроки.
 */
public class FilterStartsWithNameCommand implements Command, Serializable {

    @Serial
    private final static long serialVersionUID  = 8L;

    private final String namePart;

    public FilterStartsWithNameCommand(String namePart){
        this.namePart = namePart;
    }

    @Override
    public Response execute() {
        return new Response(ResponseStatus.OK, CollectionManager.filterStartsWithName(namePart));
    }

    @Override
    public String getCommandName() {
        return "filter_starts_with_name";
    }
}