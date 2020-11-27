// Rachele Puri 547938

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class Server {
    private static int DEFAULT_PORT = 6789;

    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel;
        ServerSocket socket;
        Selector selector = null;
        try {
            // Apetura canale per la connessione client-server.
            serverSocketChannel = ServerSocketChannel.open();
            socket = serverSocketChannel.socket();
            socket.bind(new InetSocketAddress(DEFAULT_PORT));
            // Metto il canale in modalità non bloccante.
            serverSocketChannel.configureBlocking(false);
            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        while (true) {
            try {
                System.out.println("SERVER --> In attesa sulla select");
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            // Scorro tutte le chiavi del selettore.
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    if (key.isAcceptable()) {
                        // Accettazione connesssione.
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        System.out.println("SERVER --> Accepted connection from " + client);
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);
                    } else if (key.isWritable()) {
                        System.out.println("SERVER --> Scrittura");
                        SocketChannel client = (SocketChannel) key.channel();
                        String s = (String) key.attachment();
                        ByteBuffer buf = ByteBuffer.wrap(s.getBytes());
                        // Salvo il numero di byte letti in una variabile.
                        int numBytesW = client.write(buf);
                        if (numBytesW == -1) { // Write non riuscita.
                            System.out.println("SERVER --> Write finita | Socket chiusa");
                            key.cancel();
                            key.channel().close();
                            System.out.println("SERVER --> Canale chiuso.");
                        }
                        if (buf.hasRemaining()) { // C'è ancora qualcosa nel buffer.
                            buf.flip();
                            String mes = StandardCharsets.UTF_8.decode(buf).toString();
                            key.attach(mes);
                        } else { // numBytesW == buf.length().
                            key.attach(null);
                            key.interestOps(SelectionKey.OP_READ);
                        }
                    } else if (key.isReadable()) {
                        System.out.println("SERVER --> Lettura");
                        SocketChannel client = (SocketChannel) key.channel();
                        String s = (String) key.attachment();
                        s = "echoed by server: ";
                        ByteBuffer buf = ByteBuffer.allocate(1024);
                        buf.clear();
                        // Salvo il numero di byte letti in una variabile.
                        int numBytesR = client.read(buf);
                        if (numBytesR == -1) { // Read non riuscita.
                            System.out.println("SERVER --> Read finita | Socket chiusa");
                            key.cancel();
                            key.channel().close();
                            System.out.println("SERVER --> Canale chiuso.");
                        }
                        if (numBytesR < 1024) {
                            buf.flip();
                            s = s + StandardCharsets.UTF_8.decode(buf).toString();
                            key.attach(s);
                            key.interestOps(SelectionKey.OP_WRITE);
                        } else { // numBytesR == 1024.
                            buf.flip();
                            s = s + StandardCharsets.UTF_8.decode(buf).toString();
                            key.attach(s);
                        }
                    }
                } catch (IOException e) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (Exception ex) {
                    }
                }
            }
        }
    }
}