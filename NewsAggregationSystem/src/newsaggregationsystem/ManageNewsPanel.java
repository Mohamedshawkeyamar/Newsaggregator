package newsaggregationsystem;

import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class ManageNewsPanel extends JPanel {
    private JTextField titleField;
    private JTextArea contentArea;
    private JTextField typeField;
    private JButton fetchNewsButton; // زر لجلب الأخبار
    private JButton submitButton; // زر لإضافة الأخبار
    private DefaultListModel<String> listModel; // نموذج قائمة المقالات
    private JList<String> articlesList; // قائمة المقالات
    private List<NewsArticle> articles = new ArrayList<>(); // قائمة لتخزين المقالات

    public ManageNewsPanel() {
        setLayout(new BorderLayout());

        // إعداد واجهة المستخدم
        titleField = new JTextField(20);
        contentArea = new JTextArea(5, 20);
        typeField = new JTextField(20);
        submitButton = new JButton("Add News");
        fetchNewsButton = new JButton("Fetch News"); // زر لجلب الأخبار

        // إعداد قائمة المقالات
        listModel = new DefaultListModel<>();
        articlesList = new JList<>(listModel);

        // Layout
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Content:"));
        inputPanel.add(new JScrollPane(contentArea));
        inputPanel.add(new JLabel("Type:"));
        inputPanel.add(typeField);

        add(inputPanel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);
        add(fetchNewsButton, BorderLayout.NORTH); // إضافة زر جلب الأخبار
        add(new JScrollPane(articlesList), BorderLayout.EAST); // إضافة قائمة المقالات

        // إضافة المستمع للزرين
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNews();
            }
        });

        fetchNewsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    initializeArticles(); // جلب الأخبار
                    updateArticleList(articles); // تحديث قائمة المقالات
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ManageNewsPanel.this, "Error fetching news: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // إضافة مستمع لقائمة المقالات لاختيار الأخبار
        articlesList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = articlesList.getSelectedIndex();
                if (selectedIndex != -1) {
                    NewsArticle selectedArticle = articles.get(selectedIndex);
                    titleField.setText(selectedArticle.getTitle());
                    contentArea.setText(selectedArticle.getContent());
                    typeField.setText(selectedArticle.getType());
                }
            }
        });
    }

    private void addNews() {
        String title = titleField.getText();
        String content = contentArea.getText();
        String type = typeField.getText();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/news_aggregator", "root", "root")) {
            String query = "INSERT INTO Articles (title, content, type) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, content);
            preparedStatement.setString(3, type.isEmpty() ? "general" : type);
            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(this, "News added successfully!");
            // مسح الحقول بعد الإضافة
            titleField.setText("");
            contentArea.setText("");
            typeField.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding news: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // طريقة لجلب الأخبار من API
    private void initializeArticles() throws Exception {
        String apiKey = "b14e863c47a54eb286f11314ad309346"; // استخدم مفتاح API الصحيح
        String urlString = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=" + apiKey;

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int status = connection.getResponseCode();
        System.out.println("Response Code: " + status); // طباعة رمز الاستجابة

        if (status == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println("Response: " + response.toString()); // طباعة الاستجابة بالكامل

            // تحليل استجابة JSON باستخدام Gson
            Gson gson = new Gson();
            NewsApiResponse newsApiResponse = gson.fromJson(response.toString(), NewsApiResponse.class);

            // إضافة المقالات إلى القائمة
            articles.clear(); // مسح المقالات القديمة
            for (NewsArticleResponse articleResponse : newsApiResponse.getArticles()) {
                NewsArticle article = NewsArticleFactory.createArticle(articleResponse);
                if (article != null) {
                    articles.add(article);
                } else {
                    System.out.println("Error creating article from response: " + articleResponse);
                }
            }
        } else {
            String errorMessage = "Error fetching news from API: " + connection.getResponseMessage();
            System.out.println(errorMessage); // طباعة رسالة الخطأ
            throw new Exception(errorMessage);
        }
    }

    // تحديث قائمة المقالات
    private void updateArticleList(List<NewsArticle> articles) {
        listModel.clear();
        for (NewsArticle article : articles) {
            listModel.addElement(article.getTitle()); // عرض العناوين فقط
        }
    }
}
