package newsaggregationsystem;

import java.sql.*;

public class UserSettingsManager {
    private static UserSettingsManager instance;

    private String preferredCategories;
    private String preferredSources;

    private UserSettingsManager() {}

    public static UserSettingsManager getInstance() {
        if (instance == null) {
            instance = new UserSettingsManager();
        }
        return instance;
    }

    public String getPreferredCategories() {
        return preferredCategories;
    }

    public void setPreferredCategories(String preferredCategories) {
        this.preferredCategories = preferredCategories;
    }

    public String getPreferredSources() {
        return preferredSources;
    }

    public void setPreferredSources(String preferredSources) {
        this.preferredSources = preferredSources;
    }

    // جلب البيانات من قاعدة البيانات
    public void loadUserSettings() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/news_aggregator", "root", "root")) {
            String query = "SELECT preferredCategories, preferredSources FROM users WHERE id = 1"; // استبدل `1` بمعرف المستخدم المطلوب
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                this.preferredCategories = resultSet.getString("preferredCategories");
                this.preferredSources = resultSet.getString("preferredSources");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // تحديث البيانات في قاعدة البيانات
    public void saveUserSettings() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/news_aggregator", "root", "root")) {
            String updateQuery = "UPDATE users SET preferredCategories = ?, preferredSources = ? WHERE id = 1"; // استبدل `1` بمعرف المستخدم المطلوب
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, this.preferredCategories);
            preparedStatement.setString(2, this.preferredSources);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
