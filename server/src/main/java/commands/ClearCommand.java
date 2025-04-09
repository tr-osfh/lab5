package commands;

import collection.CollectionManager;
import connection.Response;
import connection.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда полной очистки коллекции.
 * Удаляет все элементы из коллекции без возможности восстановления.
 */
public class ClearCommand implements Command, Serializable {
    @Serial
    private final static long serialVersionUID  = 3L;

    public ClearCommand(CollectionManager manager) {
    }

    @Override
    public Response execute() {
        CollectionManager.clear();
        return new Response(ResponseStatus.OK, "Коллекция успешно удалена.");
    }

    @Override
    public String getCommandName() {
        return "clear";
    }
}