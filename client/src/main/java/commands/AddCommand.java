package commands;


import seClasses.Dragon;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда добавления нового элемента в коллекцию.
 */
public class AddCommand implements Command, Serializable {

    @Serial
    private static final long serialID = 1L;

    private Dragon dragon;
    public AddCommand(Dragon dragon){
        this.dragon = dragon;
    }

    @Override
    public void execute(String[] args) {}

    @Override
    public String getDescription() {
        return "add {element} : добавить новый элемент в коллекцию";
    }

}