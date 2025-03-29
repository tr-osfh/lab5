package commands;


import collection.CollectionManager;

/**
 * Команда фильтрации элементов коллекции по началу имени.
 * Выводит все элементы, значение поля name которых начинается с указанной подстроки.
 */
public class FilterStartsWithNameCommand implements Command {
    private CollectionManager manager;

    /**
     * Конструктор команды префиксного поиска
     * @param manager Менеджер коллекции для доступа к методам фильтрации
     */
    public FilterStartsWithNameCommand(CollectionManager manager){
        this.manager = manager;
    }

    /**
     * @param args Аргументы команды (должен быть ровно 1 аргумент - начальная подстрока)
     * @throws IllegalArgumentException Если количество аргументов неверное
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            String namePart = args[1];
            manager.filterStartsWithName(namePart);
        } else {
            throw new IllegalArgumentException();
        }
    }

     /**
     * Возвращает описание команды для справки
     * @return Форматированная строка с синтаксисом и назначением
     */
    @Override
    public String getDescription() {
        return "filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки";
    }
}