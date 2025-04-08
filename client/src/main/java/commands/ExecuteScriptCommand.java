package commands;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Команда выполнения скрипта из файла. Читает и исполняет последовательность команд,
 * включая создание объектов Dragon с валидацией. Обрабатывает вложенные скрипты с защитой от рекурсии.
 */
public class ExecuteScriptCommand implements Command, Serializable {

    @Serial
    private static final long serialID = 5L;

    private ArrayList<Command> commands;
    public ExecuteScriptCommand(ArrayList<Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute(String[] args) {
    }
    @Override
    public String getDescription() {
        return "execute_script file_path : считать и исполнить скрипт из указанного файла. Поддерживает команды с объектами.";
    }
}