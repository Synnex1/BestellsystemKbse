
package controller;

public class BestellungController {
    private static BestellungController instance;
    private ProduktController pc;
    
    private BestellungController() {
        pc = ProduktController.getInstance();
    }
    
    public static BestellungController getInstance() {
        if(instance == null) {
            instance = new BestellungController();
        }
        return instance;
    }
    
    
    
}
