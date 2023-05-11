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

        boolean running = true;

        while (running) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected from " + clientSocket.getInetAddress());

            ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
            try {
                Message messageIn = (Message) inputStream.readObject();
                System.out.println("Message received from " + messageIn.getClientAddress() + ":" + messageIn.getClientPort());
                System.out.println("Message content: " + messageIn);
                if(messageIn.toString().equals("kill")) {
                    running = false;
                }

                //reverse the message and send it back to the client
                ObjectOutput out = new ObjectOutputStream(clientSocket.getOutputStream());
                Message messageOut = new Message(
                        messageIn.getServerAddress(),
                        messageIn.getServerPort(),
                        messageIn.getClientAddress(),
                        messageIn.getClientPort(),
                        messageIn.getClientMacAddress(),
                        new StringBuilder(messageIn.toString()).reverse().toString()
                );
                out.writeObject(messageOut);

                System.out.println("Response sent to " + messageOut.getClientAddress() + ":" + messageOut.getClientPort());
                System.out.println("Response content: " + messageOut);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            clientSocket.close();
        }
    }
}
