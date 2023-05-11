package util;

public class Command extends Message{

    private final Commands command;

    public Command(String serverAddress, int serverPort, String clientAddress, int clientPort, String clientMacAddress, String content){
        super(serverAddress, serverPort, clientAddress, clientPort, clientMacAddress, content);
    }
}
