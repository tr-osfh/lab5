package commands;


import collection.CollectionManager;

/**
 * Команда вывода всех элементов коллекции в строковом представлении.
 * Отображает полный список элементов коллекции или сообщение о пустой коллекции.
 */
public class ShowCommand implements Command {

    private CollectionManager manager;

    /**
     * Конструктор команды отображения коллекции
     * @param manager Менеджер коллекции для доступа к данным
     */
    public ShowCommand(CollectionManager manager) {
        this.manager = manager;
    }

    /**
     * @param args Аргументы команды (должны отсутствовать)
     * @throws IllegalArgumentException При наличии аргументов
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            manager.show();
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
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}