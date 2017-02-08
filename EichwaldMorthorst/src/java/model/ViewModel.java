package model;

import java.io.Serializable;
import javax.inject.Named;
import controller.ProduktController;


@Named(value = "vm")
public class ViewModel implements Serializable {
    private String name;
    private int anzahl;
    
    
    public ViewModel(){
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
        
    }
}
