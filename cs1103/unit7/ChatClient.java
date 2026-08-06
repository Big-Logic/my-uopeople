import java.io.*;
import java.net.*;

public class ChatClient {

    private static final String SERVER = "localhost";
    private static final int PORT = 5000;

    public static void main(String[] args) {

        try {

            Socket socket = new Socket(SERVER, PORT);

            BufferedReader keyboard =
                    new BufferedReader(new InputStreamReader(System.in));

            BufferedReader serverInput =
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));

            PrintWriter serverOutput =
                    new PrintWriter(socket.getOutputStream(), true);

            System.out.println("=================================");
            System.out.println(" Connected to Chat Server");
            System.out.println(" Type 'exit' to leave");
            System.out.println("=================================");

            // Thread to receive messages
            Thread receive = new Thread(() -> {

                String message;

                try {

                    while ((message = serverInput.readLine()) != null) {
                        System.out.println(message);
                    }

                } catch (IOException e) {

                }

            });

            receive.start();

            String userMessage;

            while (true) {

                userMessage = keyboard.readLine();

                serverOutput.println(userMessage);

                if (userMessage.equalsIgnoreCase("exit")) {
                    break;
                }

            }

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}