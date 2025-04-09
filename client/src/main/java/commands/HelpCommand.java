package commands;


import connection.Response;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда вывода справочной информации о доступных командах.
 * Отображает список всех зарегистрированных команд с их описаниями.
 */
public class HelpCommand implements Command, Serializable {

    @Serial
    private static final long serialVersionUID  = 10L;

    public HelpCommand() {

    }

    @Override
    public Response execute() {
        return null;
    }

    /**
     * Возвращает описание команды для системы помощи
     * @return Строка с синтаксисом и назначением команды
     */
    @Override
    public String getDescription() {
        return "help : вывести справку по доступным командам";
    }
}