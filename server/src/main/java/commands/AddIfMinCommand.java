package commands;

import collection.CollectionManager;
import connection.Response;
import connection.ResponseStatus;
import seClasses.Dragon;

import java.io.Serial;
import java.io.Serializable;

public class AddIfMinCommand implements Command, Serializable {

    @Serial
    private final static long serialVersionUID  = 2L;
    private Dragon dragon;
    public AddIfMinCommand(Dragon dragon) {
        this.dragon = dragon;
    }

    @Override
    public Response execute() {
        try {
            return new Response(ResponseStatus.OK, CollectionManager.addIfMin(dragon));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public String getCommandName() {
        return "add_if_min";
    }
}