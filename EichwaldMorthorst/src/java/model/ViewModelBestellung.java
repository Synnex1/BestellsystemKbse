package model;

import controller.BestellungController;
import controller.ProduktController;
import java.io.Serializable;
import javax.inject.Named;
import entity.Produkt;
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
    private int anzahl =1;
    @Inject
    BestellungController bc;
    @Inject
    ProduktController pc;
    @Inject
    UserSession uS;

    
    
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
    
    public void valueChanged(){
    }
    
    public boolean checkIfExistEnough(long produktId, int anzahl){
        if(pc.findProdukt(produktId) == null){
            return false;
        }else if(anzahl > pc.findProdukt(produktId).getAnzahl()){
            return false;
        }
        return true;
    }
    
    
    
    public void newKunde(String kunde){
        uS.setBestellung(bc.newBestellung(kunde));
    }
    
    public void zumWarenkorbZufuegen(Long bestellId, Long produktId, int produktAnzahl){
        uS.setBestellung(bc.addBestellpostenToBestellung(bestellId,produktId,produktAnzahl));
    }
    
    public void bestellPostenBearbeiten(Long bestellungId, Long bestellPostenId, int anzahl){
        uS.setBestellung(bc.updateBestellposten(bestellungId, bestellPostenId, anzahl));
        
    }
    
    public void bestellPostenLoeschen(Long bestellungId, Long bestellPostenId){
        uS.setBestellung(bc.deleteBestellposten(bestellungId, bestellPostenId));
    }
    
    public void deleteBestellung(Long bestellungId){
        bc.deleteBestellung(bestellungId);
        uS.setBestellung(null);
        uS.setBestellKunde(null);
        uS.setProduktAnzahl(1);
        uS.setProduktId(null);
    }
    
    public void setBestellPostenToEditId(Long bestellPostenId){
        uS.setBestellPostenId(bestellPostenId);
    }
    
    public String deleteInView(Long bestellungId, Long bestellPostenId){
        bestellPostenLoeschen(bestellungId, bestellPostenId);
        return "/Views/bestellung.xhtml";
    }
    
    
}
