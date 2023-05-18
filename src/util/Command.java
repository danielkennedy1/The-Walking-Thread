package util;

import java.io.IOException;

import static util.TriviaAPI.*;

public enum Command {
    REVERSE("/reverse will reverse the proceeding string"),
    JOKE("/joke will tell you a funny dad joke"),
    QUOTE("/quote will tell you a famous quote"),
    KILL("/kill will stop the server from running"),
    HELP("/help will print descriptions of all command words"),
    EXIT("/exit will stop a clients session with the server"),
    QUESTION("/question will get a computer science related question"),
    ANSWER("/answer will get the answer of the question");

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
            case "/question" -> QUESTION;
            case "/answer" -> ANSWER;
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
            case QUESTION -> "/question";
            case ANSWER -> "/answer";
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
            case QUESTION:
                getTriviaQuestion();
                result = getQuestion();
                break;
            case ANSWER:
                result = getCorrectAnswer();
                break;
        }
        return result;
    }

    public static String getDescription() {
        StringBuilder stringBuilderOfDescriptions = new StringBuilder("");
        for (Command command : Command.values()) {
            stringBuilderOfDescriptions.append(command + ": " + command.description + "\n");
        }
        String stringOfDescriptions = stringBuilderOfDescriptions.toString();
        System.out.println(stringOfDescriptions);
        return stringOfDescriptions;
    }
}
