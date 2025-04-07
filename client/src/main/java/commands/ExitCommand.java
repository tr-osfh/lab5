package commands;


import java.io.Serializable;

/**
 * Команда завершения работы приложения.
 * Немедленно останавливает выполнение программы без сохранения данных.
 */
public class ExitCommand implements Command, Serializable {

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