package rs.ac.bg.fon.ps.server.thread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import rs.ac.bg.fon.ps.common.communication.Request;
import rs.ac.bg.fon.ps.common.communication.Response;
import rs.ac.bg.fon.ps.common.communication.ResponseType;
import rs.ac.bg.fon.ps.common.domain.GenericEntity;
import rs.ac.bg.fon.ps.common.domain.KategorijaKlijenta;
import rs.ac.bg.fon.ps.common.domain.Klijent;
import rs.ac.bg.fon.ps.common.domain.Kvalifikacija;
import rs.ac.bg.fon.ps.common.domain.Materijal;
import rs.ac.bg.fon.ps.common.domain.Usluga;
import rs.ac.bg.fon.ps.common.domain.Zubar;
import rs.ac.bg.fon.ps.common.domain.ZubarKvalifikacija;
import rs.ac.bg.fon.ps.common.operations.Operations;
import rs.ac.bg.fon.ps.server.controller.Controller;
import rs.ac.bg.fon.ps.server.so.usluga.KreirajUsluguSO;
import rs.ac.bg.fon.ps.server.so.usluga.ZapamtiUsluguSO;

public class HandleClientThread extends Thread {

    private Socket socket;

    public HandleClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            while (!socket.isClosed()) {
                Request request = (Request) in.readObject();
                Response response = new Response();

                try {
                    switch (request.getOperation()) {

                        case Operations.LOGIN: {
                        // Сада знамо да клијент шаље објекат Zubar, па га тако и преузимамо
                        Zubar zubar = (Zubar) request.getArgument(); 

                        // Прослеђујемо целог зубара у контролер
                        response.setResult(Controller.getInstance().login(zubar));
                        response.setResponseType(ResponseType.SUCCESS);
                        break;
                        }

                        case Operations.ADD_KLIJENT: {
                            Controller.getInstance().addKlijent((Klijent) request.getArgument());
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }
                        
                        case Operations.KREIRAJ_USLUGU: {
                            Usluga praznaUsluga = (Usluga) request.getArgument();
                            Usluga kreiranaUsluga = Controller.getInstance().kreirajUslugu(praznaUsluga);

                            // Vraćamo klijentu objekat koji sada u sebi ima popunjen ID (iz baze)
                            response.setResult(kreiranaUsluga); 
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }
                        case Operations.ZAPAMTI_USLUGU:{
                            Usluga uslugaZaPamcenje = (Usluga) request.getArgument();
                            Controller.getInstance().zapamtiUslugu(uslugaZaPamcenje);

                            response.setResult(null); // Ne vraćamo ništa specifično nazad
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }
                        
                        case Operations.KREIRAJ_KLIJENTA: {
                            Klijent noviKlijent = Controller.getInstance().kreirajKlijenta();
                            response.setResult(noviKlijent);
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }
                        case Operations.ZAPAMTI_KLIJENTA: {
                            Klijent klijentZaCuvanje = (Klijent) request.getArgument();
                            Controller.getInstance().zapamtiKlijenta(klijentZaCuvanje);
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }
                        
                        case Operations.GET_ALL_KLIJENT: {
                            List<GenericEntity> lista = Controller.getInstance().getAllKlijent();
                            response.setResult(lista);
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.UPDATE_KLIJENT: {
                            Controller.getInstance().updateKlijent((Klijent) request.getArgument());
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.DELETE_KLIJENT: {
                            Controller.getInstance().deleteKlijent((Klijent) request.getArgument());
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.SEARCH_KLIJENT: {
                            // Sada citamo Klijent objekat, a ne String!
                            Klijent kriterijum = (Klijent) request.getArgument();

                            // Prosledjujemo taj objekat serverskom kontroleru
                            List<GenericEntity> lista = Controller.getInstance().searchKlijent(kriterijum);

                            response.setResult(lista);
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.ADD_USLUGA: {
                            Controller.getInstance().addUsluga((Usluga) request.getArgument());
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.GET_ALL_USLUGA: {
                            List<GenericEntity> lista = Controller.getInstance().getAllUsluga();
                            response.setResult(lista);
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.UPDATE_USLUGA: {
                            Controller.getInstance().updateUsluga((Usluga) request.getArgument());
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.SEARCH_USLUGA: {
                            // 1. Sada primamo ceo objekat Usluga sa klijenta, a ne String
                            Usluga kriterijum = (Usluga) request.getArgument();

                            // 2. Prosleđujemo taj objekat kontroleru
                            List<GenericEntity> lista = Controller.getInstance().searchUsluga(kriterijum);

                            response.setResult(lista);
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.GET_STAVKE_BY_USLUGA: {
                            List<GenericEntity> lista = Controller.getInstance()
                                    .getStavkeByUsluga((Usluga) request.getArgument());
                            response.setResult(lista);
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.GET_ALL_KATEGORIJA_KLIJENTA: {
                            List<GenericEntity> lista = Controller.getInstance().getAllKategorijaKlijenta();
                            response.setResult(lista);
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.ADD_KATEGORIJA_KLIJENTA: {
                            Controller.getInstance().addKategorijaKlijenta((KategorijaKlijenta) request.getArgument());
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.UPDATE_KATEGORIJA_KLIJENTA: {
                            Controller.getInstance().updateKategorijaKlijenta((KategorijaKlijenta) request.getArgument());
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.DELETE_KATEGORIJA_KLIJENTA: {
                            Controller.getInstance().deleteKategorijaKlijenta((KategorijaKlijenta) request.getArgument());
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.ADD_ZUBAR: {
                            Controller.getInstance().addZubar((Zubar) request.getArgument());
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.GET_ALL_ZUBAR: {
                            List<GenericEntity> lista = Controller.getInstance().getAllZubar();
                            response.setResult(lista);
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.UPDATE_ZUBAR: {
                            Controller.getInstance().updateZubar((Zubar) request.getArgument());
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.DELETE_ZUBAR: {
                            Controller.getInstance().deleteZubar((Zubar) request.getArgument());
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.ADD_MATERIJAL: {
                            Controller.getInstance().addMaterijal((Materijal) request.getArgument());
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.GET_ALL_MATERIJAL: {
                            List<GenericEntity> lista = Controller.getInstance().getAllMaterijal();
                            response.setResult(lista);
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.UPDATE_MATERIJAL: {
                            Controller.getInstance().updateMaterijal((Materijal) request.getArgument());
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.DELETE_MATERIJAL: {
                            Controller.getInstance().deleteMaterijal((Materijal) request.getArgument());
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.ADD_KVALIFIKACIJA: {
                            Controller.getInstance().addKvalifikacija((Kvalifikacija) request.getArgument());
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.GET_ALL_KVALIFIKACIJA: {
                            List<GenericEntity> lista = Controller.getInstance().getAllKvalifikacija();
                            response.setResult(lista);
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.UPDATE_KVALIFIKACIJA: {
                            Controller.getInstance().updateKvalifikacija((Kvalifikacija) request.getArgument());
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.DELETE_KVALIFIKACIJA: {
                            Controller.getInstance().deleteKvalifikacija((Kvalifikacija) request.getArgument());
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.ADD_ZUBAR_KVALIFIKACIJA: {
                            Controller.getInstance().addZubarKvalifikacija((ZubarKvalifikacija) request.getArgument());
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.GET_ALL_ZUBAR_KVALIFIKACIJA: {
                            List<GenericEntity> lista = Controller.getInstance().getAllZubarKvalifikacija();
                            response.setResult(lista);
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        case Operations.DELETE_ZUBAR_KVALIFIKACIJA: {
                            Controller.getInstance().deleteZubarKvalifikacija((ZubarKvalifikacija) request.getArgument());
                            response.setResponseType(ResponseType.SUCCESS);
                            break;
                        }

                        default:
                            throw new Exception("Nepostojeca operacija.");
                    }

                } catch (Exception e) {
                    response.setResponseType(ResponseType.ERROR);
                    response.setException(e);
                }

                out.writeObject(response);
                out.flush();
            }

        } catch (Exception e) {
            System.out.println("Klijent je prekinuo konekciju.");
        }
    }
}