package commands;


import java.io.Serial;
import java.io.Serializable;

/**
 * Команда завершения работы приложения.
 * Немедленно останавливает выполнение программы без сохранения данных.
 */
public class ExitCommand implements Command, Serializable {


    @Serial
    private static final long serialID = 6L;

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