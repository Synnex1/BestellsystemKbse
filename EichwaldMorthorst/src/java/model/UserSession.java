
package model;

import entity.Bestellung;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Die User Session speichert wichtige Werte für die ganze Session. 
 * Somit kann man zwischen den Views wechseln ohne den Verlust der gemachten Einträge.
 * 
 * @author Andrej Eichwald
 */
@Named(value = "uS")
@SessionScoped
public class UserSession implements Serializable{
    private Bestellung bestellung;
    private String  bestellKunde; 
    private Long bestellPostenId;
    private Long  produktId;
    @Digits(integer=6, fraction=0)
    @Min(value=1, message="Der Wert muss größer als 1 sein!")
    @Max(value=10000, message="Es dürfen nicht mehr als 10000 Artikel auf einmal verkauft werden")
    private int produktAnzahl = 1;
    private boolean hideInput = true;
    private boolean hideList = true;
 
    /**
     *
     * @return Gibt eine Bestellung wieder
     */
    public Bestellung getBestellung() {
        return bestellung;
    }

    /**
     * Setzt die Bestellung
     * 
     * @param bestellung
     */
    public void setBestellung(Bestellung bestellung) {
        this.bestellung = bestellung;
    }
    
    /**
     * Gibt den für die Bestellung eingetragenen Namen wieder
     * 
     * @return String Name des Kunden der Bestellung
     */
    public String getBestellKunde() {
        return bestellKunde;
    }

    /**
     * Setzt den Namen des Kunden für die Bestellung
     * 
     * @param bestellKunde Name des Kunden
     */
    public void setBestellKunde(String bestellKunde) {
        this.bestellKunde = bestellKunde;
    }
    
    /**
     * Gibt die Id eines Bestellpostens wieder
     * 
     * @return Long Id des Bestellpostens
     */
    public Long getBestellPostenId() {
        return bestellPostenId;
    }

    /**
     * Setzt die Bestellposten Id 
     *
     * @param bestellPostenId Long Wert für die Id des Bestellpostens
     */
    public void setBestellPostenId(Long bestellPostenId) {
        this.bestellPostenId = bestellPostenId;
    }

    /**
     * Gibt die Id des Produktes wieder
     *
     * @return Long Id des Produktes
     */
    public Long getProduktId() {
        return produktId;
    }

    /**
     * Setzt die Id des Produktes
     *
     * @param produktId Long Id des Produktes
     */
    public void setProduktId(Long produktId) {
        this.produktId = produktId;
    }
    
    /**
     * Gibt die Anzahl des Produktes wieder 
     *
     * @return int Anzahl des Produktes
     */
    public int getProduktAnzahl() {
        return produktAnzahl;
    }

    /**
     * Setzt die Anzahl des Produktes
     *
     * @param produktAnzahl int Anzahl des Produktes
     */
    public void setProduktAnzahl(int produktAnzahl) {
        this.produktAnzahl = produktAnzahl;
    }

    /**
     * Gibt den boolean Wert der Variable HideInput wieder
     *
     * @return boolean HideInput
     */
    public boolean isHideInput() {
        return hideInput;
    }

    /**
     * Setzt den boolean Wert der Variable HideInput
     *
     * @param hideInput boolean HideInput
     */
    public void setHideInput(boolean hideInput) {
        this.hideInput = hideInput;
    }

    /**
     * Gibt den boolean Wert der Variable HideList wieder
     *
     * @return boolean HideList
     */
    public boolean isHideList() {
        return hideList;
    }

    /**
     * Setzt den boolean Wert der Variable HideList
     *
     * @param hideList boolean HideList
     */
    public void setHideList(boolean hideList) {
        this.hideList = hideList;
    }
    
    /**
     * Setzt den Wert der Variable HideInput auf false und somit auch eine Klasse 
     * von hidden auf nothidden. Es werden danach einige Sachen eingeblendet, um 
     * mit Ihnen weiter zu agieren.
     *
     */
    public void hideInputFalse(){
        this.hideInput = false;
    }
    
    /**
     * Setzt den Wert der Variable HideList auf false und somit auch eine Klasse 
     * von hidden auf nothidden. Es werden danach einige Sachen eingeblendet, um 
     * mit Ihnen weiter zu agieren.
     *
     */
    public void hideListFalse(){
        this.hideList = false;
    }
    
}
