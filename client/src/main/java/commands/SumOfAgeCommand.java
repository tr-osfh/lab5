package commands;


/**
 * Команда вычисления суммарного возраста элементов коллекции.
 * Суммирует значения поля age всех драконов, игнорируя элементы с неустановленным возрастом (null).
 */
public class SumOfAgeCommand implements Command {

    public SumOfAgeCommand(){

    }

    @Override
    public void execute(String[] args) {

    }

    @Override
    public String getDescription() {
        return "sum_of_age : вывести сумму значений поля age для всех элементов коллекции (игнорирует null)";
    }
}
