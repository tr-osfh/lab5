package root.files.commands;

import root.files.collection.CollectionManagerRecever;

public class FilterContainsNameCommand implements Command{
    private CollectionManagerRecever manager;

    public FilterContainsNameCommand(CollectionManagerRecever manager){
        this.manager = manager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            try {
                String namePart = args[1];
                manager.filterContainsName(namePart);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getDescription() {
        return "filter_contains_name name : вывести элементы, значение поля name которых содержит заданную подстроку";
    }
}
