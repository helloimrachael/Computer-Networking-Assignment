import java.io.IOException;
import java.net.*;
import java.util.Random;

public class ServerPing {
    // Porta su cui è in ascolto il server.
    private final int porta;
    // Seed utilizzato per la generazione di latenze e perdita di pacchetti.
    private final long seed;

    public ServerPing(int porta, long seed) {
        this.porta = porta;
        this.seed = seed;
    }

    public void start() {
        Random rand = new Random(seed);
        long latenza;
        try (DatagramSocket serverSocket = new DatagramSocket(porta)) {
            byte[] buffer = new byte[1024];
            DatagramPacket pacchettoRicevuto = new DatagramPacket(buffer, buffer.length);
            System.out.println("Il server è in attesa di un PING");
            while (true) {
                serverSocket.receive(pacchettoRicevuto);
                String msg = new String(pacchettoRicevuto.getData());
                int numRandom = rand.nextInt(100);
                // Invia una risposta con probabilità seed.
                if (numRandom < 25) {
                    System.out.println(pacchettoRicevuto.getAddress() + ":" + pacchettoRicevuto.getPort() + "> " + msg
                            + " ACTION: not sent");
                } else {
                    latenza = (long) (Math.random() * seed);
                    System.out.println(pacchettoRicevuto.getAddress() + ":" + pacchettoRicevuto.getPort() + "> " + msg
                            + " ACTION: delayed " + latenza + " ms");
                    try {
                        Thread.sleep(latenza);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    byte[] replyBuffer = msg.getBytes();
                    DatagramPacket pacchettoInviato = new DatagramPacket(replyBuffer, replyBuffer.length,
                            pacchettoRicevuto.getAddress(), pacchettoRicevuto.getPort());
                    serverSocket.send(pacchettoInviato);
                }
            }
        } catch (BindException e) {
            System.out.println("Porta già occupata");
        } catch (IOException e) { // NB: SocketException è una sottoclasse di IOException
            e.printStackTrace();
        }

    }

}