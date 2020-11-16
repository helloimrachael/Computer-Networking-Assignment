// Rachele Puri 547938

import java.io.IOException;
import java.util.ArrayList;

public class ListaConti {
    int numCorrentisti;
    ArrayList<ContoCorrente> banca = new ArrayList<>(); // Lista di tutti i movimenti di tutti i conti correnti.

    // Metodo che crea tanti conti correnti quanti sono i correntisti.
    public void createFile() throws IOException {
        numCorrentisti = (int) (Math.random() * 10) + 1;
        for (int i = 0; i < numCorrentisti; i++) {
            banca.add(nuovoContoCorrente(i));
        }
    }

    // Metodo che genera casualmente una casuale per un movimento.
    public String Causale() {
        int causale = (int) (Math.random() * 5);
        if (causale == 0) {
            return "Bonifico";
        }
        if (causale == 1) {
            return "Accredito";
        }
        if (causale == 2) {
            return "Bollettino";
        }
        if (causale == 3) {
            return "F24";
        }
        if (causale == 4) {
            return "PagoBancomat";
        }
        return null;
    }

    // Metodo che genera casualmente una data da assegnare ad un movimento.
    public String Data() {
        String data;
        int mese = (int) (Math.random() * 12) + 1;
        if (mese == 2) {
            int giorno = (int) (Math.random() * 28) + 1;
            int anno = (int) (Math.random() * 2) + 2018;
            data = giorno + "/" + mese + "/" + anno;
        } else if (mese == 4 || mese == 6 || mese == 9 || mese == 11) {
            int giorno = (int) (Math.random() * 30) + 1;
            int anno = (int) (Math.random() * 2) + 2018;
            data = giorno + "/" + mese + "/" + anno;
        } else {
            int giorno = (int) (Math.random() * 29) + 1;
            int anno = (int) (Math.random() * 2) + 2018;
            data = giorno + "/" + mese + "/" + anno;
        }
        return data;
    }

    // Metodo che crea un nuovo conto corrente e aggiunge un numero casuale di
    // movimenti a esso.
    public ContoCorrente nuovoContoCorrente(int j) {
        String data;
        String causale;
        ContoCorrente conto = new ContoCorrente();
        conto.setNomeCorrentista("Correntista-" + j);
        int numMovimenti = (int) (Math.random() * 10) + 1;
        for (int i = 0; i < numMovimenti; i++) {
            data = Data();
            causale = Causale();
            conto.setMovimenti(causale, data);
        }
        return conto;
    }

    // Restituisce la lista di tutti i conto correnti di tutti i correntisti.
    public ArrayList<ContoCorrente> getContiCorrenti() {
        return this.banca;
    }
}