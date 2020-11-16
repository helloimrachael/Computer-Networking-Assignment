// Rachele Puri 547938

public class Movimenti {
    private String data;
    private String causale;

    public Movimenti() {
    }

    // Metodo che mi setta la data del movimento.
    public void setData(String data) {
        this.data = data;
    }

    // Metodo che setta la causale del movimento.
    public void setCausale(String causale) {
        this.causale = causale;
    }

    // Metodo che restituisce la data del movimento.
    public String getData() {
        return this.data;
    }

    // Metodo che restituisce la causale del movimento.
    public String getCausale() {
        return this.causale;
    }
}
