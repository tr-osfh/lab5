package commands;

import connection.Response;
import connection.ResponseStatus;

import java.io.Serial;
import java.util.ArrayList;

public class ExecuteScriptCommand implements Command {
    @Serial
    private final static long serialID = 5L;
    private String link;
    private final ArrayList<Command> commandStack;
    public ExecuteScriptCommand(ArrayList<Command> commandStack) {
        this.commandStack = commandStack;
    }

    @Override
    public Response execute() {
        if (commandStack.isEmpty()){
            return new Response(ResponseStatus.ERROR, "Стак команд пуст.");
        } else {
            StringBuilder output = new StringBuilder();
            commandStack.forEach(command -> output.append(command.execute().getResponse()).append("\n"));
            return new Response(ResponseStatus.OK, output.substring(0, output.length() - 1));
        }
    }

    /**
     * Возвращает описание команды для справки
     * @return Форматированная строка с синтаксисом и назначением
     */
    @Override
    public String getCommandName() {
        return "execute_script";
    }
}