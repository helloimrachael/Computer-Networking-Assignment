// Rachele Puri 547938

import java.util.ArrayList;

public class ContoCorrente {
    private String nomeCorrentista;
    private ArrayList<Movimenti> movimenti; // Lista di tutti i movimenti legati a questo conto corrente.

    public ContoCorrente() {
        movimenti = new ArrayList<>();
    }

    // Metodo che assegna il nome al correntista.
    public void setNomeCorrentista(String nomeCorrentista) {
        this.nomeCorrentista = nomeCorrentista;
    }

    // Metodo che inserisce un movimento alla lista dei movimenti.
    public void setMovimenti(String causale, String data) {
        Movimenti m = new Movimenti();
        m.setCausale(causale);
        m.setData(data);
        movimenti.add(m);
    }

    // Metodo che restituisce il nome del correntista.
    public String getNomeCorrentista() {
        return this.nomeCorrentista;
    }

    // Metodo che restituisce la lista di tutti i movimenti di questo conto
    // corrente.
    public ArrayList<Movimenti> getMovimenti() {
        return this.movimenti;
    }
}
