
package entity; 

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


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
    @OneToMany
    private List<Bestellposten> bestellposten = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKunde() {
        return kunde;
    }

    public void setKunde(String kunde) {
        this.kunde = kunde;
    }

    public List<Bestellposten> getBestellposten() {
        return bestellposten;
    }
    
    public Bestellposten getBestellposten(Long id){
        for(Bestellposten bp : bestellposten){
            if(bp.getId().compareTo(id) == 0){
                return bp;
            }
        }
        return null;
    }

    public void setBestellposten(List<Bestellposten> bestellposten) {
        this.bestellposten = bestellposten;
    }
    
    public void addBestellposten() {
        Bestellposten b = new Bestellposten();
        this.bestellposten.add(b);
    }
    
    public void deleteBestellposten(Long id){
        for(Bestellposten bp : bestellposten){
            if(bp.getId().compareTo(id) == 0){
                this.bestellposten.remove(bp);
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
