package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
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
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            clientSocket.close();
        }
    }
}
