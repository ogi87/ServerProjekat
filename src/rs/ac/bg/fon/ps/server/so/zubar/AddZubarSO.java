package rs.ac.bg.fon.ps.server.so.zubar;

import rs.ac.bg.fon.ps.common.domain.GenericEntity;
import rs.ac.bg.fon.ps.common.domain.Zubar;
import rs.ac.bg.fon.ps.server.so.AbstractSO;

public class AddZubarSO extends AbstractSO {

    @Override
    protected void validate(GenericEntity entity) throws Exception {
        if (!(entity instanceof Zubar)) {
            throw new Exception("Prosledjeni objekat nije tipa Zubar.");
        }

        Zubar zubar = (Zubar) entity;

        if (zubar.getIme() == null || zubar.getIme().trim().isEmpty()) {
            throw new Exception("Ime zubara je obavezno.");
        }

        if (zubar.getPrezime() == null || zubar.getPrezime().trim().isEmpty()) {
            throw new Exception("Prezime zubara je obavezno.");
        }

        if (zubar.getKorisnickoIme() == null || zubar.getKorisnickoIme().trim().isEmpty()) {
            throw new Exception("Korisnicko ime je obavezno.");
        }

        if (zubar.getSifra() == null || zubar.getSifra().trim().isEmpty()) {
            throw new Exception("Sifra je obavezna.");
        }
    }

    @Override
    protected void execute(GenericEntity entity) throws Exception {
        broker.save(entity);
    }
}