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
 * Der BestellungsController 
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
     *
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
     *
     * @return
     */
    public List<Bestellung> getAllBestellung() {
        return this.bestellungen;
    }
    
    /**
     *
     * @param kunde
     * @return
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
     *
     * @param bestellung_id
     * @param produkt_id
     * @param anzahl
     * @return
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
     *
     * @param bestellung_id
     * @param kunde
     * @return
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
                b.deleteBestellposten(bestellposten_id);
                ps.remove(b.getBestellposten(bestellposten_id));
                return b;
            }
        }
        return null;
    }
}
