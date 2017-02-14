package controller;

import ejb.Persistence;
import entity.Bestellposten;
import entity.Bestellung;
import entity.Produkt;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BestellungController {
    @Inject
    private ProduktController pc;
    @Inject
    private Persistence ps;
    private List<Bestellung> bestellungen;
    
    @PostConstruct
    public void init() {
        this.bestellungen = allElements();
    }
    
    private List<Bestellung> allElements() {
        return ps.findAllBestellung();
    }
    
    public List<Bestellung> getAllBestellung() {
        return this.bestellungen;
    }
    
    public Bestellung newBestellung(String kunde) {
        Bestellung b = new Bestellung();
        b.setKunde(kunde);
        ps.persist(b);
        this.bestellungen.add(b);
        return b;
    }
    
    public Bestellung addBestellpostenToBestellung(Long bestellung_id, Long produkt_id, int anzahl) {
        Produkt p = pc.findProdukt(produkt_id);
        if(p.getAnzahl() < anzahl) {
            return null;
        }
        this.bestellungen = allElements();
        for(Bestellung b : bestellungen) {
            if(b.getId().compareTo(bestellung_id) == 0) {
                b.addBestellposten(p, anzahl);
                ps.merge(b);
                return b;
            }
        }
        return null;
    }
    
    public Bestellung updateBestellung(Long bestellung_id, String kunde) {
        for(Bestellung b : bestellungen) {
            if(b.getId().compareTo(bestellung_id) == 0) {
                b.setKunde(kunde);
                ps.persist(b);
                return b;
            }
        }
        return null;
    }
}
