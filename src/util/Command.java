package util;

import java.io.IOException;

public enum Command {
    REVERSE("/reverse will reverse the proceeding string"),
    JOKE("/joke will tell you a funny dad joke"),
    QUOTE("/quote will tell you a famous quote"),
    KILL("/kill will stop the server from running"),
    HELP("/help will print descriptions of all command words"),
    EXIT("/exit will stop a clients session with the server");

    private final String description;

    Command(String description) {
        this.description = description;
    }
    public static Command getCommand(String command) {
        return switch (command) {
            case "/reverse" -> REVERSE;
            case "/kill" -> KILL;
            case "/exit" -> EXIT;
            case "/joke" -> JOKE;
            case "/quote" -> QUOTE;
            case "/help" -> HELP;
            default -> null;
        };
    }

    public static String getCommandString(Command command) {
        return switch (command) {
            case REVERSE -> "/reverse";
            case KILL -> "/kill";
            case EXIT -> "/exit";
            case JOKE -> "/joke";
            case QUOTE -> "/quote";
            case HELP -> "/help";
        };
    }
    public static String processCommand(Command command, String operand) throws IOException {
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
            case JOKE:
                result = DadJokeService.getJoke();
                break;
            case QUOTE:
                result = QuotesAPI.getQuote();
                break;
            case HELP:
                result = getDescription();
                break;
        }
        return result;
    }

    public static String getDescription() {
        System.out.println("getdesc called");
        StringBuilder stringBuilderOfDescriptions = new StringBuilder("");
        for (Command command : Command.values()) {
            stringBuilderOfDescriptions.append(command + ": " + command.description + "\n");
        }
        String stringOfDescriptions = stringBuilderOfDescriptions.toString();
        System.out.println(stringOfDescriptions);
        return stringOfDescriptions;
    }
}
