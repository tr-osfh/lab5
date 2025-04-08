package commands;


import collection.CollectionManager;
import collection.DragonManager;
import connection.Response;
import connection.ResponseStatus;
import seClasses.Dragon;

import java.io.Serial;

import static collection.CollectionManager.add;

/**
 * Команда добавления нового элемента в коллекцию.
 */
public class AddCommand implements Command {

    @Serial
    private final static long serialID = 1L;
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