package commands;


import java.io.Serializable;

/**
 * Команда фильтрации элементов коллекции по содержанию подстроки в имени.
 * Выводит все элементы, значение поля name которых содержит указанную подстроку.
 */
public class FilterContainsNameCommand implements Command, Serializable {
    private String partOfName;

    public FilterContainsNameCommand(String partOfName){
        this.partOfName = partOfName;
    }

    @Override
    public void execute(String[] args) {
    }

    @Override
    public String getDescription() {
        return "filter_contains_name name : вывести элементы, значение поля name которых содержит заданную подстроку";
    }
}
