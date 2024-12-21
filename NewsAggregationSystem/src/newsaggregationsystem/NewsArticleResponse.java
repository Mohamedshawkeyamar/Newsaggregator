package newsaggregationsystem;

public class NewsArticleResponse {
    private String title;
    private String content; // تأكد من أن هذا موجود في البيانات
    private Source source;

    // Constructor
    public NewsArticleResponse(String title, String content, Source source) {
        this.title = title;
        this.content = content;
        this.source = source;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Source getSource() {
        return source;
    }
}
