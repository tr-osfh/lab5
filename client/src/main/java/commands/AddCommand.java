package commands;


import seClasses.Dragon;

/**
 * Команда добавления нового элемента в коллекцию.
 */
public class AddCommand implements Command {

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