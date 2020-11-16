// Puri Rachele 547938

import java.io.File;

public class Consumer extends Thread {
    linkedList directoriesList; // Variabile che identifica la lista contenente i nomi delle directories.
    String consumerName; // Variabile che identifica il nome del consumatore corrente.

    public Consumer(linkedList directoriesList, String consumerName) {
        this.directoriesList = directoriesList;
        this.consumerName = consumerName;
    }

    @Override
    public void run() {

        boolean notFinished = true;
        while (notFinished) {
            directoriesList.myLock.lock();

            // Controlla se la lista e' vuota, in tal caso aspetta.
            while (directoriesList.directories.isEmpty()) {
                try {
                    directoriesList.theListIsEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Confronta la stringa contenuta in un elemento della lista con la stringa che
            // identifica che il produttore ha visitato tutto le directory.
            if (directoriesList.peekNameDirectory().equals("There are no more directories")) {
                System.out.println("Thread " + consumerName + " found the string 'There are no more directories'.");
                directoriesList.myLock.unlock();
                notFinished = false;
                System.out.println(consumerName + " has finished.");
                return;
            }

            // Scorre la directory per stampare i file in essa contenuti (se li contiene).
            File file = new File(directoriesList.pollNameDirectory());
            directoriesList.myLock.unlock();
            File[] listOfFile = file.listFiles();
            for (File f : listOfFile) {
                if (f.isFile()) {
                    System.out.println("File: " + f.getName() + " (Printed by thread: " + consumerName + " ).");
                }
            }
        }
    }
}
