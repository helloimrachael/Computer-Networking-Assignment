// Rachele Puri 547938

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Produttore extends Thread {

    Occorrenze occorrenza;
    ThreadPool pool;
    String path = "banca.json";

    public Produttore(Occorrenze occorrenza, ThreadPool pool) {
        this.occorrenza = occorrenza;
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            // LETTURA DAL BUFFER.
            ObjectMapper objectMapper = new ObjectMapper();
            // Salvo in una stringa tutto il contenuto del buffer.
            String s = readFile();
            ListaConti file = objectMapper.readValue(s, ListaConti.class);
            // Salvo tutti i conti correnti in una lista.
            ArrayList<ContoCorrente> conti = file.getContiCorrenti();
            // Salvo tutti i movimenti in una lista.
            ArrayList<Movimenti> movimenti = new ArrayList<>();
            for (int i = 0; i < conti.size(); i++) {
                movimenti.addAll(conti.get(i).getMovimenti());
            }
            // Creo i consumatori e li mando in esecuzione.
            for (int j = 0; j < movimenti.size(); j++) {
                Consumatore consumatore = new Consumatore(movimenti.get(j).getCausale(), occorrenza);
                pool.executeTask(consumatore);
            }

            // Chiudo il thread pool.
            pool.closeThreadPool();

            System.out.println("Bonifico = " + occorrenza.getBonifico());
            System.out.println("Accredito = " + occorrenza.getAccredito());
            System.out.println("Bollettino = " + occorrenza.getBollettino());
            System.out.println("F24 = " + occorrenza.getF24());
            System.out.println("PagoBancomat = " + occorrenza.getPagoBancomat());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metodo per la lettura del buffer.
    public String readFile() throws IOException {
        // Apro il canale in lettura.
        FileChannel readChannel = FileChannel.open(Paths.get(path), StandardOpenOption.READ);
        ByteBuffer readBuffer = ByteBuffer.allocate(2048);
        String s = "";
        while (readChannel.read(readBuffer) > 0) {
            readBuffer.flip();
            while (readBuffer.hasRemaining()) {
                s = s + StandardCharsets.UTF_8.decode(readBuffer).toString();
            }
            readBuffer.flip();
        }
        readChannel.close();
        return s;
    }
}
