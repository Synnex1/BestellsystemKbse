
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Die Entitaet Produkt enthaelt eine Bezeichnung und eine Anzahl, die den Bestand widerspiegelt.
 *
 * @author Mike Morthorst
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Produkt.findAll", query = "SELECT p FROM Produkt p ORDER BY p.id"),
})
public class Produkt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int anzahl;
    
    /**
     * Id-Getter
     *
     * @return Id des Produktes.
     */
    public Long getId() {
        return id;
    }

    /**
     * Id-Setter
     *
     * @param id Id die gesetzt werden soll.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Name-Getter
     *
     * @return Bezeichnung des Produktes.
     */
    public String getName() {
        return name;
    }

    /**
     * Name-Setter
     *
     * @param name Bezeichnung die gesetzt werden soll.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Anzahl-Getter
     *
     * @return Bestand des Produktes.
     */
    public int getAnzahl() {
        return anzahl;
    }

    /**
     * Anzahl-Setter
     *
     * @param anzahl Bestandsmenge, die gesetzt werden soll.
     */
    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produkt)) {
            return false;
        }
        Produkt other = (Produkt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  name + " (Im Bestand: " + anzahl +")";
    }
    
}