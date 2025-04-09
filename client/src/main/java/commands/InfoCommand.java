package commands;

import connection.Response;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда вывода метаинформации о коллекции.
 * Отображает:
 * <li>Тип хранимых данных
 * <li>Дату и время инициализации
 * <li>Текущее количество элементов
 */
public class InfoCommand implements Command, Serializable {

    @Serial
    private static final long serialVersionUID  = 11L;

    public InfoCommand() {

    }

    @Override
    public Response execute() {
        return null;
    }

    @Override
    public String getDescription() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}