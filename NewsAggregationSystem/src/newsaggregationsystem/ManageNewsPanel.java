/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newsaggregationsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ManageNewsPanel extends JPanel {
    private JTextField titleField;
    private JTextArea contentArea;
    private JTextField typeField;

    public ManageNewsPanel() {
        setLayout(new BorderLayout());

        // Title Input
        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField(20);

        // Content Input
        JLabel contentLabel = new JLabel("Content:");
        contentArea = new JTextArea(5, 20);

        // Type Input
        JLabel typeLabel = new JLabel("Type:");
        typeField = new JTextField(20);

        // Submit Button
        JButton submitButton = new JButton("Add News");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNews();
            }
        });

        // Layout
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(titleLabel);
        inputPanel.add(titleField);
        inputPanel.add(contentLabel);
        inputPanel.add(new JScrollPane(contentArea));
        inputPanel.add(typeLabel);
        inputPanel.add(typeField);

        add(inputPanel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);
    }

    private void addNews() {
        String title = titleField.getText();
        String content = contentArea.getText();
        String type = typeField.getText();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/news_aggregator", "root", "root")) {
            String query = "INSERT INTO Articales (title, content, type) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, content);
            preparedStatement.setString(3, type.isEmpty() ? "general" : type); // تعيين قيمة افتراضية إذا لم يتم إدخال نوع
            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(this, "News added successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding news: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
