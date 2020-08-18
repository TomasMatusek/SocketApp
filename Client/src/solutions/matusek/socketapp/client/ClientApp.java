package solutions.matusek.socketapp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ClientApp {

    private final static String SERVER_IP = "127.0.0.1";
    private final static int SERVER_PORT = 9841;

    public static void main(String[] args) throws IOException, InterruptedException {

        while (true) {
            System.out.println("");
            System.out.println("Please enter your message:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String message = reader.readLine();

            if (message.equalsIgnoreCase("exit")) {
                break;
            } else {
                sendMessageToServer(message);
            }
        }
    }

    private static void sendMessageToServer(String message) {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            OutputStream output = socket.getOutputStream();
            output.write(message.getBytes());
            output.flush();
            output.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Failed to send message. " + e.getMessage());
        }
    }
}