// Rachele Puri 547938

import java.net.UnknownHostException;

public class MainClient {
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
            // Crea e avvia il TimeClient.
            TimeClient client = new TimeClient(indirizzoIP, DEFAULT_DATA_PORT);
            client.start();
        } catch (UnknownHostException e) { // L'indirizzo passato non è valido.
            System.err.println("L'indirizzo immesso non e' valido");
        } catch (IllegalArgumentException e) { // L'indirizzo passato non è un indirizzo multicast.
            System.err.println("L'indirizzo immesso non e' un indirizzo multicast");
        }
    }
}