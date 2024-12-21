/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newsaggregationsystem;

public class NewsCategoryFactory {
    public static NewsCategory createCategory(String categoryName) {
        return new NewsCategory(categoryName);
    }
}

