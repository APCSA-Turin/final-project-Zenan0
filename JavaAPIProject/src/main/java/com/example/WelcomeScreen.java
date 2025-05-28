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

public class WelcomeScreen extends JFrame{
    JLabel label;
    JButton button;
    Border border;

    WelcomeScreen() {
        this.setTitle("Typing Test Game");
        this.setResizable(false);
        this.setSize(500, 500);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel("Welcome to the typing test game! To get started, press the button below!");
        label.setBounds(0, 0, 500, 500);
        this.add(label);

        // button = new JButton();
        // button.setBounds(0, 0, 100, 50);
        // this.add(button);

        this.setVisible(true);
    }
}
