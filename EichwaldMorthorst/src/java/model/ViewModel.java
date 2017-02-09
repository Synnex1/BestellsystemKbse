package model;

import java.io.Serializable;
import javax.inject.Named;
import controller.ProduktController;
import entity.Produkt;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


@Named(value = "vm")
@RequestScoped
public class ViewModel implements Serializable {
    @Size(min=2)
    private String name;
    @Min(value=1, message="Der Wert muss größer als 1 sein!")
    @Max(value=10000, message="Es dürfen nicht mehr als 10000 Artikel auf einmal verkauft werden")
    private int anzahl;
    ProduktController pc;
    
    public ViewModel(){
        this.pc = ProduktController.getInstance();
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
    
    public void newProdukt(String name, int anzahl){
        pc.newProdukt(name, anzahl);
    }
    
    public List<Produkt> getAllProdukt(){
        return pc.getAllProdukt();
    }
}
