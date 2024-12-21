/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newsaggregationsystem;

public class Source {
    private String id; // يمكن أن يكون null
    private String name;

    // Constructor
    public Source(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // يمكن إضافة getters أخرى إذا لزم الأمر
}
