package commands;

import connection.Response;
import connection.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

public class DefaultCommand implements Command, Serializable {

    @Serial
    private static final long serialID = 4L;

    @Override
    public Response execute() {
        return new Response(ResponseStatus.INFO, "Неизветная команда, воспользуйтесь help, чтобы посмотреть список доступных команд.");
    }
    @Override
    public String getCommandName() {
        return "";
    }
}
