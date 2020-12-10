// Rachele Puri 547938

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MainServer {
    private static final int PORT_DEFAULT = 5000;

    public static void main(String[] args) {
        int port = PORT_DEFAULT;
        try {
            EventManager eventManager = new EventManager(3, 12, 5);
            // Esporta l'oggetto.
            EventManagerInterface stub = (EventManagerInterface) UnicastRemoteObject.exportObject(eventManager, port);

            // Crea il registro.
            LocateRegistry.createRegistry(port);
            Registry register = LocateRegistry.getRegistry(port);

            // Binding.
            register.rebind("EVENT_MANAGER", stub);
            System.out.println("Server pronto");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}