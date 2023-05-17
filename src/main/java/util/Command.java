package util;

public enum Command {

    REVERSE,
<<<<<<< HEAD:src/main/java/util/Command.java
    KILL,
    QUOTE;


=======
    JOKE,
    KILL,
    EXIT;
>>>>>>> ad189d3e2871fff1486598f57abe9769b830966b:src/util/Command.java
    public static Command getCommand(String command) {
        return switch (command) {
            case "/reverse" -> REVERSE;
            case "/quote" -> QUOTE;
            case "/kill" -> KILL;
            case "/exit" -> EXIT;
            case "/joke" -> JOKE;
            default -> null;
        };
    }

    public static String getCommandString(Command command) {
        return switch (command) {
            case REVERSE -> "/reverse";
            case KILL -> "/kill";
<<<<<<< HEAD:src/main/java/util/Command.java
            case QUOTE -> "/quote";
=======
            case EXIT -> "/exit";
            case JOKE -> "/joke";
>>>>>>> ad189d3e2871fff1486598f57abe9769b830966b:src/util/Command.java
        };
    }
}
