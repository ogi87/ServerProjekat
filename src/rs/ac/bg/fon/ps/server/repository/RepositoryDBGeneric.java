package rs.ac.bg.fon.ps.server.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import rs.ac.bg.fon.ps.common.domain.GenericEntity;
import rs.ac.bg.fon.ps.common.domain.Klijent;
import rs.ac.bg.fon.ps.common.domain.StavkaUsluge;
import rs.ac.bg.fon.ps.common.domain.Usluga;
import rs.ac.bg.fon.ps.common.domain.Zubar;
import rs.ac.bg.fon.ps.common.domain.ZubarKvalifikacija;
import rs.ac.bg.fon.ps.server.db.DbConnectionFactory;

public class RepositoryDBGeneric {

    public void save(GenericEntity entity) throws Exception {
        String query = "INSERT INTO " + entity.getTableName()
                + " (" + entity.getColumnNamesForInsert() + ") VALUES (" + entity.getInsertValues() + ")";

        Connection connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            entity.setId(rs.getLong(1));
        }

        rs.close();
        ps.close();
    }

    public List<GenericEntity> getAll(GenericEntity entity) throws Exception {
        String query = "SELECT * FROM " + entity.getTableName();

        Connection connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<GenericEntity> lista = entity.getListFromResultSet(rs);

        rs.close();
        ps.close();

        return lista;
    }

    public List<GenericEntity> getAllKlijent() throws Exception {
        String query = "SELECT k.id_klijent, k.ime, k.prezime, k.kontakt, "
                + "kk.id_kategorija, kk.naziv AS kategorija_naziv, kk.popust AS kategorija_popust "
                + "FROM klijent k "
                + "JOIN kategorija_klijenta kk ON k.id_kategorija = kk.id_kategorija";

        Connection connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        Klijent klijent = new Klijent();
        List<GenericEntity> lista = klijent.getListFromResultSet(rs);

        rs.close();
        ps.close();

        return lista;
    }

    public List<GenericEntity> searchKlijent(String kriterijum) throws Exception {
        String query = "SELECT k.id_klijent, k.ime, k.prezime, k.kontakt, "
                + "kk.id_kategorija, kk.naziv AS kategorija_naziv, kk.popust AS kategorija_popust "
                + "FROM klijent k "
                + "JOIN kategorija_klijenta kk ON k.id_kategorija = kk.id_kategorija "
                + "WHERE LOWER(k.ime) LIKE ? OR LOWER(k.prezime) LIKE ?";

        Connection connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);

        String like = "%" + kriterijum.toLowerCase() + "%";
        ps.setString(1, like);
        ps.setString(2, like);

        ResultSet rs = ps.executeQuery();

        Klijent klijent = new Klijent();
        List<GenericEntity> lista = klijent.getListFromResultSet(rs);

        rs.close();
        ps.close();

        return lista;
    }

    public List<GenericEntity> getAllZubar() throws Exception {
        String query = "SELECT id_zubar, ime, prezime, korisnicko_ime, sifra FROM zubar";

        Connection connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        Zubar zubar = new Zubar();
        List<GenericEntity> lista = zubar.getListFromResultSet(rs);

        rs.close();
        ps.close();

        return lista;
    }

    public List<GenericEntity> getAllUsluga() throws Exception {
        String query = "SELECT u.id_usluga, u.naziv, u.ukupan_iznos, u.popust, u.ukupan_iznos_sa_popustom, "
                + "z.id_zubar, z.ime AS zubar_ime, z.prezime AS zubar_prezime, "
                + "k.id_klijent, k.ime AS klijent_ime, k.prezime AS klijent_prezime "
                + "FROM usluga u "
                + "JOIN zubar z ON u.id_zubar = z.id_zubar "
                + "JOIN klijent k ON u.id_klijent = k.id_klijent";

        Connection connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        Usluga usluga = new Usluga();
        List<GenericEntity> lista = usluga.getListFromResultSet(rs);

        rs.close();
        ps.close();

        return lista;
    }

    public List<GenericEntity> searchUsluga(String kriterijum) throws Exception {
        String query = "SELECT u.id_usluga, u.naziv, u.ukupan_iznos, u.popust, u.ukupan_iznos_sa_popustom, "
                + "z.id_zubar, z.ime AS zubar_ime, z.prezime AS zubar_prezime, "
                + "k.id_klijent, k.ime AS klijent_ime, k.prezime AS klijent_prezime "
                + "FROM usluga u "
                + "JOIN zubar z ON u.id_zubar = z.id_zubar "
                + "JOIN klijent k ON u.id_klijent = k.id_klijent "
                + "WHERE LOWER(u.naziv) LIKE ?";

        Connection connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);

        String like = "%" + kriterijum.toLowerCase() + "%";
        ps.setString(1, like);

        ResultSet rs = ps.executeQuery();

        Usluga usluga = new Usluga();
        List<GenericEntity> lista = usluga.getListFromResultSet(rs);

        rs.close();
        ps.close();

        return lista;
    }

    public List<GenericEntity> getStavkeByUsluga(Usluga usluga) throws Exception {
        String query = "SELECT su.id_usluga, su.rb, su.kolicina, su.cena, su.iznos, "
                + "m.id_materijal, m.naziv AS materijal_naziv, m.cena AS materijal_cena "
                + "FROM stavka_usluge su "
                + "JOIN materijal m ON su.id_materijal = m.id_materijal "
                + "WHERE su.id_usluga = ? "
                + "ORDER BY su.rb";

        Connection connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, usluga.getUslugaId());

        ResultSet rs = ps.executeQuery();

        StavkaUsluge stavka = new StavkaUsluge();
        List<GenericEntity> lista = stavka.getListFromResultSet(rs);

        rs.close();
        ps.close();

        return lista;
    }

    public List<GenericEntity> getAllZubarKvalifikacija() throws Exception {
        String query = "SELECT zk.id_zubar, zk.id_kvalifikacija, zk.datum_sticanja, "
                + "z.ime AS zubar_ime, z.prezime AS zubar_prezime, "
                + "k.naziv AS kvalifikacija_naziv "
                + "FROM zubar_kvalifikacija zk "
                + "JOIN zubar z ON zk.id_zubar = z.id_zubar "
                + "JOIN kvalifikacija k ON zk.id_kvalifikacija = k.id_kvalifikacija";

        Connection connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        ZubarKvalifikacija zk = new ZubarKvalifikacija();
        List<GenericEntity> lista = zk.getListFromResultSet(rs);

        rs.close();
        ps.close();

        return lista;
    }

    public void delete(GenericEntity entity) throws Exception {
        String query = "DELETE FROM " + entity.getTableName()
                + " WHERE " + entity.getPrimaryKeyColumnName() + " = ?";

        Connection connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, entity.getPrimaryKeyValue());
        ps.executeUpdate();
        ps.close();
    }

    public void update(GenericEntity entity) throws Exception {
        String query = "UPDATE " + entity.getTableName()
                + " SET " + entity.getUpdateSetClause()
                + " WHERE " + entity.getPrimaryKeyColumnName() + " = ?";

        Connection connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, entity.getPrimaryKeyValue());
        ps.executeUpdate();
        ps.close();
    }

    public void deleteStavkeByUsluga(Usluga usluga) throws Exception {
        String query = "DELETE FROM stavka_usluge WHERE id_usluga = ?";

        Connection connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, usluga.getUslugaId());
        ps.executeUpdate();
        ps.close();
    }

    public void updateUslugaSaStavkama(Usluga usluga) throws Exception {
        update(usluga);
        deleteStavkeByUsluga(usluga);

        int rb = 1;
        for (StavkaUsluge stavka : usluga.getStavke()) {
            stavka.setUsluga(usluga);
            stavka.setRb(rb++);
            save(stavka);
        }
    }

    public void deleteZubarKvalifikacija(ZubarKvalifikacija zk) throws Exception {
        String query = "DELETE FROM zubar_kvalifikacija WHERE id_zubar = ? AND id_kvalifikacija = ?";

        Connection connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, zk.getZubar().getZubarId());
        ps.setLong(2, zk.getKvalifikacija().getKvalifikacijaId());
        ps.executeUpdate();
        ps.close();
    }
}