package util;
import java.net.Socket;
import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.IOException;

public class SynchronizedLogger implements Runnable{

    //attributes we want to be passed into constructor, which will be logged
    private Socket clientSocket;
    private Message messageIn;
    private String responseContent;

    public SynchronizedLogger(Socket clientSocket, Message messageIn, String responseContent){
        this.clientSocket = clientSocket ;
        this.messageIn = messageIn;
        this.responseContent = responseContent;
    }

    // synchronized method run so that only one thread can write to the logger.txt file at a time
    @Override
    public synchronized void run() {
        try {
            // create a file writer which will write to logger.txt
            FileWriter writer = new FileWriter("src/util/logger.txt", true);
            // convert local socket address of client to a string
            String stringSocketAddress = String.valueOf(clientSocket.getLocalSocketAddress());
            // find time when log is happening
            LocalDateTime logTime = LocalDateTime.now();
            String logString = String.valueOf(logTime);
            String text;
            // check if the message in is a chat or a command
            // if the message is a chat, we want to return the sender name
            // if the message is a command, we want to return the command
            switch (messageIn.getClass().getSimpleName()) {
                case "Chat":
                    String sender = ((Chat) messageIn).getSenderName();
                    text = stringSocketAddress + " " + sender + " " + responseContent + " " + logString + "\n";
                    writer.write(text);
                    break;
                case "CommandMessage":
                    String command = ((CommandMessage) messageIn).toString();
                    text = stringSocketAddress + " " + command + " " + responseContent + " time: " + logString + "\n";
                    writer.write(text);
                    break;
                default:
                    System.err.println("Unknown message type received");
                    break;
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
