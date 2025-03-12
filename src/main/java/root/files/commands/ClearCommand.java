package root.files.commands;

import root.files.collection.CollectionManager;

/**
 * Команда полной очистки коллекции.
 * Удаляет все элементы из коллекции без возможности восстановления.
 */
public class ClearCommand implements Command {

    private CollectionManager manager;

    /**
     * Конструктор команды очистки
     * @param manager Менеджер коллекции для выполнения операции очистки
     */
    public ClearCommand(CollectionManager manager) {
        this.manager = manager;
    }

    /**
     * Выполняет очистку коллекции при условии отсутствия аргументов
     * @param args Аргументы команды (должны отсутствовать)
     * @throws IllegalArgumentException Если присутствуют аргументы
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            manager.clear();
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
        return "clear : очистить коллекцию";
    }
}