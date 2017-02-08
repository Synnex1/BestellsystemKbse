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
public class ProduktController {
    private static ProduktController instance;
    
    private ProduktController() {
    
    }
    
    public static ProduktController getInstance() {
        if(instance == null) {
            instance = new ProduktController();
        }
        return instance;
    }
}
