package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        int port = 12345;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
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
                    running = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
<<<<<<< HEAD:src/main/java/server/Main.java

    public void processCommand(Command command){
        processCommand(command, "");
    }
    static String processCommand(Command command, String operand){
        String result = "";
        switch (command){
            case KILL:
                result = "Killing server";
                break;
            case REVERSE:
                result = new StringBuilder(operand).reverse().toString();
                break;
            case QUOTE:
                result = QuotableAPI.getQuote();
                break;
            }

        return result;
    }
=======
>>>>>>> ad189d3e2871fff1486598f57abe9769b830966b:src/server/Main.java
}
