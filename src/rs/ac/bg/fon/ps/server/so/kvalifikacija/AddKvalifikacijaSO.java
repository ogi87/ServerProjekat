package rs.ac.bg.fon.ps.server.so.kvalifikacija;

import rs.ac.bg.fon.ps.common.domain.GenericEntity;
import rs.ac.bg.fon.ps.common.domain.Kvalifikacija;
import rs.ac.bg.fon.ps.server.so.AbstractSO;

public class AddKvalifikacijaSO extends AbstractSO {

    @Override
    protected void validate(GenericEntity entity) throws Exception {
        if (!(entity instanceof Kvalifikacija)) {
            throw new Exception("Прослеђени објекат није типа Квалификација.");
        }
        Kvalifikacija k = (Kvalifikacija) entity;
        
        // Валидација (Корак 2 на страни сервера)
        if (k.getNaziv() == null || k.getNaziv().trim().isEmpty()) {
            throw new Exception("Назив квалификације је обавезан!");
        }
    }

    @Override
    protected void execute(GenericEntity entity) throws Exception {
        // Позивамо генеричку методу за чување из брокера
        broker.save(entity);
    }
}