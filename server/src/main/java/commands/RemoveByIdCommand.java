package commands;


import collection.CollectionManager;
import connection.Response;
import connection.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда удаления элемента коллекции по уникальному идентификатору (ID).
 * Осуществляет поиск элемента с указанным ID и его удаление при наличии.
 */
public class RemoveByIdCommand implements Command, Serializable {
    @Serial
    private final static long serialVersionUID  = 12L;
    private Long id;
    public RemoveByIdCommand(Long id) {
        this.id = id;
    }

    @Override
    public Response execute() {
        return new Response(ResponseStatus.OK, CollectionManager.removeById(id));
    }


    @Override
    public String getCommandName() {
        return "remove_by_id";
    }
}