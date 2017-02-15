package model;

import controller.BestellungController;
import controller.ProduktController;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import entity.Bestellung;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Die ViewModelBestellung kümmert sich um alle Angelegenheiten des Bestellungsvorgangs und der Ausgabe aller Bestellungen
 * Der BestellController wird benutzt, um Bestellungen und Bestellposten in die Datenbank zu schreiben.
 * Der ProduktController wird benutzt, um Produkte in Bestellposten hinzuzufügen.
 * Die UserSession wird benutzt, um wichtige Daten zwischenzulagern.
 *
 * @author Andrej Eichwald
 */
@Named(value = "vmBestellung")
@RequestScoped
public class ViewModelBestellung implements Serializable {
    
    
    @Inject
    BestellungController bc;
    @Inject
    ProduktController pc;
    @Inject
    UserSession uS;

    /**
     * Standardkonstruktor
     * 
     */
    public ViewModelBestellung(){
    }
    
    /**
     * Wird benötigt um auf die Änderungen des Dropdown-Menü zu reagieren.
     * Sobald ein Produkt ausgewählt wird, wird der Wert neu gesetzt.
     * 
     */
    public void valueChanged(){
    }
    
    /**
     * Es wird eine neue Bestellung angelegt mit dem übergebenen Namen des Kunden.
     * Die Bestellung wird zurückgegeben und in der UserSession gespeichert.
     *
     * @param kunde String Kundename für die Bestellung
     */
    public void newKunde(String kunde){
        uS.setBestellung(bc.newBestellung(kunde));
    }
    
    /**
     * Es wird für eine bestimmte Bestellung mit der übergebenen BestellungsId ein 
     * neuer Bestellposten angelegt. Dieser bekommt ein bestimmtes Produkt mit der 
     * übergebenen ProduktId. Außerdem wird noch die Anzahl der Produkte mit der 
     * Produktanzahl angegeben.
     *
     * @param bestellId Long Id der Bestellung, der der Bestellposten zugeordnet wird
     * @param produktId Long Id des Produkts, der dem Bestellposten zugeordnet wird
     * @param produktAnzahl int Anzahl der zu bestellenden Produkte
     */
    public void zumWarenkorbHinzufuegen(Long bestellId, Long produktId, int produktAnzahl){
        Bestellung b = bc.addBestellpostenToBestellung(bestellId,produktId,produktAnzahl);
        if(b != null){
        uS.setBestellung(b);
        }
    }
    
    /**
     * Es wird die Anzahl des Produkts eines bestehenden Bestellpostens geändert und 
     * wieder in die Datenbank geschrieben.
     *
     * @param bestellungId Long Id der zu bearbeitenden Bestellung
     * @param bestellPostenId Long Id des zu bearbeitenden Bestellpostens
     * @param anzahl int neue Anzahl des Produkts im Bestellposten
     */
    public void bestellPostenBearbeiten(Long bestellungId, Long bestellPostenId, int anzahl){
        uS.setBestellung(bc.updateBestellposten(bestellungId, bestellPostenId, anzahl));
        uS.setProduktAnzahl(1);
    }
    
    /**
     * Es wird ein bestimmter Bestellposten mit der übergebenen BestellId und 
     * BestellpostenId aus der Datenbank gelöscht
     *
     * @param bestellungId Long Id der Bestellung aus der der Bestellposten gelöscht werden soll
     * @param bestellPostenId Long Id des Bestellpostens, der gelöscht werden soll
     */
    public void bestellPostenLoeschen(Long bestellungId, Long bestellPostenId){
        Bestellung b = bc.deleteBestellposten(bestellungId, bestellPostenId);
        if(b != null){
            uS.setBestellung(b);
        }
        
    }
    
    
    /**
     * Es wird eine bestimmte Bestellung mit der übergebenen BestellungsId gelöscht.
     * Alle Parameter der UserSession werden zurückgesetzt.
     *
     * @param bestellungId Long Id der zu löschenden Bestellung
     */
    public void deleteBestellung(Long bestellungId){
        bc.deleteBestellung(bestellungId);
        uS.setBestellung(null);
        uS.setBestellPostenId(null);
        uS.setProduktAnzahl(1);
        uS.setProduktId(null);
        uS.setHideInput(true);
        uS.setHideList(true);
        uS.setBestellKunde(null);
    }
    
    /**
     * Es wird die Bestellung ausgeführt. 
     * Alle Parameter der UserSession werden zurückgesetzt.
     * Der Benutzer wird auf eine Seite weitergeleitet, wo er über eine 
     * erfolgreiche Bestellung informiert wird
     *
     * @return Weiterleitung zu einer "erfolgreiche Bestellung"-Seite
     */
    public String bestellen(Long bestellungId){
        if(uS.getBestellung().getBestellposten().isEmpty()){
            deleteBestellung(bestellungId);
            return "/Views/fehlgeschlagen_bestellung.xhtml";
        } else{
        uS.setBestellKunde(null);
        uS.setBestellPostenId(null);
        uS.setHideInput(true);
        uS.setHideList(true);
        uS.setProduktAnzahl(1);
        uS.setProduktId(null);
        uS.setBestellung(null);
        return "/Views/erfolgreiche_bestellung.xhtml";
        }
    }
    
    /**
     * Gibt eine Liste mit allen Bestellungen wieder, die sich in der Datenbank 
     * befinden.
     *
     * @return Liste aller vorhandenen Bestellungen
     */
    public List<Bestellung> getAllBestellungen(){
        return bc.getAllBestellung();
    }
}
