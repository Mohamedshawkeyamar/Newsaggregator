package newsaggregationsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ViewNewsPanel extends JPanel {
    private JTextArea newsArea;

    public ViewNewsPanel() {
        setLayout(new BorderLayout());

        newsArea = new JTextArea();
        newsArea.setEditable(false);
        add(new JScrollPane(newsArea), BorderLayout.CENTER);

        loadNews();
    }

    private void loadNews() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/news_aggregator", "root", "root")) {
            String query = "SELECT title, content, type FROM Articles"; // استخدم الاسم الصحيح هنا
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            StringBuilder newsBuilder = new StringBuilder();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                String type = resultSet.getString("type");

                newsBuilder.append("Title: ").append(title).append("\n");
                newsBuilder.append("Content: ").append(content).append("\n");
                newsBuilder.append("Type: ").append(type).append("\n");
                newsBuilder.append("------------------------------\n");
            }

            newsArea.setText(newsBuilder.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error fetching news: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
