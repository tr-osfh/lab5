package commands;


import collection.CollectionManager;
import connection.Response;
import connection.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Команда вывода справочной информации о доступных командах.
 * Отображает список всех зарегистрированных команд с их описаниями.
 */
public class HelpCommand implements Command, Serializable {
    @Serial
    private final static long serialVersionUID  = 10L;
    public HelpCommand() {

    }

    @Override
    public Response execute() {
        return new Response(
                ResponseStatus.OK,
                Arrays.stream(CommandsList.CommandType.values())
                        .map(CommandsList.CommandType::getDescription)
                        .filter(description -> !description.isEmpty())
                        .collect(Collectors.joining("\n"))
        );
    }
    /**
     * Возвращает описание команды для системы помощи
     * @return Строка с синтаксисом и назначением команды
     */
    @Override
    public String getCommandName() {
        return "help";
    }
}