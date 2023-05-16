package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
        int port = 12345;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server listening on port " + port);

        boolean running = true;

        while (running) {
            Socket clientSocket = serverSocket.accept();
            try {
                System.out.println("Client connected from " + clientSocket.getInetAddress());
                Connection connection = new Connection(clientSocket);
                Thread thread = new Thread(connection);
                thread.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
