package rs.ac.bg.fon.ps.server.main;

import rs.ac.bg.fon.ps.server.thread.ServerThread;

public class ServerMain {

    public static void main(String[] args) {
        ServerThread serverThread = new ServerThread();
        serverThread.start();
    }
}