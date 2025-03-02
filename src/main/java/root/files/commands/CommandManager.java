package root.files.commands;


import root.files.collection.CollectionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import root.files.console.ConsoleManager;

/// Invoker
public class CommandManager {
    private static HashMap<String, Command> commands = new HashMap<>();
    private ConsoleManager consoleManager;

    public CommandManager(CollectionManager colManager, ConsoleManager consoleManager){
        this.consoleManager = consoleManager;

        commands.put("exit", new ExitCommand(colManager));
        commands.put("info", new infoCommand(colManager));
        commands.put("show", new ShowCommand(colManager));
        commands.put("save", new SaveCommand(colManager));
        commands.put("clear", new ClearCommand(colManager));
        commands.put("remove_by_id", new RemoveByIdCommand(colManager));
        commands.put("add", new AddCommand(colManager));
        commands.put("update", new UpdateIdCommand(colManager));
        commands.put("help", new HelpCommand(colManager));
        commands.put("execute_script", new ExecuteScriptCommand(colManager));
        commands.put("head", new HeadCommand(colManager));
        commands.put("add_if_min", new AddIfMinCommand(colManager));
        commands.put("remove_lower", new RemoveLowerCommand(colManager));
        commands.put("sum_of_age", new SumOfAgeCommand(colManager));
        commands.put("filter_contains_name", new FilterContainsNameCommand(colManager));
        commands.put("filter_starts_with_name", new FilterStartsWithNameCommand(colManager));
    }

    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }

    public void executeCommand(){
        try{
            String[] args = consoleManager.readCommand();
            if (commands.containsKey(args[0])){
                try {
                    commands.get(args[0]).execute(args);
                } catch (IllegalArgumentException e){
                    System.out.println("Ошибка. Попробуйте еще раз.");
                }
            } else {
                System.out.println("Команда не найдена.");
            }
        } catch (IllegalArgumentException e){
            System.out.println("Что-то пошло не так. Попробуйте еще раз.");
        }
    }

    public void executeScriptCommand(ArrayList<String> script){
        try{
            for (String command : script){
                String[] args = command.split(" ");
                if (commands.containsKey(args[0])){
                    try {
                        commands.get(args[0]).execute(args);
                    } catch (IllegalArgumentException e){
                        System.out.println("Ошибка. Попробуйте еще раз.");
                    }
                } else {
                    System.out.println("Команда не найдена.");
                }
            }
        } catch (IllegalArgumentException e){
            System.out.println("Что-то пошло не так. Попробуйте еще раз.");
        }
    }
}
