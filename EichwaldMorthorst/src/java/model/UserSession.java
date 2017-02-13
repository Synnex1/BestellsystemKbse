
package model;

import entity.Bestellposten;
import entity.Bestellung;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Named(value = "uS")
@SessionScoped
public class UserSession implements Serializable{
    private Bestellung bestellung;
    
    private Long  produktId;
    @Digits(integer=6, fraction=0)
    @Min(value=1, message="Der Wert muss größer als 1 sein!")
    @Max(value=10000, message="Es dürfen nicht mehr als 10000 Artikel auf einmal verkauft werden")
    private int produktAnzahl;
    
    public Bestellung getBestellung() {
        return bestellung;
    }

    public void setBestellung(Bestellung bestellung) {
        this.bestellung = bestellung;
    }

    public Long getProduktId() {
        return produktId;
    }

    public void setProduktId(Long produktId) {
        this.produktId = produktId;
    }

    public int getProduktAnzahl() {
        return produktAnzahl;
    }

    public void setProduktAnzahl(int produktAnzahl) {
        this.produktAnzahl = produktAnzahl;
    }
    
    
    
    
    
}
