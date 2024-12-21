package newsaggregationsystem;

public class NewsArticleFactory {
    // طريقة لإنشاء كائن NewsArticle باستخدام العنوان والمحتوى والنوع
    public static NewsArticle createArticle(String title, String content, String type) {
        return new NewsArticle(title, content, type);
    }

    // تعديل هذه الطريقة لإنشاء كائن NewsArticle من NewsArticleResponse
    public static NewsArticle createArticle(NewsArticleResponse articleResponse) {
        // التأكد من أن articleResponse ليس null
        if (articleResponse == null) {
            throw new IllegalArgumentException("ArticleResponse cannot be null");
        }

        // استرجاع البيانات من articleResponse
        String title = articleResponse.getTitle();
        String content = articleResponse.getContent() != null ? articleResponse.getContent() : ""; // التعامل مع null
        String type = articleResponse.getSource() != null ? articleResponse.getSource().getName() : "Unknown"; // التأكد من أن المصدر ليس null

        // إنشاء وإرجاع كائن NewsArticle
        return new NewsArticle(title, content, type);
    }
}
