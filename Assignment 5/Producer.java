// Puri Rachele 547938

import java.io.File;

public class Producer extends Thread {
    String path; // Variabile che identifica il percorso da percorrere.
    linkedList directoriesList; // Variabile che identifica la lista dei nomi delle directories.

    public Producer(String path, linkedList directoriesList) {
        this.path = path;
        this.directoriesList = directoriesList;
    }

    @Override
    public void run() {

        // Il produttore visita ricorsivamente la directory e le sue sottodirectories.
        recursiveSearch(path);
        directoriesList.myLock.lock();

        // Non ci sono piu' directory da aggiungere alla lista e lo comunico ai
        // consumatori aggiungendo alla lista la stringa "There are no more
        // directories".
        directoriesList.addNameDirectory("There are no more directories");

        // Risveglia tutti quelli in attesa sulla variabile di condizione.
        directoriesList.theListIsEmpty.signalAll();
        directoriesList.myLock.unlock();
    }

    public void recursiveSearch(String directory) {
        File file = new File(directory);
        File[] fileList = file.listFiles();
        if (fileList != null) {
            for (File f : fileList) { // Scorro tutte le directory e le proprie sottodirectories, salvando i loro nomi
                                      // nella lista.
                if (f.isDirectory()) {
                    directoriesList.myLock.lock();

                    // Aggiungo il nome della directory con il proprio path assoluto.
                    directoriesList.addNameDirectory(f.getAbsolutePath());

                    // Risveglio i consumatori perche' il produttore ha appena aggiunto un elemento
                    // nella lista.
                    directoriesList.theListIsEmpty.signalAll();
                    directoriesList.myLock.unlock();
                    System.out.println("Producer added " + f.getAbsolutePath() + " to the list.");

                    // Richiamo la funzione per scorrere le sottodirectories.
                    recursiveSearch(f.getAbsolutePath());
                }
            }
        }
    }
}
