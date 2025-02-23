package root.files.commands;

import root.files.collection.CollectionManager;

public class FilterStartsWithNameCommand implements Command{
    private CollectionManager manager;

    public FilterStartsWithNameCommand(CollectionManager manager){
        this.manager = manager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            String namePart = args[1];
            manager.filterStartsWithName(namePart);
        } else {
            System.out.println("Неверное количество аргументов.");
        }
    }

    @Override
    public String getDescription() {
        return "filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки";
    }
}
