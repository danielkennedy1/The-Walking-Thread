package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TriviaAPI {

    private static String question = "";
    private static String correctAnswer = "";


    public static void getTriviaQuestion() {
        String apiUrl = "https://opentdb.com/api.php?amount=1&category=18&difficulty=medium";
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pattern questionPattern = Pattern.compile("\"question\":\"([^\"]+)\"" );
        Matcher questionMatcher = questionPattern.matcher(response);
        if (questionMatcher.find()) {
            question = questionMatcher.group(1);
        }

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