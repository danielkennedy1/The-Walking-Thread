package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import google.gson.Gson;
//import google.gson.JsonObject;


public class QuotableAPI {
    public static String getQuote(){

        try {
            URL url = new URL("https://api.quotable.io/random");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response from the API
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

//                Gson gson = new Gson();
//                JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
//
//                // Extract content and author
//                String content = jsonObject.get("content").getAsString();
//                String author = jsonObject.get("author").getAsString();
                return response.toString();
            } else {
                System.out.println("Error: " + responseCode);
            }

            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Error: Quote not found";
    }

}