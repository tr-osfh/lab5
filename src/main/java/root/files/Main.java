package root.files;

import root.files.collection.CollectionManager;
import root.files.commands.CommandManager;
import root.files.console.ConsoleManager;
import root.files.file.FileManager;

public class Main {
    public static void main(String[] args) {
        try {
            String filePath = System.getenv("DB_FILE_PATH");
            FileManager fm = new FileManager(filePath);
            CollectionManager receiver = new CollectionManager();
            ConsoleManager consoleManager = new ConsoleManager();
            CommandManager cm = new CommandManager(receiver, consoleManager);
            receiver.setCommandManager(cm);
            receiver.setFileManager(fm);
            receiver.setFileName(filePath);
            receiver.setDragons(fm.loadCSV());
            for (;;){
                cm.executeCommand();
            }
        } catch (NullPointerException e) {
            System.out.println("Файл не найден.");
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("Неизвестная ошибка");
            e.printStackTrace();
        }
    }
}