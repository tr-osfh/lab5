package commands;

import collection.CollectionManager;
import connection.Response;
import connection.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда завершения работы приложения.
 * Немедленно останавливает выполнение программы без сохранения данных.
 */
public class ExitCommand implements Command, Serializable {
    @Serial
    private final static long serialVersionUID  = 6L;
    public ExitCommand(){
    }

    @Override
    public Response execute() {
        CollectionManager.exit();
        return new Response(ResponseStatus.OK, "Работа приложение завершена.");
    }

    @Override
    public String getCommandName() {
        return "exit";
    }
}