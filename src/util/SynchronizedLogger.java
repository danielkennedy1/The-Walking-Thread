package util;
import java.net.Socket;
import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.IOException;

public class SynchronizedLogger implements Runnable{

    private Socket clientSocket;
    private Message messageIn;
    private String responseContent;
    private String clientName;

    public SynchronizedLogger(Socket clientSocket, Message messageIn, String responseContent){
        this.clientSocket = clientSocket ;
        this.messageIn = messageIn;
        this.responseContent = responseContent;
    }

    @Override
    public synchronized void run() {
        try {
            FileWriter writer = new FileWriter("src/util/logger.txt", true);
            String stringSocketAddress = String.valueOf(clientSocket.getLocalSocketAddress());
            LocalDateTime logTime = LocalDateTime.now();
            String logString = String.valueOf(logTime);
            String text;
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
