package commands;

import collection.CollectionManager;

/**
 * Команда завершения работы приложения.
 * Немедленно останавливает выполнение программы без сохранения данных.
 */
public class ExitCommand implements Command {

    public ExitCommand(){
    }

    @Override
    public void execute(String[] args) {
    }

    @Override
    public String getDescription() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}