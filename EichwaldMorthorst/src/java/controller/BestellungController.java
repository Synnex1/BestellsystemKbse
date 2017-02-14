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
    private Bestellung bestellung;
    
    @PostConstruct
    public void init() {
        this.bestellung = new Bestellung();
        this.bestellungen = allElements();
    }
    
    private List<Bestellung> allElements() {
        return ps.findAllBestellung();
    }
    
    public List<Bestellung> getAllBestellung() {
        return this.bestellungen;
    }
    
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
    
    public Bestellung addBestellpostenToBestellung(Long bestellung_id, Long produkt_id, int anzahl) {
        Produkt p = pc.findProdukt(produkt_id);
        if(p.getAnzahl() < anzahl) {
            return null;
        }else {
            p.setAnzahl(p.getAnzahl()-anzahl);
            ps.merge(p);
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
