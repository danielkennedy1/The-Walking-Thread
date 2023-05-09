package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import util.Message;

public class Main {

    public static void main(String[] args) throws IOException {
        int port = 12345;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server listening on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected from " + clientSocket.getInetAddress());

            ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
            Message message = null;
            try {
                message = (Message) inputStream.readObject();
                System.out.println("Message received from " + message.CLIENT_ADDRESS + ":" + message.CLIENT_PORT);
                System.out.println("Message content: " + message.CONTENT);
                //reverse the message and send it back to the client
                ObjectOutput out = new ObjectOutputStream(clientSocket.getOutputStream());
                message.CONTENT = new StringBuilder(message.CONTENT).reverse().toString();
                out.writeObject(message);
                System.out.println("Response sent to " + message.CLIENT_ADDRESS + ":" + message.CLIENT_PORT);
                System.out.println("Response content: " + message.CONTENT);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            clientSocket.close();
        }
    }
}
