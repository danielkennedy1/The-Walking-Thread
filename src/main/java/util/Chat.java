package util;

public class Chat extends Message {

    private final String senderName;

    public Chat(String serverAddress, int serverPort, String clientAddress, int clientPort, String clientMacAddress, String content, String senderName){
        super(serverAddress, serverPort, clientAddress, clientPort, clientMacAddress, content);
        this.senderName = senderName;
    }

    public String getSenderName(){
        return senderName;
    }
}
