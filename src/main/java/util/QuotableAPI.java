package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class QuotableAPI {
    public static String getQuote(){
        // get a connection to the api
        try {
            URL url = new URL("https://api.quotable.io/random");

            // making a connection to API URL and sending a get request
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // get response code: this indicates the outcome of the HTTP request
            // if response code value is in 200s, the HTTP request succeeded
            int responseCode = connection.getResponseCode();

            // verify response code is successful and if successful
            // use a bufferedReader to take in the input from the connection
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response from the API
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                // continue adding lines to a string builder called response
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Regex patterns that we want to use to get the name of author/ content of quote
                String regexGetAuthor = "\"author\":\"([^\"]+)\"";
                String regexGetQuote = "\"content\":\"([^\"]+)\"";

                // creates a pattern object from the regex
                Pattern patternAuthor = Pattern.compile(regexGetAuthor);
                Pattern patternQuote = Pattern.compile(regexGetQuote);

                // matches the regex pattern created against the response string builder from the API
                Matcher matcherAuthor = patternAuthor.matcher(response);
                Matcher matcherQuote = patternQuote.matcher(response);

                String authorValue = null;
                String quoteValue = null;

                if (matcherAuthor.find()){
                    // Extract the value corresponding to the "author" field
                    authorValue = matcherAuthor.group(1);
                }
                else {
                    System.out.println("Author field not found.");
                }
                if (matcherQuote.find()) {
                    // Extract the value corresponding to the "author" field
                    quoteValue = matcherQuote.group(1);
                }
                else {
                    System.out.println("Author field not found.");
                }

            return (authorValue + ": " + quoteValue);
            } else {
                System.out.println("Error: " + responseCode);
            }

            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "error, api not found";
    }
}