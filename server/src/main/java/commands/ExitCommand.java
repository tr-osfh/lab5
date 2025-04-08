package commands;

import collection.CollectionManager;
import connection.Response;
import connection.ResponseStatus;

import java.io.Serial;

/**
 * Команда завершения работы приложения.
 * Немедленно останавливает выполнение программы без сохранения данных.
 */
public class ExitCommand implements Command {
    @Serial
    private final static long serialID = 6L;
    public ExitCommand(){
    }

    @Override
    public Response execute() {
        CollectionManager.exit();
        return new Response(ResponseStatus.OK, "Работа приложение завершена.");
    }

    @Override
    public String getCommandName() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}