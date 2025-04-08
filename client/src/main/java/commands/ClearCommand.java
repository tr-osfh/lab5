package commands;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда полной очистки коллекции.
 * Удаляет все элементы из коллекции без возможности восстановления.
 */
public class ClearCommand implements Command, Serializable {

    @Serial
    private static final long serialID = 3L;

    public ClearCommand(){
    }

    @Override
    public void execute(String[] args) {
    }

    @Override
    public String getDescription() {
        return "clear : очистить коллекцию";
    }
}