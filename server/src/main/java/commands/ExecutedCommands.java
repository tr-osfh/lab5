package commands;

import java.util.ArrayList;

public class ExecutedCommands{

    public ExecutedCommands(ArrayList<String> executedCommands) {
        this.executedCommands = executedCommands;
    }

    private ArrayList<String> executedCommands = new ArrayList<String>();

    public void addExecutedCommand(String line){
        executedCommands.add(line);
    }

    public void setExecutedCommands(ArrayList<String> executedCommands) {
        this.executedCommands = executedCommands;
    }

    public ArrayList<String> getExecutedCommands() {
        return executedCommands;
    }
}
