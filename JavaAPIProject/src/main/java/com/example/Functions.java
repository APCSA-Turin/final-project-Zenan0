package com.example;

public class Functions {
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

    public static int calculateWPM(int numWords, double time) {
        double wpm = (double) numWords / time;
        return (int) (wpm * 60);
    }

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
}
