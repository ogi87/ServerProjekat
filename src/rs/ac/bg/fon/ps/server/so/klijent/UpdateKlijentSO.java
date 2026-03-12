package rs.ac.bg.fon.ps.server.so.klijent;

import rs.ac.bg.fon.ps.common.domain.GenericEntity;
import rs.ac.bg.fon.ps.common.domain.Klijent;
import rs.ac.bg.fon.ps.server.so.AbstractSO;

public class UpdateKlijentSO extends AbstractSO {

    @Override
    protected void validate(GenericEntity entity) throws Exception {

        if (!(entity instanceof Klijent)) {
            throw new Exception("Prosledjeni objekat nije tipa Klijent.");
        }

        Klijent klijent = (Klijent) entity;

        if (klijent.getKlijentId() == null) {
            throw new Exception("Klijent mora imati ID za izmenu.");
        }

        if (klijent.getIme() == null || klijent.getIme().trim().isEmpty()) {
            throw new Exception("Ime klijenta je obavezno.");
        }

        if (klijent.getPrezime() == null || klijent.getPrezime().trim().isEmpty()) {
            throw new Exception("Prezime klijenta je obavezno.");
        }

        if (klijent.getKontakt() == null || klijent.getKontakt().trim().isEmpty()) {
            throw new Exception("Kontakt klijenta je obavezan.");
        }

        if (klijent.getKategorija() == null || klijent.getKategorija().getKategorijaId() == null) {
            throw new Exception("Kategorija klijenta je obavezna.");
        }
    }

    @Override
    protected void execute(GenericEntity entity) throws Exception {
        broker.update(entity);
    }
}