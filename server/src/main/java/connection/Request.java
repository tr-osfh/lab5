package connection;

import seClasses.Dragon;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable {
    @Serial
    private static final long serialID = 0L;

    private String commandName;
    private String args = "";
    private Dragon dragon = null;
    public Request(ResponseStatus status, String commandName, Dragon dragon){
        this.commandName = commandName.trim();
    }

    public Request(String commandName, String args) {
        this.commandName = commandName.trim();
        this.args = args.trim();
    }

    public Request(String commandName, Dragon dragon){
        this.commandName = commandName.trim();
        this.dragon = dragon;
    }

    public Request(String commandName, String args, Dragon dragon){
        this.commandName = commandName.trim();
        this.args = args.trim();
        this.dragon = dragon;
    }

    public boolean isEmpty(){
        return commandName.isEmpty() && args.isEmpty() && dragon == null;
    }
    public String getCommandName(){
        return commandName;
    }
    public String getArgs() {
        return args;
    }
    public Dragon getDragon() {
        return dragon;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Request request = (Request) object;
        return Objects.equals(commandName, request.commandName) && Objects.equals(args, request.args) && Objects.equals(dragon, request.dragon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandName, args, dragon);
    }

    @Override
    public String toString() {
        return "Request{" +
                "commandName='" + commandName + '\'' +
                ", args='" + args + '\'' +
                ", dragon=" + dragon +
                '}';
    }
}
