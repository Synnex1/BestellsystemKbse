/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

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
    
    public List<Produkt> getAllProdukt() {
        return em.createNamedQuery("Produkt.findAll", Produkt.class).getResultList();
    }
    
}
