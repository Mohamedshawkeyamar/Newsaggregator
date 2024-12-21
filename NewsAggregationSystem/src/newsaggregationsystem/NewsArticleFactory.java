/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newsaggregationsystem;

public class NewsArticleFactory {
    public static NewsArticle createArticle(String title, String content, String type) {
        return new NewsArticle(title, content, type);
    }
}
