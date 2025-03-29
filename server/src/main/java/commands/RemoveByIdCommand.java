package commands;


import collection.CollectionManager;

/**
 * Команда удаления элемента коллекции по уникальному идентификатору (ID).
 * Осуществляет поиск элемента с указанным ID и его удаление при наличии.
 */
public class RemoveByIdCommand implements Command {

    private final CollectionManager manager;

    /**
     * Конструктор команды удаления по ID
     * @param manager Менеджер коллекции для доступа к методам удаления
     */
    public RemoveByIdCommand(CollectionManager manager) {
        this.manager = manager;
    }

    /**
     * @param args Аргументы команды (должен быть ровно 1 аргумент - ID)
     * @throws IllegalArgumentException Если:
     * <li>Количество аргументов неверное
     * <li>Аргумент не является числом
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            manager.removeById(Long.valueOf(args[1]));
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
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}