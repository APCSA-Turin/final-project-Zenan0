package com.example;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainScreen extends JFrame implements ActionListener{
    JLabel titleLabel;

    JTextField inputField;

    JFrame frame = new JFrame();

    String input = "";
    String quote;
    String author;

    JTextArea displayQuote;
    JTextArea wpm;
    JTextArea accuracy;
    JTextArea displayAuthor;

    JButton infoButton;
    JButton startButton;
    JButton redoButton;
    JButton newTestButton;
    JButton statsButton;
    
    double startTime;
    double endTime;

    JSONObject quoteData;

    ArrayList<Double> wpmScores;
    ArrayList<Double> accuracyScores;
    
    MainScreen() throws JSONException, Exception {
        frame.setTitle("Typing Test Game");
        frame.setSize(new Dimension(1000, 700));
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setResizable(false);

        titleLabel = new JLabel("TYPING TEST GAME");
        titleLabel.setBounds(200, 40, 700, 60);
        titleLabel.setFont(new Font("Monospaced", Font.ITALIC, 60));
        titleLabel.setForeground(Color.WHITE);
        frame.add(titleLabel);

        quoteData = Functions.getRandomQuoteData();
        wpmScores = new ArrayList<>();
        accuracyScores = new ArrayList<>();

        quote = Functions.getRandomQuote(quoteData);
        displayQuote = new JTextArea(quote);
        displayQuote.setLineWrap(true);
        displayQuote.setBounds(100, 200, 800, 80);
        displayQuote.setEditable(false);
        displayQuote.setFont(new Font("Consolas", Font.PLAIN, 20));
        displayQuote.setBackground(Color.DARK_GRAY);
        displayQuote.setForeground(Color.WHITE);
        frame.add(displayQuote);

        wpm = new JTextArea("WPM:");
        wpm.setFont(new Font("Monospaced", Font.BOLD, 30));
        wpm.setBackground(Color.DARK_GRAY);
        wpm.setForeground(Color.WHITE);
        wpm.setBounds(200, 340, 200, 50);
        wpm.setEditable(false);
        frame.add(wpm);

        accuracy = new JTextArea("Accuracy:");
        accuracy.setFont(new Font("Monospaced", Font.BOLD, 30));
        accuracy.setBackground(Color.DARK_GRAY);
        accuracy.setForeground(Color.WHITE);
        accuracy.setBounds(550, 340, 300, 50);
        accuracy.setEditable(false);
        frame.add(accuracy);

        author = Functions.getQuoteAuthor(quoteData);
        displayAuthor = new JTextArea("Author:");
        displayAuthor.setBounds(200, 390, 600, 50);
        displayAuthor.setFont(new Font("Monospaced", Font.BOLD, 30));
        displayAuthor.setBackground(Color.DARK_GRAY);
        displayAuthor.setForeground(Color.WHITE);
        displayAuthor.setEditable(false);
        frame.add(displayAuthor);
        
        inputField = new JTextField();
        inputField.setBounds(100, 290, 800, 40);
        inputField.setFont(new Font("Consolas", Font.BOLD, 20));
        inputField.setEnabled(false);
        inputField.setBackground(Color.DARK_GRAY);
        inputField.setForeground(Color.WHITE);
        inputField.setCaretColor(Color.WHITE);
        inputField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                input = inputField.getText();
                if (!input.trim().equals("")) {
                    endTime = LocalTime.now().toNanoOfDay();
                    double duration = (endTime - startTime) / 1000000000;
                    double temp = Functions.calculateRawWPM(input.length(), duration) * ((double) Functions.numCorrectChars(quote, input) / quote.length()); // calculates the real wpm
                    wpm.setText("WPM: " + Math.round(temp * 100) / 100.0); // shows the real wpm rounded to the nearest hundredth
                    double temp2 = Functions.checkAccuracy(quote, input); // stores the accuracy in a second temp variable
                    accuracy.setText("Accuracy: " + Math.round(temp2 * 100) / 100.0 + "%"); // displays the accuracy percentage rounded to the nearest hundredth
                    displayAuthor.setText("Author: " + author);
                    wpmScores.add(temp);
                    accuracyScores.add(temp2);
                    inputField.setEnabled(false);
                }
            }
        });
        frame.add(inputField);

        infoButton = new JButton();
        infoButton.setBounds(450, 600, 100, 50);
        infoButton.setFont(new Font(null, Font.BOLD, 15));
        infoButton.addActionListener(this);
        infoButton.setText("INFO");
        infoButton.setFocusable(false);
        frame.add(infoButton);

        startButton = new JButton();
        startButton.setBounds(450, 525, 100, 50);
        startButton.setFont(new Font(null, Font.BOLD, 15));
        startButton.addActionListener(this);
        startButton.setText("START");
        startButton.setFocusable(false);
        frame.add(startButton);

        redoButton = new JButton();
        redoButton.setBounds(300, 525, 125, 50);
        redoButton.setFont(new Font(null, Font.BOLD, 15));
        redoButton.addActionListener(this);
        redoButton.setText("REDO TEST");
        redoButton.setFocusable(false);
        redoButton.setEnabled(false);
        frame.add(redoButton);

        newTestButton = new JButton();
        newTestButton.setBounds(575, 525, 125, 50);
        newTestButton.setFont(new Font(null, Font.BOLD, 15));
        newTestButton.addActionListener(this);
        newTestButton.setText("NEW TEST");
        newTestButton.setFocusable(false);
        frame.add(newTestButton);

        statsButton = new JButton();
        statsButton.setBounds(575, 600, 100, 50);
        statsButton.setFont(new Font(null, Font.BOLD, 15));
        statsButton.addActionListener(this);
        statsButton.setText("STATS");
        statsButton.setFocusable(false);
        frame.add(statsButton);
        
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

        // stops running the program after the user exits the window, sets the layout of the frame to null, and makes the frame visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // an info box appears telling them how to use the program
        if (e.getSource() == infoButton) {
            new InfoButton();
        }
        // the text field can npw be written in and any prior text is cleared
        // the redo button is also enabled and the start time is set to the current time of the day in nanoseconds
        if (e.getSource() == startButton) {
            inputField.setEnabled(true);
            inputField.setText("");
            redoButton.setEnabled(true);
            startTime = LocalTime.now().toNanoOfDay();
        }
        // clears any prior text in the text field and disables the text field
        if (e.getSource() == redoButton) {
            inputField.setText("");
            inputField.setEnabled(false);
        }
        // sets quote to a randomly generated quote, clears the text field, disables the text field, and sets the displayQuote text to the randomly generated quote
        if (e.getSource() == newTestButton) {
            try {
                quoteData = Functions.getRandomQuoteData();
                quote = Functions.getRandomQuote(quoteData);
                author = Functions.getQuoteAuthor(quoteData);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            inputField.setText("");
            inputField.setEnabled(false);
            displayQuote.setText(quote);
            displayAuthor.setText(author);
        }
        if (e.getSource() == statsButton) {
            if (wpmScores.size() == 0) {
                new StatsScreen(0, 0, 0, 0);
            } else {
                double avgWPM = 0;
                double avgAcc = 0;
                double highestWPM = 0;
                double highestAcc = 0;
                for (int i = 0; i < wpmScores.size(); i++) {
                    avgWPM += wpmScores.get(i);
                    if (wpmScores.get(i) > highestWPM) {
                        highestWPM = wpmScores.get(i);
                    }
                }
                for (int i = 0; i < accuracyScores.size(); i++) {
                    avgAcc += accuracyScores.get(i);
                    if (accuracyScores.get(i) > highestAcc) {
                        highestAcc = accuracyScores.get(i);
                    }
                }
                avgWPM /= wpmScores.size();
                avgAcc /= accuracyScores.size();
                new StatsScreen(avgWPM, avgAcc, highestWPM, highestAcc);
            }

        }
    }
}
