package root.files.commands;

import root.files.collection.CollectionManager;

/**
 * Команда вывода первого элемента коллекции.
 * Отображает первого элемент PriorityQueue без изменения коллекции.
 */
public class HeadCommand implements Command {

    private CollectionManager manager;

    /**
     * Конструктор команды просмотра головы коллекции
     * @param manager Менеджер коллекции для доступа к данным
     */
    public HeadCommand(CollectionManager manager) {
        this.manager = manager;
    }

    /**
     * @param args Аргументы команды (должны отсутствовать)
     * @throws IllegalArgumentException При наличии аргументов
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            manager.head();
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
        return "head : вывести первый элемент коллекции";
    }
}