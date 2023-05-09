package util;

import java.io.Serializable;

public class Message implements Serializable {
    public String SERVER_ADDRESS;
    public int SERVER_PORT;
    
    public String CLIENT_ADDRESS;
    public int CLIENT_PORT;
    
    public String CLIENT_MAC_ADDRESS;

    public String CONTENT;
    
    public Message(String serverAddress, int serverPort, String clientAddress, int clientPort, String clientMacAddress, String content) {
        this.SERVER_ADDRESS = serverAddress;
        this.SERVER_PORT = serverPort;
        
        this.CLIENT_ADDRESS = clientAddress;
        this.CLIENT_PORT = clientPort;
        
        this.CLIENT_MAC_ADDRESS = clientMacAddress;

        this.CONTENT = content;
    }
}
