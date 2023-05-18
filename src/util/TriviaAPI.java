package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

// This class contains three methods. getTriviaQuestion, getQuestion and getCorrectAnswer
// getTriviaQuestion: calls to api, receives a json string parses it and extracts the question and answer
// getQuestion and getCorrectAnswer return a question and answer
// Note: we have filtered the API so that it returns computer science related questions
public class TriviaAPI {

    private static String question = "";
    private static String correctAnswer = "";


    public static void getTriviaQuestion() {
        // URL link to API
        String apiUrl = "https://opentdb.com/api.php?amount=1&category=18&difficulty=medium";
        StringBuilder response = new StringBuilder();

        // try to establish connection to the URL and send a GET request
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // get response code to verify content was successfully received
            int responseCode = connection.getResponseCode();
            // if response code is ok, then read the response in
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
            }
            // close connection when finished reading
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // using regex, create a pattern to look for in the response
        Pattern questionPattern = Pattern.compile("\"question\":\"([^\"]+)\"" );
        // check for regex matches between pattern and response
        Matcher questionMatcher = questionPattern.matcher(response);
        if (questionMatcher.find()) {
            question = questionMatcher.group(1);
        }

        // repeat the step above, but this time for the correct answer instead of the question
        Pattern answerPattern = Pattern.compile("\"correct_answer\":\"([^\"]+)\"" );
        Matcher answerMatcher = answerPattern.matcher(response);
        if (answerMatcher.find()) {
            correctAnswer = answerMatcher.group(1);
        }
    }

    public static String getQuestion(){
        return question;
    }

    public static String getCorrectAnswer(){
        return correctAnswer;
    }
}