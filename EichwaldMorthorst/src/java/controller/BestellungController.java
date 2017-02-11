package controller;

import ejb.Persistence;
import entity.Bestellung;
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
    
    public Bestellung validateBestellung(Bestellung b) {
        return new Bestellung();
    }
}
