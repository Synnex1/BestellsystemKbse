package model;

import java.io.Serializable;
import javax.inject.Named;
import controller.ProduktController;
import entity.Produkt;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 * Die ViewModelIndex beschränkt sich ausschließlich auf die View index.xhtml und stellt alle Funktionalitäten für diese bereit.
 * Sie besitzt die Variablen String name und int anzahl die für die Entität Produkt beötigt werden.
 * Außerdem wird der ProduktController injectet, um die Daten in die Datenbank zu schreiben.
 *
 * @author Andrej Eichwald
 */
@Named(value = "vmIndex")
@RequestScoped
public class ViewModelIndex implements Serializable {
    @Pattern(regexp="([a-zA-Z\\s]*)\\w+", message="Produktname darf nicht leer sein oder keine Sonderzeichen enthalten")
    private String name;
    @Digits(integer=6, fraction=0)
    @Min(value=1, message="Der Wert muss größer als 1 sein!")
    @Max(value=10000, message="Es dürfen nicht mehr als 10000 Artikel auf einmal verkauft werden")
    private int anzahl;
    @Inject
    ProduktController pc;
    
    /**
     * Standardkonstruktor
     * 
     */
    public ViewModelIndex(){
    }

    /**
     * Gibt den Namen wider
     * 
     * @return den Namen des Produktes
     */
    public String getName() {
        return name;
    }

    /**
     * Der Name wird gesetzt
     *
     * 
     * @param name der gesetzt wird
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gibt die Anzahl wieder
     *
     * @return die Anzahl des Produkts
     */
    public int getAnzahl() {
        return anzahl;
    }

    /**
     * Die Anzahl wird gesetzt
     *
     * @param anzahl die gesetzt wird
     */
    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }
    
    /**
     * Ein neues Produkt wird mit dem übergebenen Namen und der Anzahl erstellt.
     * Die Id wird erst in der Datenbank erzeugt und wieder zurückgegeben.
     *  
     * @param name
     * @param anzahl
     */
    public void newProdukt(String name, int anzahl){
        pc.newProdukt(name, anzahl);
        this.name="";
        this.anzahl=0;
    }
    
    /**
     * Gibt alle in der Datenbank vorhandenen Produkte wieder
     * 
     * @return Eine Liste von allen vorhandenen Produkten wird zurückgeliefert
     */
    public List<Produkt> getAllProdukt(){
        return pc.getAllProdukt();
    }
    
}
