package util;
import java.net.Socket;
import java.time.LocalTime;
import java.io.FileWriter;
import java.io.IOException;

public class SynchronizedLogger implements Runnable{

    private Socket clientSocket;
    private String messageIn;
    private String responseContent;
    private String clientName;

    public SynchronizedLogger(Socket clientSocket, String messageIn, String responseContent/*, String clientName*/){
        this.clientSocket = clientSocket ;
        this.messageIn = messageIn;
        this.responseContent = responseContent;
//        this.clientName = clientName;
    }

    @Override
    public synchronized void run() {
        try {
            FileWriter writer = new FileWriter("src/util/logger.txt", true);
            String stringSocketAddress = String.valueOf(clientSocket.getLocalSocketAddress());
            String text = stringSocketAddress + " " + messageIn + " " + responseContent +"\n";
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
