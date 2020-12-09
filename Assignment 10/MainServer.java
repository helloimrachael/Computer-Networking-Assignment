// Rachele Puri 547938

import java.net.UnknownHostException;

public class MainServer {
    // Porta associata all'indirizzo di multicast.
    final static int DEFAULT_DATA_PORT = 30000;

    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                System.out.println("Usage: insert IP address");
                return;
            }
            // Indirizzo di broadcast.
            String indirizzoIP = args[0];
            // Crea e avvia il TimeServer.
            TimeServer server = new TimeServer(indirizzoIP, DEFAULT_DATA_PORT);
            server.start();
        } catch (UnknownHostException e) { // l'indirizzo passato non è valido
            System.err.println("L'indirizzo immesso non e' valido");
        } catch (IllegalArgumentException e) { // l'indirizzo passato non è un indirizzo multicast
            System.err.println("L'indirizzo immesso non e' un indirizzo multicast");
        }
    }
}
