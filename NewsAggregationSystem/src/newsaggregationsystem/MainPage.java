/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newsaggregationsystem;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JFrame {

    public MainPage() {
        setTitle("News Aggregator - Main Page");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel setup
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to News Aggregator", JLabel.CENTER);
        panel.add(welcomeLabel, BorderLayout.NORTH);

        JTextArea newsArea = new JTextArea();
        newsArea.setEditable(false);
        panel.add(new JScrollPane(newsArea), BorderLayout.CENTER);

        JButton fetchNewsButton = new JButton("Fetch News");
        fetchNewsButton.addActionListener(e -> {
            // Simulate fetching news
            newsArea.setText("News 1: Example News Content\nNews 2: Example News Content\n...");
        });

        panel.add(fetchNewsButton, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }
}
