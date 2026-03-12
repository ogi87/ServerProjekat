package rs.ac.bg.fon.ps.server.so.zubarkvalifikacija;

import java.util.List;
import rs.ac.bg.fon.ps.common.domain.GenericEntity;
import rs.ac.bg.fon.ps.common.domain.ZubarKvalifikacija;
import rs.ac.bg.fon.ps.server.so.AbstractSO;

public class GetAllZubarKvalifikacijaSO extends AbstractSO {

    private List<GenericEntity> lista;

    public List<GenericEntity> getLista() {
        return lista;
    }

    @Override
    protected void validate(GenericEntity entity) throws Exception {
        if (!(entity instanceof ZubarKvalifikacija)) {
            throw new Exception("Prosledjeni objekat nije tipa ZubarKvalifikacija.");
        }
    }

    @Override
    protected void execute(GenericEntity entity) throws Exception {
        lista = broker.getAllZubarKvalifikacija();
    }
}