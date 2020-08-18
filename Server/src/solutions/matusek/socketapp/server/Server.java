package solutions.matusek.socketapp.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Server {

    private final static int PORT = 9841;

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                String message = inputToStream(clientSocket.getInputStream());
                System.out.println(message);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            Thread.sleep(500);
        }
    }

    private static String inputToStream(InputStream inputStream) {
        if (inputStream == null) {
            return "";
        }

        try {
            StringBuilder textBuilder = new StringBuilder();
            try (Reader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
                int c = 0;
                while ((c = reader.read()) != -1) {
                    textBuilder.append((char) c);
                }
            }
            return textBuilder.toString();
        } catch (IOException e) {
            return "";
        }
    }
}