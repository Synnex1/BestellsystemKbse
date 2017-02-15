
package controller;

import ejb.Persistence;
import entity.Produkt;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.inject.Inject;

/**
 * Der ProduktController besitzt eine Liste aller in der Datenbank vorhandenen Produkte und verwaltet diese.
 * Er ist als Singleton umgesetzt, um somit eine zentrale Zugriffsstelle fuer Produkte zu bieten.
 *
 * @author Mike Morthorst
 */
@Singleton
public class ProduktController{
    private List<Produkt> produkte;
    @Inject
    private Persistence ps;
    
    /**
     * Wird ausgefuehrt nachdem die Injection der Persistenz erfolgt ist.
     * Fuellt die Bestellungsliste mit allen Produkten aus der Datenbank.
     */
    @PostConstruct
    public void init() {
        this.produkte = allElements();
    }
    
    private List<Produkt> allElements() {
        return ps.findAllProdukt();
    }
    
    /**
     * Erstellt ein neues Produkt und schreibt es in die Datenbank.
     *
     * @param name Bezeichnung des Produktes.
     * @param anzahl Bestandsmenge des Produktes.
     */
    public void newProdukt(String name, int anzahl) {
        Produkt p = new Produkt();
        p.setName(name);
        p.setAnzahl(anzahl);
        ps.persist(p);
        this.produkte.add(p);
    }
    
    /**
     * Gibt die Liste aller Produkte zurück.
     *
     * @return Liste aller Produkte
     */
    public List<Produkt> getAllProdukt() {
        return this.produkte;
    }
    
    /**
     * Loescht ein Produkt aus der Datenbank.
     *
     * @param produkt_id Id des zu loeschenden Produktes
     */
    public void deleteProdukt(Long produkt_id) {
        for(Iterator<Produkt> i = this.produkte.iterator(); i.hasNext(); ){
            Produkt p = i.next();
            if(p.getId().compareTo(produkt_id) == 0) {
                i.remove();
                ps.remove(p);
            }
        }
    }
    
    /**
     * Sucht unter Angabe der Produkt-Id ein Produkt und gibt res zurueck
     *
     * @param produkt_id Id des zu suchenden Produktes
     * @return Das Produkt welches durch die uebergebene Id identifiziert wird. Ansonsten Null.
     */
    public Produkt findProdukt(Long produkt_id) {
        for(Produkt p : this.produkte) {
            if(p.getId().compareTo(produkt_id) == 0) {
                return p;
            }
        }
        return null;
    }
    
    /**
     * Veraendert die Werte eines Produktes.
     *
     * @param produkt_id Die Id des Produktes, welches veraendert werden soll.
     * @param name Neuer Bezeichner des Produktes.
     * @param anzahl Neuer Bestand des Produktes.
     */
    public void updateProdukt(Long produkt_id, String name, int anzahl) {
        for(Produkt p : this.produkte) {
            if(p.getId().compareTo(produkt_id) == 0) {
                p.setName(name);
                p.setAnzahl(anzahl);
                ps.merge(p);
            }
        }
    }
    
    /**
     * Veraendert den Bestand eines Produktes.
     *
     * @param produkt_id Id des Produktes, welches veraendert werden soll.
     * @param anzahl Neuer Bestand des Produktes.
     */
    public void updateProduktCount(Long produkt_id, int anzahl) {
        for(Produkt p: this.produkte) {
            if(p.getId().compareTo(produkt_id) == 0) {
                p.setAnzahl(anzahl);
                ps.merge(p);
            }
        }
    }

    /**
     * Untersucht ob die uebergebene Bestellmenge den Bestand des Produktes uebersteigt oder nicht.
     *
     * @param produkt_id Id des zu untersuchenden Produktes.
     * @param buy_count Bestellmenge
     * @return Das Produkt mit vermindertem Bestand, wenn die Bestellmenge kleiner ist als der vorherhige Bestand. Ansonsten Null.
     */
    public Produkt checkProduktCountConstraint(Long produkt_id, int buy_count) {
        for(Produkt p : this.produkte) {
            if(p.getId().compareTo(produkt_id) == 0) {
                if(p.getAnzahl() < buy_count) {
                    return null;
                } else {
                    p.setAnzahl(p.getAnzahl() - buy_count);
                    ps.merge(p);
                    return p;
                }
            }
        }
        return null;
    }
}

