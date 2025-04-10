package commands;


import collection.CollectionManager;
import connection.Response;
import connection.ResponseStatus;
import seClasses.Dragon;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда добавления нового элемента в коллекцию.
 */
public class AddCommand implements Command, Serializable {

    @Serial
    private final static long serialVersionUID  = 1L;
    private Dragon dragon;
    public AddCommand(Dragon dragon){
        this.dragon = dragon;
    }

    @Override
    public Response execute(){
        return new Response(ResponseStatus.OK, CollectionManager.add(dragon));
    }

    @Override
    public String getCommandName() {
        return "add";
    }
}