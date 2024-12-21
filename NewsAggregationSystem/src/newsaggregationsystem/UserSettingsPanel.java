package newsaggregationsystem;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class UserSettingsPanel extends JPanel {
    private JTextField categoriesField;
    private JTextField sourcesField;
    private JButton saveButton;

    public UserSettingsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // إنشاء الحقول الخاصة بالإدخال
        categoriesField = new JTextField(20);
        sourcesField = new JTextField(20);
        saveButton = new JButton("Save Settings");

        add(new JLabel("Preferred Categories:"));
        add(categoriesField);
        add(new JLabel("Preferred Sources:"));
        add(sourcesField);
        add(saveButton);

        // التعامل مع زر الحفظ
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUserSettings();
            }
        });

        // تحميل إعدادات المستخدم من قاعدة البيانات
        UserSettingsManager settingsManager = UserSettingsManager.getInstance();
        settingsManager.loadUserSettings();

        // عرض القيم الموجودة في الحقول
        categoriesField.setText(settingsManager.getPreferredCategories());
        sourcesField.setText(settingsManager.getPreferredSources());
    }

    private void saveUserSettings() {
        // جلب القيم المدخلة من الحقول
        String categories = categoriesField.getText();
        String sources = sourcesField.getText();

        // حفظ الإعدادات عبر UserSettingsManager
        UserSettingsManager settingsManager = UserSettingsManager.getInstance();
        settingsManager.setPreferredCategories(categories);
        settingsManager.setPreferredSources(sources);
        settingsManager.saveUserSettings();

        JOptionPane.showMessageDialog(this, "Settings saved successfully!");
    }
}
