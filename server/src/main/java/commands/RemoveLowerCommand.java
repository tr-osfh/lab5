package commands;

import collection.CollectionManager;
import connection.Response;
import connection.ResponseStatus;
import seClasses.Dragon;

import java.io.Serial;

/**
 * Команда удаления элементов, меньших чем заданный.
 * Удаляет все элементы коллекции, значение координаты X которых меньше,
 * чем у указанного дракона. Требует интерактивного ввода параметров дракона.
 */
public class RemoveLowerCommand implements Command {
    @Serial
    private final static long serialID = 13L;
    private final Dragon dragon;
    public RemoveLowerCommand(Dragon dragon){
        this.dragon = dragon;
    }

    @Override
    public Response execute() {
        return new Response(ResponseStatus.OK, CollectionManager.removeLower(dragon));
    }

    @Override
    public String getCommandName() {
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }
}