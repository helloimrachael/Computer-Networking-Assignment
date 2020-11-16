// Rachele Puri 547938

public class Occorrenze {
    private int numBonifici;
    private int numAccrediti;
    private int numBollettini;
    private int numF24;
    private int numPagoBancomat;

    public Occorrenze() {
        numBonifici = 0;
        numAccrediti = 0;
        numBollettini = 0;
        numF24 = 0;
        numPagoBancomat = 0;
    }

    public synchronized void aggiungiBonifico() {
        this.numBonifici++;
    }

    public synchronized void aggiungiAccredito() {
        this.numAccrediti++;
    }

    public synchronized void aggiungiBollettino() {
        this.numBollettini++;
    }

    public synchronized void aggiungiF24() {
        this.numF24++;
    }

    public synchronized void aggiungiPagoBancomat() {
        this.numPagoBancomat++;
    }

    public synchronized int getBonifico() {
        return this.numBonifici;
    }

    public synchronized int getAccredito() {
        return this.numAccrediti;
    }

    public synchronized int getBollettino() {
        return this.numBollettini;
    }

    public synchronized int getF24() {
        return this.numF24;
    }

    public synchronized int getPagoBancomat() {
        return this.numPagoBancomat;
    }

}
