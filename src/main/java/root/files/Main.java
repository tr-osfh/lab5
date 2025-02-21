package root.files;

import root.files.collection.CollectionManager;
import root.files.commands.CommandManager;
import root.files.file.FileWriterManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            String filePath = System.getenv("DB_FILE_PATH");
            FileWriterManager fm = new FileWriterManager(filePath);
            CollectionManager receiver = new CollectionManager();
            CommandManager cm = new CommandManager(receiver);
            receiver.setCommandManager(cm);
            receiver.setFileManager(fm);
            receiver.setFileName(filePath);
            receiver.setDragons(fm.loadCSV());
            for (;;){
                cm.executeCommand();
            }
        } catch (NullPointerException e) {
            System.out.println("Файл не найден.");
        } catch (Exception e){
            System.out.println("Неизвестная ошибка");
            e.printStackTrace();
        }
    }
}