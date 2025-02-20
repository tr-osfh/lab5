package root.files.commands;


import root.files.collection.CollectionManagerRecever;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

/// Invoker
public class CommandManager {

    private static HashMap<String, Command> commands = new HashMap<>();

    public CommandManager(CollectionManagerRecever manager){
        commands.put("add", new AddCommand(manager));
        commands.put("show", new ShowCommand(manager));
        commands.put("save", new SaveCommand(manager));
    }




    private Command command;

    public void setCommand(Command command){
        this.command = command;
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


}
