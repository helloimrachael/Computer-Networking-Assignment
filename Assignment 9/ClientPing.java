import java.io.IOException;
import java.net.*;
import java.sql.Timestamp;

public class ClientPing {
    // Porta su cui è in ascolto il client.
    private int porta;
    // Porta su cui è in ascolto il server.
    private int portaServer;
    // Hostname del server.
    private String nomeServer;
    // Attesa per la ricezione di una risposta da parte del server(ms).
    private final int timeout;

    public ClientPing(int portaServer, String nomeServer, int timeout) {
        this.porta = 30000;
        this.timeout = timeout;
        this.portaServer = portaServer;
        this.nomeServer = nomeServer;
    }

    public void start() {
        String PING = null;
        long RTT = 0;
        int numPacchettiRicevuti = 0;
        long min = 0;
        long max = 0;
        float media = 0;
        long sommaRTT = 0;
        long numRTT = 0;
        try (DatagramSocket client = new DatagramSocket(porta)) {
            for (int i = 0; i < 10; i++) {
                PING = "PING " + i + System.nanoTime();
                byte[] messaggio = PING.getBytes();
                DatagramPacket pacchetto = new DatagramPacket(messaggio, messaggio.length,
                        InetAddress.getByName(this.nomeServer), this.portaServer);
                Timestamp tempoInvio = new Timestamp(System.currentTimeMillis());
                client.send(pacchetto);
                // Imposta il timeout.
                client.setSoTimeout(timeout);
                byte[] buffer = new byte[PING.length()];
                DatagramPacket pacchettoRicevuto = new DatagramPacket(buffer, buffer.length);
                try {
                    // Se il timer non è scaduto, rimane in attesa della risposta del server.
                    client.receive(pacchettoRicevuto);
                    String messaggioRicevuto = new String(pacchettoRicevuto.getData());
                    if (messaggioRicevuto.equals(PING)) {
                        numPacchettiRicevuti++;
                        Timestamp tempoRicezione = new Timestamp(System.currentTimeMillis());
                        RTT = tempoRicezione.getTime() - tempoInvio.getTime();
                        if (min == 0) {
                            min = RTT;
                        }
                        if (RTT < min) {
                            min = RTT;
                        }
                        if (RTT > max) {
                            max = RTT;
                        }
                        sommaRTT = sommaRTT + RTT;
                        numRTT++;
                        System.out.println(PING + " RTT: " + RTT + " ms");
                    }
                } catch (Exception e) {
                    System.out.println(PING + " RTT: *");
                }
            }
            int percentulePacchetti = 100 - numPacchettiRicevuti * 10;
            media = (float) sommaRTT / numRTT;
            System.out.println("---- PING Statistics ----");
            System.out.println("10 packets trasmitted, " + numPacchettiRicevuti + " packets received, "
                    + percentulePacchetti + "%");
            System.out.print("round-trip (ms) min/avg/max = " + min + "/");
            System.out.printf("%.2f/", media);
            System.out.println(max);
        } catch (

        SocketTimeoutException e) {
            System.out.println("Il client non ha ricevuto una risposta in " + this.timeout + " ms.");
        } catch (BindException e) {
            System.out.println("Porta già occupata");
        } catch (IOException e) { // NB: SocketException è una sottoclasse di IOException
            e.printStackTrace();
        }
    }

}