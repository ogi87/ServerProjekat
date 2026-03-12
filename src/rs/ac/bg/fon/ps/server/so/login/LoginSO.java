package rs.ac.bg.fon.ps.server.so.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import rs.ac.bg.fon.ps.common.domain.GenericEntity;
import rs.ac.bg.fon.ps.common.domain.Zubar;
import rs.ac.bg.fon.ps.server.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.server.so.AbstractSO;

public class LoginSO extends AbstractSO {

    private Zubar ulogovaniZubar;

    public Zubar getUlogovaniZubar() {
        return ulogovaniZubar;
    }

    @Override
    protected void validate(GenericEntity entity) throws Exception {
        if (!(entity instanceof Zubar)) {
            throw new Exception("Prosledjeni objekat nije tipa Zubar.");
        }
    }

    @Override
    protected void execute(GenericEntity entity) throws Exception {
        Zubar z = (Zubar) entity;

        String sql = "SELECT id_zubar, ime, prezime, korisnicko_ime, sifra "
                + "FROM zubar WHERE korisnicko_ime = ? AND sifra = ?";

        Connection connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, z.getKorisnickoIme());
        ps.setString(2, z.getSifra());

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            ulogovaniZubar = new Zubar(
                    rs.getLong("id_zubar"),
                    rs.getString("ime"),
                    rs.getString("prezime"),
                    rs.getString("korisnicko_ime"),
                    rs.getString("sifra")
            );
        } else {
            throw new Exception("Ne postoji zubar sa tim kredencijalima.");
        }

        rs.close();
        ps.close();
    }
}