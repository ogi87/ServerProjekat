package rs.ac.bg.fon.ps.server.so.zubarkvalifikacija;

import rs.ac.bg.fon.ps.common.domain.GenericEntity;
import rs.ac.bg.fon.ps.common.domain.ZubarKvalifikacija;
import rs.ac.bg.fon.ps.server.so.AbstractSO;

public class AddZubarKvalifikacijaSO extends AbstractSO {

    @Override
    protected void validate(GenericEntity entity) throws Exception {

        if (!(entity instanceof ZubarKvalifikacija)) {
            throw new Exception("Prosledjeni objekat nije tipa ZubarKvalifikacija.");
        }

        ZubarKvalifikacija zk = (ZubarKvalifikacija) entity;

        if (zk.getZubar() == null || zk.getZubar().getZubarId() == null) {
            throw new Exception("Zubar je obavezan.");
        }

        if (zk.getKvalifikacija() == null || zk.getKvalifikacija().getKvalifikacijaId() == null) {
            throw new Exception("Kvalifikacija je obavezna.");
        }

        if (zk.getDatumSticanja() == null) {
            throw new Exception("Datum sticanja je obavezan.");
        }
    }

    @Override
    protected void execute(GenericEntity entity) throws Exception {
        broker.save(entity);
    }
}