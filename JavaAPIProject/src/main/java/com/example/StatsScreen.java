package com.example;
import javax.swing.*;

public class StatsScreen {
    StatsScreen(double avgWPM, double avgAcc, double highestWPM, double highestAcc) {
        JOptionPane.showMessageDialog(null, "Your average wpm is: " + avgWPM + "\nYour average accuracy is: " + avgAcc
         + "\nYour highest WPM is: " + highestWPM + "\nYour highest accuracy is: " + highestAcc, "Statistics", JOptionPane.PLAIN_MESSAGE);
    }
}
