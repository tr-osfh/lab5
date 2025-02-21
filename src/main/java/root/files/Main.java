package root.files;

import root.files.collection.CollectionManagerRecever;
import root.files.commands.CommandManager;
import root.files.file.FileWriterManager;
import root.files.seClasses.Dragon;

import static root.files.console.Reader.*;

public class Main {
    public static void main(String[] args) {
        try {
            //String filePath = System.getenv("DB_FILE_PATH");
            String filePath = "/Users/sch/Yandex.Disk.localized/личные_файлы/itmo/proga_itmo/lab5New2/src/main/java/root/files/dragonsDB.csv";
            FileWriterManager fm = new FileWriterManager(filePath);
            CollectionManagerRecever cmr = new CollectionManagerRecever();
            CommandManager cm = new CommandManager(cmr);
            cmr.setCommandManager(cm);
            cmr.setFileManager(fm);
            cmr.setFileName(filePath);
            cmr.setDragons(fm.loadCSV());
            for (;;){
                cm.executeCommand();
            }
        } catch (NullPointerException e) {
            System.out.println("Файл не найден.");
            e.getStackTrace();
        }
    }
}