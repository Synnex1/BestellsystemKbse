/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.Persistence;
import entity.Produkt;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mike
 */
public class ProduktController {
    private static ProduktController instance;
    private List<Produkt> produkte;
    private Persistence ps = new Persistence();
    
    private ProduktController() {
        this.produkte = new ArrayList<>();
    }
    
    public static ProduktController getInstance() {
        if(instance == null) {
            instance = new ProduktController();
        }
        return instance;
    }
    
    private void allProdukt() {
        this.produkte = ps.getAllProdukt();
    }
    
    public void newProdukt(String name, int anzahl) {
        Produkt p = new Produkt();
        p.setName(name);
        p.setAnzahl(anzahl);
        this.produkte.add(p);
    }
    
    public List<Produkt> getAllProdukt() {
        return this.produkte;
    }
}
