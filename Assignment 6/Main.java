// Rachele Puri 547938

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {
        // Creo un oggetto che appartiene alla classe utilizzata per creare un file
        // JSON.
        ListaConti fileJSON = new ListaConti();
        try {
            fileJSON.createFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // SCRITTURA NEL BUFFER.
        String path = "./banca.json";
        ObjectMapper objectMapper = new ObjectMapper();
        ByteBuffer writeBuffer;
        try {
            writeBuffer = ByteBuffer.wrap(objectMapper.writeValueAsBytes(fileJSON));
            // Cancello il file precedentemente creato, se esite.
            Files.deleteIfExists(Paths.get(path));
            FileChannel writeChannel;
            // Apro il canale in scrittura.
            writeChannel = FileChannel.open(Paths.get(path), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            while (writeBuffer.hasRemaining()) {
                writeChannel.write(writeBuffer);
            }
            writeChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Occorrenze occ = new Occorrenze();
        // Creo un thread pool per gestire produttore e consumatore.
        ThreadPool pool = new ThreadPool(5);
        Produttore produttore = new Produttore(occ, pool);
        // Faccio partire il produttore.
        produttore.start();
    }
}
