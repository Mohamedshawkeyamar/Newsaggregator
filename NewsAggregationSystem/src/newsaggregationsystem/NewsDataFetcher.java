package newsaggregationsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDataFetcher {
    private static NewsDataFetcher instance;

    private NewsDataFetcher() {}

    public static NewsDataFetcher getInstance() {
        if (instance == null) {
            instance = new NewsDataFetcher();
        }
        return instance;
    }

    public List<NewsArticle> fetchArticles() {
        List<NewsArticle> articles = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/news_aggregator", "root", "root")) {
            String query = "SELECT title, content, type FROM Articles"; // استخدم الاسم الصحيح هنا
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                String type = resultSet.getString("type");
                articles.add(new NewsArticle(title, content, type));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }
}
