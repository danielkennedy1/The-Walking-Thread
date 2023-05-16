package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import util.*;

public class Connection implements Runnable {
    private final Socket clientSocket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private boolean terminateFlag = false;
    public Connection(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            output = new ObjectOutputStream(clientSocket.getOutputStream());
            input = new ObjectInputStream(clientSocket.getInputStream());

            boolean running = true;

            // Handle client requests
            while (running) {
                // Read message from client
                Message messageIn = (Message) input.readObject();
                String responseContent;

                // Check message type and handle accordingly
                switch (messageIn.getClass().getSimpleName()) {
                    case "Chat":
                        System.out.println(((Chat) messageIn).getSenderName() + ": " + messageIn);
                        responseContent = ((Chat) messageIn).getSenderName() + ": " + messageIn;
                        break;
                    case "CommandMessage":
                        responseContent = processCommand(((CommandMessage) messageIn).getCommand(), ((CommandMessage) messageIn).getOperand());
                        break;
                    default:
                        System.err.println("Unknown message type received");
                        System.err.println(messageIn);
                        responseContent = "Unknown message type received";
                        break;

                }

                if(responseContent.equals("Exiting server")){
                    running = false;
                }

                if(responseContent.equals("Killing server")){
                    running = false;
                    terminateFlag = true;
                }

                // Send response to client
                output.writeObject(new Chat(
                        messageIn.getServerAddress(),
                        messageIn.getServerPort(),
                        messageIn.getClientAddress(),
                        messageIn.getClientPort(),
                        messageIn.getClientMacAddress(),
                        responseContent,
                        "Server"
                ));
                output.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            if (e.getMessage().equals("Connection reset")) {
                System.out.println("Client disconnected");
            } else {
                System.err.println("Error handling client connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
        finally {
            // Clean up resources
            try {
                output.close();
                input.close();
                clientSocket.close();
                if(terminateFlag){
                    System.exit(0);
                }
            } catch (IOException e) {
                System.err.println("Error closing client connection: " + e.getMessage());
            }
        }
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
            case EXIT:
                result = "Exiting server";
                break;
        }
        return result;
    }
}
