package commands;


import java.io.Serial;
import java.io.Serializable;

/**
 * Команда вывода всех элементов коллекции в строковом представлении.
 * Отображает полный список элементов коллекции или сообщение о пустой коллекции.
 */
public class ShowCommand implements Command, Serializable {

    @Serial
    private static final long serialID = 14L;

    public ShowCommand() {

    }

    @Override
    public void execute(String[] args) {
    }

    @Override
    public String getDescription() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}