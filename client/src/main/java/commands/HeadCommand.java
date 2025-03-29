package commands;


/**
 * Команда вывода первого элемента коллекции.
 * Отображает первого элемент PriorityQueue без изменения коллекции.
 */
public class HeadCommand implements Command {

    public HeadCommand() {

    }

    @Override
    public void execute(String[] args) {
    }

    @Override
    public String getDescription() {
        return "head : вывести первый элемент коллекции";
    }
}