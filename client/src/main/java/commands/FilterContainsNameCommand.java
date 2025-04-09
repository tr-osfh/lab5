package commands;


import connection.Response;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда фильтрации элементов коллекции по содержанию подстроки в имени.
 * Выводит все элементы, значение поля name которых содержит указанную подстроку.
 */
public class FilterContainsNameCommand implements Command, Serializable {

    @Serial
    private static final long serialVersionUID  = 7L;

    private String partOfName;

    public FilterContainsNameCommand(String partOfName){
        this.partOfName = partOfName;
    }
    @Override
    public Response execute() {
        return null;
    }

    @Override
    public String getDescription() {
        return "filter_contains_name name : вывести элементы, значение поля name которых содержит заданную подстроку";
    }
}
