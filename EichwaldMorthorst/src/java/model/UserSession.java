
package model;

import entity.Bestellposten;
import entity.Bestellung;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "uS")
@SessionScoped
public class UserSession implements Serializable{
    private Bestellung bestellung;
    private Long id = 0L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        id++;
    }

    public UserSession(){
        this.bestellung = new Bestellung();
        Bestellposten b = new Bestellposten();
        b.setId(this.id);
        bestellung.getBestellposten().add(b);
    }
    
    public Bestellung getBestellung() {
        return bestellung;
    }

    public void setBestellung(Bestellung bestellung) {
        this.bestellung = bestellung;
    }
    
    public void addBestellPosten(){
        Bestellposten b = new Bestellposten();
        b.setId(this.id);
        this.bestellung.getBestellposten().add(b);
    }
    
    public void deleteBestellPosten(Long id){
        bestellung.deleteBestellposten(id);
    }
    
    
    
}
