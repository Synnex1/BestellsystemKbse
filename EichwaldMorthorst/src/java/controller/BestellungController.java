/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Mike
 */
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
