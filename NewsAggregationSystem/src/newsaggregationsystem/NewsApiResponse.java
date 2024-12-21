/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newsaggregationsystem;

import java.util.List;

public class NewsApiResponse {
    private String status;
    private List<NewsArticleResponse> articles;

    public String getStatus() {
        return status;
    }

    public List<NewsArticleResponse> getArticles() {
        return articles;
    }
}

