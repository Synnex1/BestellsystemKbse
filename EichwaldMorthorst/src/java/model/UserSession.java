
package model;

import entity.Bestellung;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "uS")
@SessionScoped
public class UserSession implements Serializable{
    private Bestellung bestellung;

    public UserSession(){
        this.bestellung = new Bestellung();
        this.bestellung.addBestellposten();
    }
    
    public Bestellung getBestellung() {
        return bestellung;
    }

    public void setBestellung(Bestellung bestellung) {
        this.bestellung = bestellung;
    }
    
    public void addBestellPosten(){
        this.bestellung.addBestellposten();
    }
    
    public void deleteBestellPosten(long id){
        bestellung.deleteBestellposten(id);
    }
    
    public void newBestellung(){
        bestellung.addBestellposten();
    }
    
    
}
