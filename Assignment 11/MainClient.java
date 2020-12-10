// Rachele Puri 547938

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClient {
    private static final int PORT_DEFAULT = 5000;

    public static void main(String[] args) {
        int port = PORT_DEFAULT;
        if (args.length > 0 && args.length != 1) {
            System.out.println("USAGE: java MainClient username");
            return;
        }
        String username = args[0];
        Scanner scanner = new Scanner(System.in);
        try {
            // Ottiene una reference per il registro.
            Registry r = LocateRegistry.getRegistry(port);
            // Ottiene una reference all'event manager.
            EventManagerInterface remoteEventManager = (EventManagerInterface) r.lookup("EVENT_MANAGER");
            System.out.println("Il congresso ha una durata di " + remoteEventManager.getnumGiorni() + " giorni");
            System.out.println("Ogni giornata è composta da " + remoteEventManager.getnumSessioni() + " sessioni");
            System.out.println("In ogni sessione ci sono " + remoteEventManager.getnumInterventi() + " interventi");
            int op = -1;
            while (op != 0) {
                int sessione;
                int giorno;
                System.out.println("Inserisci 0 per terminare");
                System.out.println("Inserisci 1 per registrare uno speaker ad una sessione");
                System.out.println("Inserisci 2 per stampare l'intero programma");
                System.out.println("Inserisci 3 per stampare il programma di uno specifico giorno");
                // Leggo op da terminale.
                op = scanner.nextInt();
                if (op == 0) {
                    System.out.println("Chiusura del client.");
                    scanner.close();
                    break;
                } else if (op == 1) {
                    System.out.println("Inserire il giorno indicando un numero da 0 a 6");
                    giorno = scanner.nextInt();
                    System.out.println("Inserire il numero della sessione indicando un numero da 0 a 11");
                    sessione = scanner.nextInt();
                    try {
                        // Registra l'utente all'evento.
                        registerUser(username, remoteEventManager, giorno, sessione);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (op == 2) {
                    ArrayList<ArrayList<ArrayList<String>>> congresso = remoteEventManager.getCongresso();
                    for (int i = 0; i < congresso.size(); i++) {
                        System.out.println("Giorno: " + (i));
                        for (int j = 0; j < congresso.get(i).size(); j++) {
                            System.out.print("Sessione " + (j) + ": \t");
                            System.out.println(congresso.get(i).get(j));
                        }
                        System.out.printf("\n");
                    }
                } else if (op == 3) {
                    System.out.println("Inserire il giorno indicando un numero da 0 a 6");
                    giorno = scanner.nextInt();
                    try {
                        ArrayList<ArrayList<String>> congressoGiornoX = remoteEventManager.getGiornoCongresso(giorno);
                        System.out.println("Giorno: " + (giorno));
                        for (int j = 0; j < congressoGiornoX.size(); j++) {
                            System.out.print("Sessione " + (j) + ": \t");
                            System.out.println(congressoGiornoX.get(j));
                        }
                        System.out.printf("\n");
                    } catch (Exception e) {
                        System.err.println();
                    }
                } else {
                    System.out.println("USAGE: inserire un numero da 0 a 3 per indicare che operazione scegliere.");
                }
            }
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    private static boolean registerUser(String name, EventManagerInterface remoteEventManager, int giorno,
            int sessione) {
        System.out.println("Richiedo la registrazione di " + name);
        try {
            if (remoteEventManager.registerUser(name, giorno, sessione)) {
                System.out.printf("L'utente \"%s\" è stato registrato con successo\n", name);
                return true;
            } else {
                System.out.printf("L'utente \"%s\" era già registrato\n", name);
            }
        } catch (RemoteException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }
}