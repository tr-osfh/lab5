package commands;


import connection.Response;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда вывода всех элементов коллекции в строковом представлении.
 * Отображает полный список элементов коллекции или сообщение о пустой коллекции.
 */
public class ShowCommand implements Command, Serializable {

    @Serial
    private static final long serialVersionUID  = 14L;

    public ShowCommand() {
    }

    @Override
    public Response execute() {
        return null;
    }

    @Override
    public String getDescription() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}