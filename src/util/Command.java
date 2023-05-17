package util;

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
}
