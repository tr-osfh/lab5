package root.files.commands;

import root.files.collection.CollectionManager;

/**
 * Команда фильтрации элементов коллекции по содержанию подстроки в имени.
 * Выводит все элементы, значение поля name которых содержит указанную подстроку.
 */
public class FilterContainsNameCommand implements Command {
    private CollectionManager manager;

    /**
     * Конструктор команды фильтрации
     * @param manager Менеджер коллекции для доступа к методам фильтрации
     */
    public FilterContainsNameCommand(CollectionManager manager){
        this.manager = manager;
    }

    /**
     * @param args Аргументы команды (должен быть ровно 1 аргумент - подстрока)
     * @throws IllegalArgumentException Если количество аргументов неверное
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            String namePart = args[1];
            manager.filterContainsName(namePart);
        } else {
            throw new IllegalArgumentException("Неверное количество аргументов. Используйте: filter_contains_name (name)");
        }
    }

    /**
     * Возвращает описание команды для справки
     * @return Форматированная строка с синтаксисом и назначением
     */
    @Override
    public String getDescription() {
        return "filter_contains_name name : вывести элементы, значение поля name которых содержит заданную подстроку";
    }
}
