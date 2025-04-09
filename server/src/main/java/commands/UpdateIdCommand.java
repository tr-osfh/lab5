package commands;


import collection.CollectionManager;
import connection.Response;
import connection.ResponseStatus;
import seClasses.Dragon;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда обновления элемента коллекции по ID.
 * Заменяет данные существующего элемента новыми значениями, полученными через интерактивный ввод.
 */
public class UpdateIdCommand implements Command, Serializable {

    @Serial
    private final static long serialVersionUID  = 16L;
    private Long id;
    private Dragon dragon;

    public UpdateIdCommand(Long id, Dragon dragon) {
        this.id = id;
        this.dragon = dragon;
    }

    @Override
    public Response execute() {
        return new Response(ResponseStatus.OK, CollectionManager.updateById(id, dragon));
    }

    @Override
    public String getCommandName(){
        return "update";
    }
}