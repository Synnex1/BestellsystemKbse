
package controller;

import ejb.Persistence;
import entity.Produkt;
import java.util.Iterator;
import java.util.List;

public class ProduktController {
    private static ProduktController instance;
    private List<Produkt> produkte;
    private Persistence ps = new Persistence();
    
    private ProduktController() {
        allProdukt();
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
    
    public void deleteProdukt(long produkt_id) {
        for(Iterator<Produkt> i = this.produkte.iterator(); i.hasNext(); ){
            Produkt p = i.next();
            if(p.getId() == produkt_id) {
                i.remove();
                ps.remove(p);
            }
        }
    }
    
    public Produkt findProdukt(long produkt_id) {
        for(Produkt p : this.produkte) {
            if(p.getId() == produkt_id) {
                return p;
            }
        }
        return null;
    }
    
    public void updateProdukt(long produkt_id, String name, int anzahl) {
        for(Produkt p : this.produkte) {
            if(p.getId() == produkt_id) {
                p.setName(name);
                p.setAnzahl(anzahl);
                ps.persist(p);
            }
        }
    }
}
