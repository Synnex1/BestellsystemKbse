
package entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Mike Morthorst
 */
@Entity
public class Bestellposten implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Bestellung bestellung;
    @ManyToOne
    private Produkt produkt;
    private int anzahl;
    
    /**
     * Standart Konstruktur
     */
    public Bestellposten() {
        
    }
    
    /**
     * Ueberladener Konstruktor
     *
     * @param produkt Produkt, welches zum Bestellposten hinzugefuegt werden soll.
     * @param anzahl Bestellmenge des Produktes.
     * @param bestellung Bestellung zu der der Bestellposten gehoert.
     */
    public Bestellposten(Produkt produkt, int anzahl, Bestellung bestellung) {
        this.produkt = produkt;
        this.anzahl = anzahl;
        this.bestellung = bestellung;
    }
    
    /**
     * Id-Getter
     *
     * @return Id des Bestellpostens.
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
     * Produkt-Getter
     *
     * @return Produkt des Bestellpostens.
     */
    public Produkt getProdukt() {
        return produkt;
    }

    /**
     * Produkt-Setter
     *
     * @param produkt Produkt, welches gesetzt werden soll.
     */
    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    /**
     * Anzahl-Getter
     *
     * @return Bestellmenge
     */
    public int getAnzahl() {
        return anzahl;
    }

    /**
     * Anzahl-Setter
     *
     * @param anzahl Bestellmenge die gesetzt werden soll
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
        if (!(object instanceof Bestellposten)) {
            return false;
        }
        Bestellposten other = (Bestellposten) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Bestellposten[ id=" + id + " ]";
    }
}
