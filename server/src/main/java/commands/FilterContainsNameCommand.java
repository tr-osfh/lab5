package commands;


import collection.CollectionManager;
import connection.Response;
import connection.ResponseStatus;

import java.io.Serial;

/**
 * Команда фильтрации элементов коллекции по содержанию подстроки в имени.
 * Выводит все элементы, значение поля name которых содержит указанную подстроку.
 */
public class FilterContainsNameCommand implements Command {
    @Serial
    private final static long serialID = 7L;
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
        return "filter_contains_name name : вывести элементы, значение поля name которых содержит заданную подстроку";
    }
}
