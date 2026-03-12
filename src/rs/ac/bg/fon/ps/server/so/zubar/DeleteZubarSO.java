package rs.ac.bg.fon.ps.server.so.zubar;

import rs.ac.bg.fon.ps.common.domain.GenericEntity;
import rs.ac.bg.fon.ps.common.domain.Zubar;
import rs.ac.bg.fon.ps.server.so.AbstractSO;

public class DeleteZubarSO extends AbstractSO {

    @Override
    protected void validate(GenericEntity entity) throws Exception {

        if (!(entity instanceof Zubar)) {
            throw new Exception("Prosledjeni objekat nije tipa Zubar.");
        }

        Zubar zubar = (Zubar) entity;

        if (zubar.getZubarId() == null) {
            throw new Exception("Zubar mora imati ID za brisanje.");
        }
    }

    @Override
    protected void execute(GenericEntity entity) throws Exception {
        broker.delete(entity);
    }
}