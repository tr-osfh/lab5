package connection;//package connection;

import collection.ServerLogger;
import commands.Command;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import collection.CollectionManager;
import commands.CommandSerializer;

import java.net.Socket;


public class ClientHandler extends Thread {
    private final Socket clientSocket;
    private final CollectionManager collectionManager;

    public ClientHandler(Socket socket, CollectionManager manager) {
        this.clientSocket = socket;
        this.collectionManager = manager;
    }

    @Override
    public void run() {
        try (
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())
        ) {
            while (true) {
                // Получение команды
                Command command = (Command) in.readObject(); // Только один readObject()
                System.out.println("[Сервер] Получена команда: " + command.toString());
                Response response = command.execute();
                System.out.println(response.getResponse());
                out.writeObject(response);
                out.flush();
                clientSocket.close();
            }
        } catch (EOFException e) {
            ServerLogger.getLogger().info("Клиент отключился: " + clientSocket.getRemoteSocketAddress());
        } catch (IOException | ClassNotFoundException e) {
            ServerLogger.getLogger().warning("Ошибка обработки команды: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                ServerLogger.getLogger().warning("Ошибка закрытия сокета: " + e.getMessage());
            }
        }
    }
}