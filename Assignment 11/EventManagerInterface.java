// Rachele Puri 547938

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface EventManagerInterface extends Remote {

    // Restituisce true se l'utente è stato correttamente registrato false se
    // l'utente era già registrato.
    boolean registerUser(String username, int giorno, int sessione) throws RemoteException, IllegalArgumentException;

    // Restituisce la durata del congresso espressa in giorni.
    int getnumGiorni() throws RemoteException;

    // Restituisce il numero di sessioni in un giorno.
    int getnumSessioni() throws RemoteException;

    // Restituisce il numero di interventi contenuti in una sessione.
    int getnumInterventi() throws RemoteException;

    // Restitisce il programma del congresso.
    public ArrayList<ArrayList<ArrayList<String>>> getCongresso() throws RemoteException;

    // Restituisce il programma di uno specifico giorno.
    public ArrayList<ArrayList<String>> getGiornoCongresso(int giorno) throws RemoteException, IllegalArgumentException;
}