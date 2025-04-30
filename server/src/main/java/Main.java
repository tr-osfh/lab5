import collection.CollectionManager;
import console.ConsoleManager;
import file.FileManager;
import seClasses.Dragon;
import connection.Server;

import java.util.PriorityQueue;


public class Main {
    private static final ConsoleManager consoleManager = new ConsoleManager();
    private final static Integer serverPort = 21213;
    static FileManager fm = new FileManager(System.getenv("DB_FILE_PATH"), consoleManager);
    public static void main(String[] args) {
        PriorityQueue<Dragon> collection;
        try {
            collection = fm.loadCSV();
        } catch (Exception e) {
            consoleManager.write("Файл не найден, завершение...");
            System.exit(0);
            collection = new PriorityQueue<>();
        }
        CollectionManager cm = new CollectionManager();
        cm.setDragons(collection);
        Server server = new Server(serverPort, fm, consoleManager);
        server.run();
    }
}
