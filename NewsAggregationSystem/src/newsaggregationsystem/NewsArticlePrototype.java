/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newsaggregationsystem;

public class NewsArticlePrototype implements Cloneable {
    private String title;
    private String content;
    private String type;

    public NewsArticlePrototype(String title, String content, String type) {
        this.title = title;
        this.content = content;
        this.type = type;
    }

    @Override
    public NewsArticlePrototype clone() {
        try {
            return (NewsArticlePrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
