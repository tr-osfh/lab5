package commands;


import collection.CollectionManager;
import connection.Response;
import connection.ResponseStatus;

import java.io.Serial;

/**
 * Команда вывода всех элементов коллекции в строковом представлении.
 * Отображает полный список элементов коллекции или сообщение о пустой коллекции.
 */
public class ShowCommand implements Command {
    @Serial
    private final static long serialID = 14L;
    public ShowCommand() {
    }

    @Override
    public Response execute() {
        return new Response(ResponseStatus.OK, CollectionManager.show());
    }

    @Override
    public String getCommandName() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}