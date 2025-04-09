package commands;

import collection.CollectionManager;
import connection.Response;
import connection.ResponseStatus;

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
    private final static long serialVersionUID  = 11L;
    public InfoCommand() {

    }

    @Override
    public Response execute() {
        return new Response(ResponseStatus.OK, CollectionManager.info());
    }

    @Override
    public String getCommandName() {
        return "info";
    }
}