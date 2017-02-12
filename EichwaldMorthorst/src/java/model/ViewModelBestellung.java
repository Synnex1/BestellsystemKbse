package model;

import controller.BestellungController;
import entity.Bestellposten;
import java.io.Serializable;
import javax.inject.Named;
import entity.Produkt;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import entity.Bestellung;

@Named(value = "vmBestellung")
@RequestScoped
public class ViewModelBestellung implements Serializable {
    private long id;
    @Size(min=2)
    private String name;
    @Digits(integer=6, fraction=0)
    @Min(value=1, message="Der Wert muss größer als 1 sein!")
    @Max(value=10000, message="Es dürfen nicht mehr als 10000 Artikel auf einmal verkauft werden")
    private int anzahl;
    private Bestellung bestellung;
    @Inject
    BestellungController bc;

    
    
    public ViewModelBestellung(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }
    
    public Bestellung getBestellung() {
        return bestellung;
    }

    public void setBestellung(Bestellung bestellung) {
        this.bestellung = bestellung;
    }
    
    
    
    
    
    
}
