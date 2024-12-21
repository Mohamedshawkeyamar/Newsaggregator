/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newsaggregationsystem;

/**
 *
 * @author moham
 */
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class NewsDataFetcher {
    private static NewsDataFetcher instance;


private static Connection getConnection() {
    return connection;   
}

    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/news_aggregator";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    
    
    private NewsDataFetcher() {
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Database connection established!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static NewsDataFetcher getInstance() {
        if (instance == null) {
            instance = new NewsDataFetcher();
        }
        return instance;
    }

    public void addArticle(String title, String content, String type) {
        String sql = "INSERT INTO Articles (title, content, type) VALUES (?, ?, ?)";

        try (Connection conn = NewsDataFetcher.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setString(3, type);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new article was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public List<NewsArticle> fetchArticles() {
        // Simulate fetching articles from various sources
        List<NewsArticle> articles = new ArrayList<>();
        articles.add(new NewsArticle("Breaking News: Java Design Patterns", "Content about Java Design Patterns", "Article"));
        articles.add(new NewsArticle("Latest Tech Trends", "Content about latest tech trends", "Blog"));
        return articles;
    }
}

    