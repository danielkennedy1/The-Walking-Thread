package util;

public enum Command {
    REVERSE,
    KILL;

    public static Command getCommand(String command) {
        return switch (command) {
            case "/reverse" -> REVERSE;
            case "/kill" -> KILL;
            default -> null;
        };
    }

    public static String getCommandString(Command command) {
        return switch (command) {
            case REVERSE -> "/reverse";
            case KILL -> "/kill";
        };
    }
}
