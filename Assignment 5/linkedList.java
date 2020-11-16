// Puri Rachele 547938

import java.util.LinkedList;
import java.util.concurrent.locks.*;

public class linkedList {
    LinkedList<String> directories; // Variabile che identifica la lista contenente i nomi delle directories.
    final Lock myLock; // Variabile che identifica la lock.
    final Condition theListIsEmpty; // Variabile di condizione.

    public linkedList() {
        directories = new LinkedList<>();
        myLock = new ReentrantLock();
        theListIsEmpty = myLock.newCondition();
    }

    // Metodo che aggiunge in testa alla lista il nome di una directory.
    public void addNameDirectory(String directory) {
        directories.addFirst(directory);
    }

    // Metodo che restuisce l'ultimo elemento dalla lista rimuovendolo da essa.
    public String pollNameDirectory() {
        return directories.pollLast();
    }

    // Metodo che restituisce l'ultimo elemento della lista senza rimuoverlo.
    public String peekNameDirectory() {
        return directories.peekLast();
    }

}
