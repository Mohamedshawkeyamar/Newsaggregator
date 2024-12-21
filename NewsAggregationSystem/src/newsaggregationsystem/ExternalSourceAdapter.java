/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newsaggregationsystem;

/**
 *
 * @author moham
 */
public class ExternalSourceAdapter implements ExternalNewsSource {
    private ExternalNewsAPI externalAPI;

    public ExternalSourceAdapter(ExternalNewsAPI externalAPI) {
        this.externalAPI = externalAPI;
    }

    @Override
    public String fetchNews() {
        return externalAPI.getNewsData();
    }
}