package rs.ac.bg.fon.ps.server.so.materijal;

import rs.ac.bg.fon.ps.common.domain.GenericEntity;
import rs.ac.bg.fon.ps.common.domain.Materijal;
import rs.ac.bg.fon.ps.server.so.AbstractSO;

public class DeleteMaterijalSO extends AbstractSO {

    @Override
    protected void validate(GenericEntity entity) throws Exception {

        if (!(entity instanceof Materijal)) {
            throw new Exception("Prosledjeni objekat nije tipa Materijal.");
        }

        Materijal materijal = (Materijal) entity;

        if (materijal.getMaterijalId() == null) {
            throw new Exception("Materijal mora imati ID za brisanje.");
        }
    }

    @Override
    protected void execute(GenericEntity entity) throws Exception {
        broker.delete(entity);
    }
}