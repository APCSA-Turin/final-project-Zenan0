package com.example;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalTime;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.Border;
import java.io.InputStream;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.json.JSONArray;
import org.json.JSONObject;

public class InfoButton{
    InfoButton() {
        JOptionPane.showMessageDialog(null, "This is the typing test game, where you can test your wpm and accuracy.\nType in the text box and match the words shown above.", "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
