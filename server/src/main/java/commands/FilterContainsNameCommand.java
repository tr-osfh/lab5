package commands;


import collection.CollectionManager;
import connection.Response;
import connection.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда фильтрации элементов коллекции по содержанию подстроки в имени.
 * Выводит все элементы, значение поля name которых содержит указанную подстроку.
 */
public class FilterContainsNameCommand implements Command, Serializable {

    @Serial
    private final static long serialVersionUID  = 7L;

    private String namePart;

    public FilterContainsNameCommand(String namePart){
        this.namePart = namePart;
    }

    @Override
    public Response execute() {
        return new Response(ResponseStatus.OK, CollectionManager.filterContainsName(namePart));
    }

    @Override
    public String getCommandName() {
        return "filter_contains_name";
    }
}
