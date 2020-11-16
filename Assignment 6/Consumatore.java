// Rachele Puri 547938

public class Consumatore implements Runnable {
    String causale;
    Occorrenze occorrenza; // Occorrenze.class e' la classe per gestire le occorrenze delle varie causali.

    public Consumatore(String causale, Occorrenze occorrenza) {
        this.causale = causale;
        this.occorrenza = occorrenza;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " " + causale);

        // Confronto la causale con una delle stringhe seguenti e incremento la
        // rispettiva variabile.
        if (causale.equals("Bonifico")) {
            occorrenza.aggiungiBonifico();
        } else if (causale.equals("Accredito")) {
            occorrenza.aggiungiAccredito();
        } else if (causale.equals("Bollettino")) {
            occorrenza.aggiungiBollettino();
        } else if (causale.equals("F24")) {
            occorrenza.aggiungiF24();
        } else {
            occorrenza.aggiungiPagoBancomat();
        }
    }
}
