package root.files.commands;

import root.files.collection.CollectionManager;

/**
 * Команда вычисления суммарного возраста элементов коллекции.
 * Суммирует значения поля age всех драконов, игнорируя элементы с неустановленным возрастом (null).
 */
public class SumOfAgeCommand implements Command {
    private CollectionManager manager;

    /**
     * Конструктор команды суммирования возраста
     * @param manager Менеджер коллекции для доступа к данным
     */
    public SumOfAgeCommand(CollectionManager manager){
        this.manager = manager;
    }

    /**
     * @param args Аргументы команды (должны отсутствовать)
     * @throws IllegalArgumentException При наличии аргументов
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            manager.sumOfAge();
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
        return "sum_of_age : вывести сумму значений поля age для всех элементов коллекции (игнорирует null)";
    }
}
