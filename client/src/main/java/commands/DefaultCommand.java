package commands;

import java.io.Serializable;

public class DefaultCommand implements Command, Serializable {

    @Override
    public void execute(String[] args) {

    }

    @Override
    public String getDescription() {
        return "";
    }
}
