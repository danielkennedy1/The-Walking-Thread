package util;

import java.io.IOException;

public enum Command {
    REVERSE,
    JOKE,
    QUOTE,
    KILL,
    EXIT;
    public static Command getCommand(String command) {
        return switch (command) {
            case "/reverse" -> REVERSE;
            case "/kill" -> KILL;
            case "/exit" -> EXIT;
            case "/joke" -> JOKE;
            case "/quote" -> QUOTE;
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
        }
        return result;
    }
}
