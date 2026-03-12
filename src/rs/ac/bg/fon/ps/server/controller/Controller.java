package rs.ac.bg.fon.ps.server.controller;

import java.util.List;
import rs.ac.bg.fon.ps.common.domain.GenericEntity;
import rs.ac.bg.fon.ps.common.domain.Klijent;
import rs.ac.bg.fon.ps.common.domain.Materijal;
import rs.ac.bg.fon.ps.common.domain.Usluga;
import rs.ac.bg.fon.ps.common.domain.Zubar;
import rs.ac.bg.fon.ps.common.domain.KategorijaKlijenta;
import rs.ac.bg.fon.ps.common.domain.Kvalifikacija;
import rs.ac.bg.fon.ps.common.domain.ZubarKvalifikacija;
import rs.ac.bg.fon.ps.server.so.klijent.*;
import rs.ac.bg.fon.ps.server.so.login.LoginSO;
import rs.ac.bg.fon.ps.server.so.materijal.*;
import rs.ac.bg.fon.ps.server.so.usluga.*;
import rs.ac.bg.fon.ps.server.so.zubar.*;
import rs.ac.bg.fon.ps.server.so.kategorijaklijenta.*;
import rs.ac.bg.fon.ps.server.so.kvalifikacija.*;
import rs.ac.bg.fon.ps.server.so.zubarkvalifikacija.*;


public class Controller {

    private static Controller instance;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Zubar login(String korisnickoIme, String sifra) throws Exception {
        Zubar z = new Zubar();
        z.setKorisnickoIme(korisnickoIme);
        z.setSifra(sifra);

        LoginSO so = new LoginSO();
        so.templateExecute(z);

        return so.getUlogovaniZubar();
    }

    public void addKlijent(Klijent klijent) throws Exception {
        AddKlijentSO so = new AddKlijentSO();
        so.templateExecute(klijent);
    }

    public List<GenericEntity> getAllKlijent() throws Exception {
        GetAllKlijentSO so = new GetAllKlijentSO();
        so.templateExecute(new Klijent());
        return so.getLista();
    }

    public void updateKlijent(Klijent klijent) throws Exception {
        UpdateKlijentSO so = new UpdateKlijentSO();
        so.templateExecute(klijent);
    }

    public void deleteKlijent(Klijent klijent) throws Exception {
        DeleteKlijentSO so = new DeleteKlijentSO();
        so.templateExecute(klijent);
    }

    public List<GenericEntity> searchKlijent(String kriterijum) throws Exception {
        Klijent k = new Klijent();
        k.setIme(kriterijum);

        SearchKlijentSO so = new SearchKlijentSO();
        so.templateExecute(k);

        return so.getLista();
    }

    public void addMaterijal(Materijal materijal) throws Exception {
        AddMaterijalSO so = new AddMaterijalSO();
        so.templateExecute(materijal);
    }

    public List<GenericEntity> getAllMaterijal() throws Exception {
        GetAllMaterijalSO so = new GetAllMaterijalSO();
        so.templateExecute(new Materijal());
        return so.getLista();
    }

    public void updateMaterijal(Materijal materijal) throws Exception {
        UpdateMaterijalSO so = new UpdateMaterijalSO();
        so.templateExecute(materijal);
    }

    public void deleteMaterijal(Materijal materijal) throws Exception {
        DeleteMaterijalSO so = new DeleteMaterijalSO();
        so.templateExecute(materijal);
    }

    public void addUsluga(Usluga usluga) throws Exception {
        AddUslugaSO so = new AddUslugaSO();
        so.templateExecute(usluga);
    }

    public List<GenericEntity> getAllUsluga() throws Exception {
        GetAllUslugaSO so = new GetAllUslugaSO();
        so.templateExecute(new Usluga());
        return so.getLista();
    }

    public void updateUsluga(Usluga usluga) throws Exception {
        UpdateUslugaSO so = new UpdateUslugaSO();
        so.templateExecute(usluga);
    }

    public List<GenericEntity> searchUsluga(String kriterijum) throws Exception {
        Usluga u = new Usluga();
        u.setNaziv(kriterijum);

        SearchUslugaSO so = new SearchUslugaSO();
        so.templateExecute(u);

        return so.getLista();
    }

    public List<GenericEntity> getStavkeByUsluga(Usluga usluga) throws Exception {
        GetStavkeByUslugaSO so = new GetStavkeByUslugaSO();
        so.templateExecute(usluga);
        return so.getLista();
    }

    public void addZubar(Zubar zubar) throws Exception {
        AddZubarSO so = new AddZubarSO();
        so.templateExecute(zubar);
    }

    public List<GenericEntity> getAllZubar() throws Exception {
        GetAllZubarSO so = new GetAllZubarSO();
        so.templateExecute(new Zubar());
        return so.getLista();
    }

    public void updateZubar(Zubar zubar) throws Exception {
        UpdateZubarSO so = new UpdateZubarSO();
        so.templateExecute(zubar);
    }

    public void deleteZubar(Zubar zubar) throws Exception {
        DeleteZubarSO so = new DeleteZubarSO();
        so.templateExecute(zubar);
    }

    public List<GenericEntity> getAllKategorijaKlijenta() throws Exception {
        GetAllKategorijaKlijentaSO so = new GetAllKategorijaKlijentaSO();
        so.templateExecute(new KategorijaKlijenta());
        return so.getLista();
    }

    public void addKategorijaKlijenta(KategorijaKlijenta kategorija) throws Exception {
        AddKategorijaKlijentaSO so = new AddKategorijaKlijentaSO();
        so.templateExecute(kategorija);
    }

    public void updateKategorijaKlijenta(KategorijaKlijenta kategorija) throws Exception {
        UpdateKategorijaKlijentaSO so = new UpdateKategorijaKlijentaSO();
        so.templateExecute(kategorija);
    }

    public void deleteKategorijaKlijenta(KategorijaKlijenta kategorija) throws Exception {
        DeleteKategorijaKlijentaSO so = new DeleteKategorijaKlijentaSO();
        so.templateExecute(kategorija);
    }

    public List<GenericEntity> getAllKvalifikacija() throws Exception {
        GetAllKvalifikacijaSO so = new GetAllKvalifikacijaSO();
        so.templateExecute(new Kvalifikacija());
        return so.getLista();
    }

    public void addKvalifikacija(Kvalifikacija kvalifikacija) throws Exception {
        AddKvalifikacijaSO so = new AddKvalifikacijaSO();
        so.templateExecute(kvalifikacija);
    }

    public void updateKvalifikacija(Kvalifikacija kvalifikacija) throws Exception {
        UpdateKvalifikacijaSO so = new UpdateKvalifikacijaSO();
        so.templateExecute(kvalifikacija);
    }

    public void deleteKvalifikacija(Kvalifikacija kvalifikacija) throws Exception {
        DeleteKvalifikacijaSO so = new DeleteKvalifikacijaSO();
        so.templateExecute(kvalifikacija);
    }

    public List<GenericEntity> getAllZubarKvalifikacija() throws Exception {
        GetAllZubarKvalifikacijaSO so = new GetAllZubarKvalifikacijaSO();
        so.templateExecute(new ZubarKvalifikacija());
        return so.getLista();
    }

    public void addZubarKvalifikacija(ZubarKvalifikacija zk) throws Exception {
        AddZubarKvalifikacijaSO so = new AddZubarKvalifikacijaSO();
        so.templateExecute(zk);
    }

    public void deleteZubarKvalifikacija(ZubarKvalifikacija zk) throws Exception {
        DeleteZubarKvalifikacijaSO so = new DeleteZubarKvalifikacijaSO();
        so.templateExecute(zk);
    }
}