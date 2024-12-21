package newsaggregationsystem;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("News Aggregator - Main Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // لوحة رئيسية مع خلفية متدرجة
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // خلفية متدرجة بنفس ألوان LoginFrame
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(0, 51, 102);  // Navy Blue
                Color color2 = new Color(240, 240, 240); // Light Gray
                GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());

        // إنشاء علامات التبويب
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        // تصميم الألسنة
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));
        tabbedPane.setBackground(Color.WHITE); // خلفية بيضاء للألسنة
        tabbedPane.setForeground(new Color(0, 51, 102)); // نص بلون Navy Blue
        tabbedPane.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2)); // حواف بلون Navy Blue

        // إضافة الألسنة
        tabbedPane.addTab("Manage News", new ManageNewsPanel());
        tabbedPane.addTab("User Settings", new UserSettingsPanel());
        tabbedPane.addTab("View News", new ViewNewsPanel());

        // إضافة علامات التبويب إلى اللوحة الرئيسية
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // إضافة اللوحة الرئيسية إلى النافذة
        add(mainPanel);

        setVisible(true);
    }

    // نقاط الدخول للبرنامج
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}
