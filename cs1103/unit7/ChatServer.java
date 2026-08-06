import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ChatServer {

    private static final int PORT = 5000;

    // Stores all connected clients
    private static final Set<ClientHandler> clients =
            ConcurrentHashMap.newKeySet();

    private static int userCounter = 1;

    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println(" Chat Server Started");
        System.out.println(" Listening on port " + PORT);
        System.out.println("=================================");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {
                Socket socket = serverSocket.accept();

                String userId = "User-" + userCounter++;

                ClientHandler client =
                        new ClientHandler(socket, userId);

                clients.add(client);

                new Thread(client).start();

                System.out.println(userId + " connected.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Broadcast message to every client
    public static void broadcast(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    // Remove disconnected client
    public static void removeClient(ClientHandler client) {
        clients.remove(client);
    }
}