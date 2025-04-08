package commands;

import java.io.Serial;
import java.io.Serializable;

public class DefaultCommand implements Command, Serializable {

    @Serial
    private static final long serialID = 4L;

    @Override
    public void execute(String[] args) {

    }

    @Override
    public String getDescription() {
        return "";
    }
}
