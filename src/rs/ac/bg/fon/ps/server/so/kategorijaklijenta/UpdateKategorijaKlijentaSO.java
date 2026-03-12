package rs.ac.bg.fon.ps.server.so.kategorijaklijenta;

import rs.ac.bg.fon.ps.common.domain.GenericEntity;
import rs.ac.bg.fon.ps.common.domain.KategorijaKlijenta;
import rs.ac.bg.fon.ps.server.so.AbstractSO;

public class UpdateKategorijaKlijentaSO extends AbstractSO {

    @Override
    protected void validate(GenericEntity entity) throws Exception {

        if (!(entity instanceof KategorijaKlijenta)) {
            throw new Exception("Prosledjeni objekat nije tipa KategorijaKlijenta.");
        }

        KategorijaKlijenta kategorija = (KategorijaKlijenta) entity;

        if (kategorija.getKategorijaId() == null) {
            throw new Exception("Kategorija mora imati ID za izmenu.");
        }

        if (kategorija.getNaziv() == null || kategorija.getNaziv().trim().isEmpty()) {
            throw new Exception("Naziv kategorije je obavezan.");
        }

        if (kategorija.getPopust() < 0) {
            throw new Exception("Popust ne moze biti negativan.");
        }
    }

    @Override
    protected void execute(GenericEntity entity) throws Exception {
        broker.update(entity);
    }
}