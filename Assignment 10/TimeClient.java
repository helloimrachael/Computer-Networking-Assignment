// Rachele Puri 547938

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class TimeClient {
    // Indirizzo del gruppo di multicast.
    private final InetAddress dataGroup;
    // Porta associata all'indirizzo multicast.
    private final int port;
    // Lunghezza del messaggio da ricevere (possiamo avere questa informazione).
    private final int MSG_LENGTH = 1024;

    public TimeClient(String addr, int port) throws UnknownHostException, IllegalArgumentException {
        this.dataGroup = InetAddress.getByName(addr);
        // Verifica che l'indirizzo passato come argomento sia valido.
        if (!this.dataGroup.isMulticastAddress())
            throw new IllegalArgumentException();
        this.port = port;
    }

    // Avvia il client.
    public void start() {
        try (MulticastSocket multicastDataGroup = new MulticastSocket(this.port)) {
            multicastDataGroup.joinGroup(this.dataGroup);
            DatagramPacket dat = new DatagramPacket(new byte[this.MSG_LENGTH], MSG_LENGTH);
            for (int i = 0; i < 10; i++) {
                multicastDataGroup.receive(dat);
                System.out.printf("Ho ricevuto %s dal server\n",
                        new String(dat.getData(), dat.getOffset(), dat.getLength()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
