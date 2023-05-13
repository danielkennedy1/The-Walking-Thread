package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import util.*;

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
                String responseContent = "";
                switch (messageIn.getClass().getSimpleName()) {
                    case "Chat":
                        System.out.println(((Chat) messageIn).getSenderName() + ": " + messageIn);
                        responseContent = "You sent a chat!";
                        break;
                    case "CommandMessage":
                        System.out.println("Command received: " + ((CommandMessage) messageIn).getCommand());
                        responseContent = processCommand(((CommandMessage) messageIn).getCommand(), ((CommandMessage) messageIn).getOperand());
                        break;
                    default:
                        System.out.println("Unknown message type received");
                        responseContent = "Unknown message type received";
                        break;

                }
                //return response
                ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                outputStream.writeObject(new Chat(
                        messageIn.getServerAddress(),
                        messageIn.getServerPort(),
                        messageIn.getClientAddress(),
                        messageIn.getClientPort(),
                        messageIn.getClientMacAddress(),
                        responseContent,
                        "Server"
                        ));
                if(responseContent.equals("Killing server")){
                    running = false;
                }
                clientSocket.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

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
                result = new QuotableAPI().toString();
                break;
            }

        return result;
    }
}
