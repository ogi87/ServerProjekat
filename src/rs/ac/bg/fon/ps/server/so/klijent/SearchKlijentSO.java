package rs.ac.bg.fon.ps.server.so.klijent;

import java.util.List;
import rs.ac.bg.fon.ps.common.domain.GenericEntity;
import rs.ac.bg.fon.ps.common.domain.Klijent;
import rs.ac.bg.fon.ps.server.so.AbstractSO;

public class SearchKlijentSO extends AbstractSO {

    private List<GenericEntity> lista;

    public List<GenericEntity> getLista() {
        return lista;
    }

    @Override
    protected void validate(GenericEntity entity) throws Exception {
        if (!(entity instanceof Klijent)) {
            throw new Exception("Prosledjeni objekat nije tipa Klijent.");
        }
    }

    @Override
    protected void execute(GenericEntity entity) throws Exception {
        Klijent klijent = (Klijent) entity;
        String kriterijum = klijent.getIme() == null ? "" : klijent.getIme().trim();
        lista = broker.searchKlijent(kriterijum);
    }
}