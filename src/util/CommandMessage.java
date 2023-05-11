package util;

public class CommandMessage extends Message{

    private final Command command;

    public CommandMessage(String serverAddress, int serverPort, String clientAddress, int clientPort, String clientMacAddress, String content, Command command){
        super(serverAddress, serverPort, clientAddress, clientPort, clientMacAddress, content);
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return Command.getCommandString(command);
    }
    public String getOperand(){
        return super.toString();
    }
}
