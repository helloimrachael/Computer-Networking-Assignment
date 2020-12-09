// Rachele Puri 547938

import java.io.IOException;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class TimeServer {
    // Indirizzo del gruppo di multicast.
    private InetAddress multicastGroup;
    // Porta associata all'indirizzo multicast.
    private final int port;

    public TimeServer(String addr, int port) throws UnknownHostException, IllegalArgumentException {
        this.multicastGroup = InetAddress.getByName(addr);
        if (!this.multicastGroup.isMulticastAddress())
            throw new IllegalArgumentException();
        this.port = port;
    }

    // Avvio del server.
    public void start() {
        Random rand = new Random();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime msg;
        try (DatagramSocket sock = new DatagramSocket()) {
            while (true) {
                msg = LocalDateTime.now();
                String MSG = msg.format(myFormat);
                DatagramPacket dat = new DatagramPacket(MSG.getBytes(), MSG.length(), this.multicastGroup, this.port);
                sock.send(dat);
                System.out.printf("Il server ha inviato %s\n",
                        new String(dat.getData(), dat.getOffset(), dat.getLength()));
                // Attende un numero di millisecondi casuale compreso tra 200 e 2000.
                Thread.sleep(rand.nextInt(1800) + 200);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}