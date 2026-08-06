import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private String userId;

    public ClientHandler(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;

        try {
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            output = new PrintWriter(
                    socket.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        ChatServer.broadcast("*** " + userId + " joined the chat ***");

        try {
            String message;

            while ((message = input.readLine()) != null) {

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                ChatServer.broadcast(userId + ": " + message);
            }

        } catch (IOException e) {

        } finally {

            ChatServer.removeClient(this);

            ChatServer.broadcast("*** " + userId + " left the chat ***");

            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }

    public void sendMessage(String message) {
        output.println(message);
    }
}