/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newsaggregationsystem;

public class NewsArticleBuilder {
    private String title;
    private String content;
    private String type;

    public NewsArticleBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public NewsArticleBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public NewsArticleBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public NewsArticle build() {
        return new NewsArticle(title, content, type);
    }
}
