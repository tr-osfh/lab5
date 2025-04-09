package commands;


import connection.Response;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда фильтрации элементов коллекции по началу имени.
 * Выводит все элементы, значение поля name которых начинается с указанной подстроки.
 */
public class FilterStartsWithNameCommand implements Command, Serializable {


    @Serial
    private static final long serialVersionUID  = 8L;

    private String partOfName;

    public FilterStartsWithNameCommand(String partOfName){
        this.partOfName = partOfName;
    }

    @Override
    public Response execute() {
        return null;
    }

    @Override
    public String getDescription() {
        return "filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки";
    }
}