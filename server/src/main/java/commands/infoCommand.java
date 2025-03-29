package commands;

import collection.CollectionManager;

/**
 * Команда вывода метаинформации о коллекции.
 * Отображает:
 * <li>Тип хранимых данных
 * <li>Дату и время инициализации
 * <li>Текущее количество элементов
 */
public class infoCommand implements Command {

    private CollectionManager manager;

    /**
     * Конструктор команды информации
     * @param manager Менеджер коллекции для доступа к метаданным
     */
    public infoCommand(CollectionManager manager) {
        this.manager = manager;
    }

    /**
     * @param args Аргументы команды (должны отсутствовать)
     * @throws IllegalArgumentException При наличии аргументов
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            manager.info();
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
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}