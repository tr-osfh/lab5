package commands;

import collection.CollectionManager;

/**
 * Команда завершения работы приложения.
 * Немедленно останавливает выполнение программы без сохранения данных.
 */
public class ExitCommand implements Command {

    private CollectionManager manager;

    /**
     * Конструктор команды завершения
     * @param manager Менеджер коллекции для доступа к системному методу выхода
     */
    public ExitCommand(CollectionManager manager){
        this.manager = manager;
    }

    /**
     * Выполняет немедленный выход из программы при условии отсутствия аргументов
     * @param args Аргументы команды (должны отсутствовать)
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            manager.exit();
        }
    }

    /**
     * Возвращает описание команды для справки
     * @return Форматированная строка с синтаксисом и назначением
     */
    @Override
    public String getDescription() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}