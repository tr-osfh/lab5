package commands;

import connection.Response;

public interface Command {
    Response execute();
    String getCommandName();
}
