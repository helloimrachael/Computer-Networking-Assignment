// Rachele Puri 547938

import java.util.Scanner;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Client {
    private static int DEFAULT_PORT = 6789;

    public static void main(String[] args) {
        SocketChannel client;
        try {
            client = SocketChannel.open(new InetSocketAddress(DEFAULT_PORT));
            System.out.println("Client collegato a " + client);
            // Buffer per la scrittura.
            ByteBuffer bufW;
            // Buffer per la lettura.
            ByteBuffer bufR;
            String input;
            boolean finito;
            String s;
            Scanner scanner = new Scanner(System.in);
            while (true) {
                // Lettura messaggio da terminale.
                System.out.println("Inserire il messaggio:");
                input = scanner.nextLine();
                bufW = ByteBuffer.wrap(input.getBytes());
                bufR = ByteBuffer.allocate(1024);
                finito = false;
                s = "";
                System.out.println("CLIENT --> Scrittura sul buffer.");
                while (bufW.hasRemaining()) {
                    client.write(bufW);
                }
                bufW.clear();
                bufW.flip();
                System.out.println("CLIENT --> Lettura dal buffer.");
                while (!finito) {
                    bufR.clear();
                    int numByte = client.read(bufR);
                    bufR.flip();
                    s = s + StandardCharsets.UTF_8.decode(bufR).toString();
                    bufR.flip();
                    if (numByte < 1024) {
                        finito = true;
                    }
                    System.out.println(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
