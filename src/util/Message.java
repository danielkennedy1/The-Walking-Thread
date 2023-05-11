package util;

import java.io.Serializable;

public class Message implements Serializable {
    private final String serverAddress;
    private final int serverPort;
    
    private final String clientAddress;
    private final int clientPort;
    
    private final String clientMacAddress;

    private final String content;
    
    public Message(String serverAddress, int serverPort, String clientAddress, int clientPort, String clientMacAddress, String content) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        
        this.clientAddress = clientAddress;
        this.clientPort = clientPort;
        
        this.clientMacAddress = clientMacAddress;

        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public int getServerPort() {
        return serverPort;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public int getClientPort() {
        return clientPort;
    }

    public String getClientMacAddress() {
        return clientMacAddress;
    }
}
