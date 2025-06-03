package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Functions {
    // word is defined as any letter/character separated by spaces
    // returns the number of words in str
    public static int numWords(String str) {
        int counter = 0;
        if (str.length() == 0) {
            return counter;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.substring(i, i + 1).equals(" ")) {
                counter++;
            }
        }
        return counter + 1;
    }

    // returns the number characters between origStr and userStr that match
    public static int numCorrectChars(String origStr, String userStr) {
        int chars = 0;
        if (userStr.length() < origStr.length()) {

            for (int i = 0; i < userStr.length(); i++) {
                if (origStr.substring(i, i + 1).equals(userStr.substring(i, i + 1))) {
                    chars++;
                }
            }

        } else {

            for (int i = 0; i < origStr.length(); i++) {
                if (origStr.substring(i, i + 1).equals(userStr.substring(i, i + 1))) {
                    chars++;
                }
            }

        }

        return chars;
    }

    public static double calculateRawWPM(int numChars, double time) {
        double wpm = (((double) numChars) / 5) / time;
        return wpm * 60;
    }

    // turns str into a list containing the words it has
    public static String[] stringToWords(String str) {
        String[] words = new String[numWords(str)];
        if (words.length == 0) {
            return words;
        }
        int currentWord = 0;
        String tempWords = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.substring(i, i + 1).equals(" ")) {
                words[currentWord] = tempWords;
                currentWord++;
                tempWords = "";
            } else {
                tempWords += str.substring(i, i + 1);
            }
        }
        words[words.length - 1] = tempWords;
        return words;
    }

    public static double checkAccuracy(String originalStr, String userStr) {
        String[] origStrWords = stringToWords(originalStr);
        String[] userStrWords = stringToWords(userStr);
        int numCorrect = 0;
        if (userStrWords.length >= origStrWords.length) {
            for (int i = 0; i < origStrWords.length; i++) {
                if (userStrWords[i].equals(origStrWords[i])) {
                    numCorrect++;
                }
            }
        } else {
            for (int i = 0; i < userStrWords.length; i++) {
                if (userStrWords[i].equals(origStrWords[i])) {
                    numCorrect++;
                }
            }
        }

        return (double) numCorrect / numWords(originalStr) * 100;
    }

    public static JSONObject getRandomQuoteData() throws JSONException, Exception {
        JSONArray quoteArray = new JSONArray(getData("https://zenquotes.io/api/random"));
        JSONObject obj = quoteArray.getJSONObject(0);
        return obj;
    }

    public static String getRandomQuote(JSONObject obj) {
        String quote = obj.getString("q");
        // while loop is used to remove any dead spaces found at the end of a quote
        while (quote.substring(quote.length() - 1).equals(" ")) {
            quote = quote.substring(0, quote.length() - 1);
        }
        return quote;
    }

    public static String getQuoteAuthor(JSONObject obj) {
        return obj.getString("a");
    }

    public static String getQuoteChars(JSONObject obj) {
        return obj.getString("c");
    }

    public static String getData(String endpoint) throws Exception {
        /*endpoint is a url (string) that you get from an API website*/
        URL url = new URL(endpoint);
        /*connect to the URL*/
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        /*creates a GET request to the API.. Asking the server to retrieve information for our program*/
        connection.setRequestMethod("GET");
        /* When you read data from the server, it wil be in bytes, the InputStreamReader will convert it to text. 
        The BufferedReader wraps the text in a buffer so we can read it line by line*/
        BufferedReader buff = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;//variable to store text, line by line
        /*A string builder is similar to a string object but faster for larger strings, 
        you can concatenate to it and build a larger string. Loop through the buffer 
        (read line by line). Add it to the stringbuilder */
        StringBuilder content = new StringBuilder();
        while ((inputLine = buff.readLine()) != null) {
            content.append(inputLine);
        }
        buff.close(); //close the bufferreader
        connection.disconnect(); //disconnect from server 
        return content.toString(); //return the content as a string
    }
}
