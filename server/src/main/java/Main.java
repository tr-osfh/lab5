import collection.CollectionManager;
import connection.Server;
import file.FileManager;
import seClasses.Dragon;

import java.util.PriorityQueue;

public class Main {
    private final static Integer serverPort = 2213;
    static FileManager fm = new FileManager(System.getenv("DB_FILE_PATH"));
    public static void main(String[] args) {
        PriorityQueue<Dragon> collection;
        try {
            collection = fm.loadCSV();
        } catch (Exception e) {
            System.out.println("Файл не найден.");
            collection = new PriorityQueue<>();
        }
        CollectionManager cm = new CollectionManager();
        cm.setDragons(collection);
        Server server = new Server(serverPort, fm, cm);
        server.run();
    }
}
