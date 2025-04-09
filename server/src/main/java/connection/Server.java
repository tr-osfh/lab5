package connection;

import collection.CollectionManager;
import collection.ServerLogger;
import file.FileManager;
import file.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {

    private final int port;;
    private CollectionManager cm;
    private FileManager fm;
    public Server(int port, FileManager fileManager, CollectionManager cm) {
        this.fm = fileManager;
        this.port = port;
    }
    public void run(){
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            ServerLogger.getLogger().info("Сервер запущен на порте " + port);

            new Thread(() -> {
                try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
                    while (true) {
                        if (consoleReader.ready()) {
                            String line = consoleReader.readLine();
                            if (line.equals("exit")) {
                                fm.saveCSV(CollectionManager.getDragons());
                                System.exit(0);
                            }
                        }
                    }
                } catch (IOException e) {
                    ServerLogger.getLogger().warning("Ошибка чтения с консоли: " + e.getMessage());
                }
            }).start();
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    new ClientHandler(clientSocket, cm).start();
                } catch (IOException e) {
                    ServerLogger.getLogger().warning("Ошибка при подключении клиента: " + e.getMessage());
                }
            }
        } catch (IOException e){
            ServerLogger.getLogger().severe("Ошибка запуска сервера: " + e.getMessage());
        }
    }
}
