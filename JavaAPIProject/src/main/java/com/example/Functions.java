package com.example;

public class Functions {
    public static int numWords(String str) {
        int counter = 1;
        if (str.indexOf(" ") == -1) {
            return counter;
        }
        String newStr = str.substring(str.indexOf(" "));
        while (newStr.indexOf(" ") != -1) {
            counter++;
            newStr = newStr.substring(newStr.indexOf(" ") + 1);
        }
        return counter;
    }
}
