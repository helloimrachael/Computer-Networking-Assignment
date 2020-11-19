// Rachele Puri 547938

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        // Creo il server socket per iniziare l'attesa di richieste.
        try (ServerSocket servSock = new ServerSocket(6789)) {
            while (true) {
                // Apro la socket in ascolto.
                try (Socket connection = servSock.accept()) {
                    System.out.println("Nuova richiesta ricevuta");
                    OutputStream out = connection.getOutputStream();
                    BufferedReader bReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    // Leggo una riga per votla
                    String line = bReader.readLine();
                    // Controllo il primo elemento della stringa.
                    if (line.startsWith("GET")) {
                        // Tokenizzo la stringa della richiesta per prendere il secondo
                        // elemento del token, il quale mi specifica il file richiesto.
                        String[] token;
                        token = line.split("\\s");
                        // Mi muovo solo nella cartela dove mi trovo.
                        String pathTok = System.getProperty("user.dir") + token[1];
                        Path path = Paths.get(pathTok);
                        // Controllo se il file e' presente nella cartella.
                        if (Files.exists(path) && !Files.isDirectory(path)) {
                            // Se e' presente mando una risposta di ok.
                            okResponse(pathTok, out, Files.probeContentType(path), Files.size(path));
                            System.out.println("Risposta 'ok' inviata.");
                        } else {
                            // Se non e' presente lo comunico con un messaggio di errore.
                            noOkResponse(out);
                            System.out.println("Risposta 'not found' inviata.");
                        }
                    } else {
                        // Se la richiesta non e' GET il server non puo' gestirla e lo comunica.
                        System.out.println("Il server non può gestire questa richiesta.");
                        noResponse(out);
                    }
                    System.out.println("Chiusura della connessione.");
                    out.close();
                    bReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo usato per comunicare che la richiesta non e' di tipo GET.
    public static void noResponse(OutputStream out) {
        try {
            String response = "HTTP/1.1 404 Bad Request\r\n";
            response = response + "Content-Type: text/html\r\n";
            response = response + "\r\n";
            out.write(response.getBytes());
            response = "<center><h1>Richiesta non valida.</h1></center>";
            out.write(response.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo usato per comunicare che il file e' stato trovato.
    public static void okResponse(String fileName, OutputStream out, String ext, long dimensione) {
        try {
            String response = "HTTP/1.1 200 OK\r\n";
            response = response + "Content-Type: " + ext + "\r\n";
            response = response + "Content-Lenght: " + dimensione + "\r\n";
            response = response + "\r\n";
            out.write(response.getBytes());
            File file = new File(fileName);
            Files.copy(file.toPath(), out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo per comunicare che il file non esiste.
    public static void noOkResponse(OutputStream out) {
        try {
            String response = "HTTP/1.1 404 Not Found\r\n";
            response = response + "Content-Type: text/html\r\n";
            response = response + "\r\n";
            out.write(response.getBytes());
            response = "<center><h1>Il file non esiste.</h1></center>";
            out.write(response.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}