package rs.ac.bg.fon.ps.server.so.materijal;

import rs.ac.bg.fon.ps.common.domain.GenericEntity;
import rs.ac.bg.fon.ps.common.domain.Materijal;
import rs.ac.bg.fon.ps.server.so.AbstractSO;

public class AddMaterijalSO extends AbstractSO {

    @Override
    protected void validate(GenericEntity entity) throws Exception {

        if (!(entity instanceof Materijal)) {
            throw new Exception("Prosledjeni objekat nije tipa Materijal.");
        }

        Materijal materijal = (Materijal) entity;

        if (materijal.getNaziv() == null || materijal.getNaziv().trim().isEmpty()) {
            throw new Exception("Naziv materijala je obavezan.");
        }

        if (materijal.getCena() <= 0) {
            throw new Exception("Cena mora biti veca od 0.");
        }
    }

    @Override
    protected void execute(GenericEntity entity) throws Exception {
        broker.save(entity);
    }
}