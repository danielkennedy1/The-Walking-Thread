package test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import util.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommandTest {

    @ParameterizedTest(name = "{index} => command=''{0}''")
    @ValueSource(strings = {"/reverse", "/kill", "/exit", "/joke", "/quote", "/help", "/question", "/answer"})
    void testGetCommandString(String commandString) {
        Command command = Command.getCommand(commandString);
        assertEquals(commandString, Command.getCommandString(command));
    }

    @ParameterizedTest
    @CsvSource({
            "hello, olleh",
            "12345, 54321",
            "123456789, 987654321",
            "abcde, edcba",
            "You get the idea, aedi eht teg uoY",
    })
    public void testStringReverse(String input, String expected) throws IOException {
        String actual = Command.processCommand(Command.REVERSE, input);
        assertEquals(expected, actual);
    }
}
