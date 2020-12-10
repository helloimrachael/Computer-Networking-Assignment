// Rachele Puri 547938

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.util.ArrayList;

public class EventManager extends RemoteServer implements EventManagerInterface {
    private ArrayList<ArrayList<ArrayList<String>>> congresso;
    private int numGiorni;
    private int numSessioni;
    private int numInterventi;

    public EventManager(int numGiorni, int numSessioni, int numInterventi) {
        this.numGiorni = numGiorni;
        this.numSessioni = numSessioni;
        this.numInterventi = numInterventi;
        congresso = new ArrayList<>(numGiorni);
        // Creo la mia tabella che conterrà il nome dell'utente registrato per la
        // sessione x del giorno y.
        // FORMATO STAMPA TABELLA
        /*
         * Giorno 0: 
            *  Sessione 0: [] 
            *  Sessione 1: []
            *  . 
            *  . 
            *  . 
            *  Sessione 11: []
         * Giorno 2:
            *  Sessione 0: []
            *  Sessione 1: []
            *  .
            *  .
            *  .
            *  Sessione 11: []
         * Giorno 2:
            *  Sessione 0: []
            *  Sessione 1: []
            *  .
            *  .
            *  .
            *  Sessione 11: []
         */
        for (int i = 0; i < numGiorni; i++) {
            congresso.add(i, new ArrayList<>(numSessioni));
        }
        for (int i = 0; i < numGiorni; i++) {
            for (int j = 0; j < numSessioni; j++) {
                congresso.get(i).add(j, new ArrayList<>(numInterventi));
            }
        }
    }

    @Override
    public synchronized boolean registerUser(String username, int giorno, int sessione)
            throws RemoteException, IllegalArgumentException {
        if (username == null)
            throw new IllegalArgumentException();
        if (giorno >= 0 && giorno < numGiorni) {
            if (sessione >= 0 && sessione < numSessioni) {
                if (congresso.get(giorno).get(sessione).size() < numInterventi) {
                    return congresso.get(giorno).get(sessione).add(username);
                } else {
                    throw new RemoteException(
                            "ERROR: non ci sono più interventi liberi per questa sessione in questo giorno");
                }
            } else {
                throw new IllegalArgumentException("ERROR: numero sessione errato!");
            }
        } else {
            throw new IllegalArgumentException("ERROR: numero giorno errato!");
        }
    }

    @Override
    public int getnumGiorni() throws RemoteException {
        return numGiorni;
    }

    @Override
    public int getnumSessioni() throws RemoteException {
        return numSessioni;
    }

    @Override
    public int getnumInterventi() throws RemoteException {
        return numInterventi;
    }

    @Override
    public ArrayList<ArrayList<ArrayList<String>>> getCongresso() throws RemoteException {
        return congresso;
    }

    @Override
    public ArrayList<ArrayList<String>> getGiornoCongresso(int giorno)
            throws RemoteException, IllegalArgumentException {
        if (giorno < 0 || giorno >= numGiorni)
            throw new IllegalArgumentException("ERROR: numero giorno errato!");
        else
            return congresso.get(giorno);
    }
}
