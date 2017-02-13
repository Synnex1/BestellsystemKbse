/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Bestellung;
import entity.Produkt;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Mike
 */
@Stateless
public class Persistence {
    @Inject
    private EntityManager em;
    
    public void persist(Object object) {
        em.persist(object);
    }
    
    public void merge(Object object) {
        em.merge(object);
    }
    
    public void remove(Object object) {
        em.remove(object);
    }
    
    public void refresh(Object object) {
        em.refresh(object);
    }
    
    public List<Produkt> findAllProdukt() {
        return em.createNamedQuery("Produkt.findAll", Produkt.class).getResultList();
    }
    
    public List<Bestellung> findAllBestellung() {
        return em.createNamedQuery("Bestellung.findAll", Bestellung.class).getResultList();
    }    
}
