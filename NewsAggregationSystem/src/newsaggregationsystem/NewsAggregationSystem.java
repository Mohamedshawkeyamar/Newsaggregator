/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package newsaggregationsystem;

import javax.swing.SwingUtilities;

public class NewsAggregationSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame(); // إنشاء واجهة تسجيل الدخول
        });
    }
}


