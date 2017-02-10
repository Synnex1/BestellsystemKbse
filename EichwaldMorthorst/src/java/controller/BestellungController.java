
package controller;

public class BestellungController {
    private static BestellungController instance;
    
    private BestellungController() {
        
    }
    
    public static BestellungController getInstance() {
        if(instance == null) {
            instance = new BestellungController();
        }
        return instance;
    }
    
    
    
}
