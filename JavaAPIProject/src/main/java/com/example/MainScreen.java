package com.example;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalTime;
import java.util.Scanner;

import javax.swing.*;
import java.io.InputStream;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainScreen extends JFrame{
    JLabel titleLabel;
    JTextField inputField;
    JButton fetchButton;
    JTextArea outputArea;
    JPanel panel;
    
    MainScreen() {
        this.setTitle("Typing Test Game");
        this.setSize(1200, 800);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel("Typing Test", SwingConstants.CENTER);
        inputField = new JTextField();
        fetchButton = new JButton("Fetch Data");
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        panel = new JPanel(new GridLayout(4, 1));
        panel.add(titleLabel);
        panel.add(inputField);
        panel.add(fetchButton);
        panel.add(outputArea);

        this.add(panel);

        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = inputField.getText().trim(); //city is in reference to my example
                if (!city.isEmpty()) {
                    // Placeholder for data — replace with real API call 
                    String result = "You searched for: " + city;
                    outputArea.setText(result);
                } else {
                    outputArea.setText("Please enter a city.");
                }
            }
        });

        this.setVisible(true);
    }
}
