package rs.ac.bg.fon.ps.server.so.kvalifikacija;

import rs.ac.bg.fon.ps.common.domain.GenericEntity;
import rs.ac.bg.fon.ps.common.domain.Kvalifikacija;
import rs.ac.bg.fon.ps.server.so.AbstractSO;

public class DeleteKvalifikacijaSO extends AbstractSO {

    @Override
    protected void validate(GenericEntity entity) throws Exception {

        if (!(entity instanceof Kvalifikacija)) {
            throw new Exception("Prosledjeni objekat nije tipa Kvalifikacija.");
        }

        Kvalifikacija kvalifikacija = (Kvalifikacija) entity;

        if (kvalifikacija.getKvalifikacijaId() == null) {
            throw new Exception("Kvalifikacija mora imati ID za brisanje.");
        }
    }

    @Override
    protected void execute(GenericEntity entity) throws Exception {
        broker.delete(entity);
    }
}