package commands;


import collection.CollectionManager;

/**
 * Команда сохранения текущего состояния коллекции в файл.
 * Записывает данные в формате CSV в файл, указанный через переменную окружения DB_FILE_PATH.
 */
public class SaveCommand implements Command {
    private CollectionManager manager;

    /**
     * Конструктор команды сохранения
     * @param manager Менеджер коллекции для доступа к методам сохранения
     */
    public SaveCommand(CollectionManager manager) {
        this.manager = manager;
    }

    /**
     * @param args Аргументы команды (должны отсутствовать)
     * @throws IllegalArgumentException При наличии аргументов
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            manager.save();
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
        return "save : сохранить коллекцию в файл";
    }
}
