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

public class MainScreen extends JFrame implements ActionListener{
    JLabel titleLabel;
    JTextField inputField;
    JButton infoButton;
    JTextArea displayQuote;
    JPanel panel;
    JFrame frame = new JFrame();
    String input = "";
    String quote;
    JTextArea wpm;
    JTextArea accuracy;
    JButton startButton;
    double startTime;
    double endTime;
    JButton redoButton;
    JButton newTestButton;
    
    MainScreen(String q) {
        frame.setTitle("Typing Test Game");
        frame.setSize(new Dimension(900, 600));
        frame.setResizable(true);

        displayQuote = new JTextArea(quote);
        displayQuote.setLineWrap(true);
        displayQuote.setBounds(0, 0, 500, 50);
        displayQuote.setEditable(false);
        frame.add(displayQuote);

        wpm = new JTextArea("WPM:");
        // wpm.setFont(new Font(null));
        wpm.setBounds(150, 400, 100, 50);
        wpm.setEditable(false);
        frame.add(wpm);

        accuracy = new JTextArea("Accuracy:");
        accuracy.setBounds(350, 400, 100, 50);
        frame.add(accuracy);
        
        inputField = new JTextField();
        inputField.setBounds(150, 350, 500, 50);
        inputField.setEnabled(false);
        inputField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                input = inputField.getText();
                if (!input.equals("")) {
                    endTime = LocalTime.now().toNanoOfDay();
                    double duration = (endTime - startTime) / 1000000000;
                    wpm.setText("WPM: " + Functions.calculateDoubleWPM(Functions.numChars(input), duration));
                    accuracy.setText("Accuracy: " + Functions.checkAccuracy(q, input));
                    inputField.setEnabled(false);
                }
            }
        });
        frame.add(inputField);

        infoButton = new JButton();
        infoButton.setBounds(350, 500, 100, 50);
        infoButton.addActionListener(this);
        infoButton.setText("INFO");
        infoButton.setFocusable(false);
        frame.add(infoButton);

        startButton = new JButton();
        startButton.setBounds(500, 200, 100, 50);
        startButton.addActionListener(this);
        startButton.setText("START");
        startButton.setFocusable(false);
        frame.add(startButton);

        redoButton = new JButton();
        redoButton.setBounds(200, 200, 100, 50);
        redoButton.addActionListener(this);
        redoButton.setText("REDO TEST");
        redoButton.setFocusable(false);
        redoButton.setEnabled(false);
        frame.add(redoButton);

        newTestButton = new JButton();
        newTestButton.setBounds(350, 300, 100, 50);
        newTestButton.addActionListener(this);
        newTestButton.setText("NEW TEST");
        newTestButton.setFocusable(false);
        frame.add(newTestButton);
        
        // panel = new JPanel();
        // panel.setBounds(150, 300, 500, 50);
        // panel.setBackground(Color.RED);
        // quote = new JLabel(q);
        // Font font = new Font("Arial", Font.BOLD, 24);
        // quote.setFont(font);
        // quote.setForeground(Color.BLUE);
        // quote.setBounds(150, 300, 500, 50);
        // panel.add(quote);
        // frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void pressedEnter() {
        inputField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                input = inputField.getText();
                if (!input.equals("")) {
                    calculate();
                }
            }
        });
    }


    public void calculate() {
        System.out.println(Functions.calculateDoubleWPM(Functions.numChars(input), 10));
        System.out.println(Functions.checkAccuracy(displayQuote.getText(), input));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == infoButton) {
            new InfoButton();
        }
        if (e.getSource() == startButton) {
            inputField.setEnabled(true);
            inputField.setText("");
            redoButton.setEnabled(true);
            startTime = LocalTime.now().toNanoOfDay();
        }
        if (e.getSource() == redoButton) {
            inputField.setText("");
            startTime = LocalTime.now().toNanoOfDay();
        }
        if (e.getSource() == newTestButton) {
            quote = Functions.getRandomQuote(null);
            inputField.setText("");
        }
    }
}
