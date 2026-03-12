package rs.ac.bg.fon.ps.server.so.usluga;

import rs.ac.bg.fon.ps.common.domain.GenericEntity;
import rs.ac.bg.fon.ps.common.domain.StavkaUsluge;
import rs.ac.bg.fon.ps.common.domain.Usluga;
import rs.ac.bg.fon.ps.server.so.AbstractSO;

public class AddUslugaSO extends AbstractSO {

    @Override
    protected void validate(GenericEntity entity) throws Exception {

        if (!(entity instanceof Usluga)) {
            throw new Exception("Prosledjeni objekat nije tipa Usluga.");
        }

        Usluga usluga = (Usluga) entity;

        if (usluga.getNaziv() == null || usluga.getNaziv().trim().isEmpty()) {
            throw new Exception("Naziv usluge je obavezan.");
        }

        if (usluga.getZubar() == null || usluga.getZubar().getZubarId() == null) {
            throw new Exception("Zubar je obavezan.");
        }

        if (usluga.getKlijent() == null || usluga.getKlijent().getKlijentId() == null) {
            throw new Exception("Klijent je obavezan.");
        }

        if (usluga.getStavke() == null || usluga.getStavke().isEmpty()) {
            throw new Exception("Usluga mora imati bar jednu stavku.");
        }

        for (StavkaUsluge stavka : usluga.getStavke()) {
            if (stavka.getMaterijal() == null || stavka.getMaterijal().getMaterijalId() == null) {
                throw new Exception("Svaka stavka mora imati materijal.");
            }

            if (stavka.getKolicina() <= 0) {
                throw new Exception("Kolicina mora biti veca od 0.");
            }
        }
    }

    @Override
    protected void execute(GenericEntity entity) throws Exception {
        Usluga usluga = (Usluga) entity;

        broker.save(usluga);

        int rb = 1;
        for (StavkaUsluge stavka : usluga.getStavke()) {
            stavka.setUsluga(usluga);
            stavka.setRb(rb++);
            broker.save(stavka);
        }
    }
}