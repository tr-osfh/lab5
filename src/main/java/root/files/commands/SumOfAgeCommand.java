package root.files.commands;

import root.files.collection.CollectionManagerRecever;

public class SumOfAgeCommand implements Command{
    private CollectionManagerRecever manager;

    public SumOfAgeCommand(CollectionManagerRecever manager){
        this.manager = manager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
        manager.sumOfAge();
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getDescription() {
        return "sum_of_age : вывести сумму значений поля age для всех элементов коллекции";
    }
}
