/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Bestellposten;
import entity.Bestellung;
import entity.Produkt;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Persistenzklasse zum schreiben in die Datenbank.
 *
 * @author Mike Morthorst
 */
@Stateless
public class Persistence {
    @Inject
    private EntityManager em;
    
    /**
     * Persistiert ein Objekt in die Datenbank
     *
     * @param object Das zu persistierende Objekt.
     */
    public void persist(Object object) {
        em.persist(object);
    }
    
    /**
     * Merged ein Objekt in die Datenbank
     * 
     * @param object Das zu Objekte, welches gemerged werden soll.
     */
    public void merge(Object object) {
        em.merge(object);
    }
    
    /**
     * Loescht ein Objekt aus der Datenbank
     *
     * @param object Das zu loeschende Objekt
     */
    public void remove(Object object) {
        em.remove(object);
    }
    
    /**
     * Gibt alle Produkte aus der Datenbank in einer Liste aus.
     *
     * @return Liste aller Produkte
     */
    public List<Produkt> findAllProdukt() {
        return em.createNamedQuery("Produkt.findAll", Produkt.class).getResultList();
    }
    
    /**
     * Gibt alle Bestellungen aus der Datenbank in einer Liste aus.
     *
     * @return Liste aller bestellungen
     */
    public List<Bestellung> findAllBestellung() {
        return em.createNamedQuery("Bestellung.findAll", Bestellung.class).getResultList();
    }
    
    /**
     * Loescht einen Bestellposten aus der Datenbank.
     *
     * @param bestellposten_id Id des zu loeschenden Bestellpostens.
     */
    public void removeBestellposten(long bestellposten_id) {
        Bestellposten b = em.find(Bestellposten.class, bestellposten_id);
        remove(b);
    }
    
    /**
     * Loescht eine Bestellung aus der Datenbank.
     *
     * @param bestellung_id Id der zu loeschenden Bestellung.
     */
    public void removeBestellung(long bestellung_id) {
        Bestellung b = em.find(Bestellung.class, bestellung_id);
        remove(b);
    }
}
