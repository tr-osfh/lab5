import connection.Client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;

public class Main {

    private static final int serverPort = 21213;

    public static void main(String[] args) {
        Client client = new Client("se.ifmo.ru", serverPort);
        try {
            System.out.println("Для получения справки по доступным командам введите help");
            client.run();
        } catch (NoSuchElementException e) {
            System.out.println("");
        }
    }
}