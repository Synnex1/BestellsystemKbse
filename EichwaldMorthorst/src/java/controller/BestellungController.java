package controller;

import ejb.Persistence;
import entity.Bestellposten;
import entity.Bestellung;
import entity.Produkt;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Der BestellungsController besitzt eine Liste aller in der Datenbank vorhandenen Bestellungen und verwaltet diese.
 * Er ist als Singleton umgesetzt, um somit eine zentrale Zugriffsstelle fuer Bestellungen zu bieten.
 *
 * @author Mike
 */
@Singleton
public class BestellungController {
    @Inject
    private ProduktController pc;
    @Inject
    private Persistence ps;
    private List<Bestellung> bestellungen;
    private Bestellung bestellung;
    
    /**
     * Wird ausgefuehrt nachdem die Injection der Persistenz und des ProduktControllers erfolgt sind.
     * Erstellt eine neue Bestellung zum bearbeiten und fuellt die Bestellungsliste mit allen Bestellungen aus der Datenbank 
     */
    @PostConstruct
    public void init() {
        this.bestellung = new Bestellung();
        this.bestellungen = allElements();
    }
    
    private List<Bestellung> allElements() {
        return ps.findAllBestellung();
    }
    
    /**
     * Gibt die Liste der Bestellungen zurueck.
     *
     * @return Eine Liste aller Bestellungen
     */
    public List<Bestellung> getAllBestellung() {
        return this.bestellungen;
    }
    
    /**
     * Erstellt eine neue Bestellung und fuegt den Namen des Kunden hinzu.
     * Anschließend wird die Bestellung in die Datenbank geschrieben und dem Frontend zurueckgegeben.
     *
     * @param kunde - Der name des Kunden
     * @return Die neu angelegte Bestellung
     */
    public Bestellung newBestellung(String kunde) {
        this.bestellung.setKunde(kunde);
        try {
            ps.persist(this.bestellung);
            this.bestellungen.add(this.bestellung);
        }catch (Exception e) {
            System.err.println("Fehler bei Erstellung einer Bestellung");
            this.bestellungen = allElements();
        }
        
        Bestellung b = this.bestellung;
        this.bestellung = new Bestellung();
        return b;
    }
    
    /**
     * Fuegt einer vorhanden Bestellung einen neuen Bestellposten hinzu. Dabei wird ueberprüft ob die zu bestellende Anzahl des Produktes 
     * an Bestand vorhanden ist. Falls Korrekt wird diese um die zu bestellende Anzahl vermindert. 
     *
     * @param bestellung_id - Id der Bestellung, der ein Bestellposten hinzugefuegt werden soll.
     * @param produkt_id - Id des Produktes, das bestellt werden soll.
     * @param anzahl - Anzahl des Produktes, die bestellt werden soll.
     * @return Die veraenderte Bestellung
     */
    public Bestellung addBestellpostenToBestellung(Long bestellung_id, Long produkt_id, int anzahl) {
        Produkt p = pc.checkProduktCountConstraint(produkt_id, anzahl);
        if(p == null) {
            return null;
        }
        
        for(Bestellung b : bestellungen) {
            if(b.getId().compareTo(bestellung_id) == 0) {
                Bestellposten bp = new Bestellposten(p, anzahl);
                ps.persist(bp);
                b.addBestellposten(bp);
                ps.merge(b);
                return b;
            }
        }
        return null;
    }
    
    /**
     * Aendert den Kundennamen einer Bestellung.
     *
     * @param bestellung_id - Id der Bestellung, dessen Kunde geaendert werden soll.
     * @param kunde - Name des neuen Kunden.
     * @return Die veraenderte Bestellung.
     */
    public Bestellung updateBestellung(Long bestellung_id, String kunde) {
        for(Bestellung b : bestellungen) {
            if(b.getId().compareTo(bestellung_id) == 0) {
                b.setKunde(kunde);
                ps.merge(b);
                return b;
            }
        }
        return null;
    }
    
    /**
     * Aendert einen Bestellposten einer Bestellung. 
     *
     * @param bestellung_id
     * @param bestellposten_id
     * @param neueAnzahl
     * @return
     */
    public Bestellung updateBestellposten(Long bestellung_id, Long bestellposten_id, int neueAnzahl) {
        for(Bestellung b : this.bestellungen) {
            if(b.getId().compareTo(bestellung_id) == 0) {
                Bestellposten bp = b.getBestellposten(bestellposten_id);
                if(bp.getAnzahl() > neueAnzahl) {
                    int neuerBestand = bp.getProdukt().getAnzahl() + bp.getAnzahl() - neueAnzahl;
                    pc.updateProduktCount(bp.getProdukt().getId(), neuerBestand);
                    bp.setAnzahl(neueAnzahl);
                    ps.merge(bp);
                    return b;
                } else if(bp.getAnzahl() < neueAnzahl) {
                    int differenz = neueAnzahl - bp.getAnzahl();
                    if(pc.checkProduktCountConstraint(bp.getProdukt().getId(), differenz) != null) {
                        bp.setAnzahl(neueAnzahl);
                        ps.merge(bp);
                        return b;
                    }
                }
            }
        }
        return null;
    }
    
    /**
     *
     * @param bestellung_id
     * @param bestellposten_id
     * @return
     */
    public Bestellung deleteBestellposten(Long bestellung_id, Long bestellposten_id) {
        for(Bestellung b : this.bestellungen) {
            if(b.getId().compareTo(bestellung_id) == 0) {
                ps.removeBestellposten(bestellposten_id);
                b.deleteBestellposten(bestellposten_id);
                return b;
            }
        }
        return null;
    }
}
