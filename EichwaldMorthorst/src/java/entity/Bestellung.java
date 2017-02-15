
package entity; 

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Die Entitaet Bestellung enthaelt den Namen eines Kunden, sowie eine Liste von Bestellposten. 
 *
 * @author Mike Morthorst
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Bestellung.findAll", query = "SELECT b FROM Bestellung b ORDER BY b.id"),
})
public class Bestellung implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String kunde;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "bestellung")
    private List<Bestellposten> bestellposten;
    
    /**
     * Standartkonstruktor
     */
    public Bestellung() {
        this.bestellposten = new ArrayList<>();
    }

    /**
     * Id-Getter
     *
     * @return Id der Bestellung.
     */
    public Long getId() {
        return id;
    }

    /**
     * Id-Setter
     *
     * @param id Id, die gesetzt werden soll.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Kunde-Getter
     *
     * @return Kundenname.
     */
    public String getKunde() {
        return kunde;
    }

    /**
     * Kunde-Setter
     *
     * @param kunde Kundenname, der gesetzt werden soll.
     */
    public void setKunde(String kunde) {
        this.kunde = kunde;
    }

    /**
     * Bestellposten-Getter
     *  
     * @return Liste der Bestellposten.
     */
    public List<Bestellposten> getBestellposten() {
        return bestellposten;
    }

    /**
     * Gibt den zu der uebergebenen Id identifizierten Bestellposten zurueck.
     *
     * @param id Id des Bestellpostens.
     * @return Der gefundene Bestellposten.
     */
    public Bestellposten getBestellposten(Long id){
        for(Bestellposten bp : bestellposten){
            if(bp.getId().compareTo(id) == 0){
                return bp;
            }
        }
        return null;
    }
    
    /**
     * Bestellposten-Setter
     *
     * @param bestellposten Liste mit Bestellposten die gesetzt werden soll.
     */
    public void setBestellposten(List<Bestellposten> bestellposten) {
        this.bestellposten = bestellposten;
    }
    
    /**
     * Fuegt einen neuen Bestellposten hinzu.
     *
     * @param bestellposten Bestellposten, der zur Liste hinzugefuegt werden soll.
     */
    public void addBestellposten(Bestellposten bestellposten) {
        this.bestellposten.add(bestellposten);
    }
    
    /**
     * Loescht einen Bestellposten aus der Liste.
     *
     * @param bestellposten_id Id des zu loeschenden Bestellpostens.
     */
    public void deleteBestellposten(Long bestellposten_id){
        for(Iterator<Bestellposten> it = this.bestellposten.iterator(); it.hasNext(); ){
            Bestellposten b = it.next();
            if(b.getId().compareTo(bestellposten_id) == 0) {
                it.remove();
            }
        }
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
        if (!(object instanceof Bestellung)) {
            return false;
        }
        Bestellung other = (Bestellung) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Bestellung[ id=" + id + " ]";
    }
    
}
