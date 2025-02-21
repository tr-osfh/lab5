package root.files.commands;


import root.files.collection.CollectionManagerRecever;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/// Invoker
public class CommandManager {

    private static HashMap<String, Command> commands = new HashMap<>();

    public CommandManager(CollectionManagerRecever manager){
        commands.put("exit", new ExitCommand(manager));
        commands.put("info", new infoCommand(manager));
        commands.put("show", new ShowCommand(manager));
        commands.put("save", new SaveCommand(manager));
        commands.put("clear", new ClearCommand(manager));
        commands.put("remove_by_id", new RemoveByIdCommand(manager));
        commands.put("add", new AddCommand(manager));
        commands.put("update", new UpdateIdCommand(manager));
        commands.put("help", new HelpCommand(manager));
        commands.put("execute_script", new ExecuteScriptCommand(manager));
        commands.put("head", new HeadCommand(manager));
        commands.put("add_if_min", new AddIfMinCommand(manager));
        commands.put("remove_lower", new RemoveLowerCommand(manager));
        commands.put("sum_of_age", new SumOfAgeCommand(manager));
        commands.put("filter_contains_name", new FilterContainsNameCommand(manager));
    }

    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }

    public void executeCommand(){
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Введите команду: ");
            String command = scanner.nextLine().trim();
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
