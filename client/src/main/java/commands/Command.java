package commands;

import java.io.Serializable;

public interface Command extends Serializable {
    void execute(String[] args);

    String getDescription();
}
