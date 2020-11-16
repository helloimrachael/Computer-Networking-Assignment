// Puri Rachele 547938

import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: insert filepath.");
        }
        File startDirectory = new File(args[0]);
        if (!startDirectory.isDirectory()) {
            System.out.println("Initial file is not a directory!");
            return;
        }
        String path = args[0];
        linkedList directories = new linkedList();
        int k = (int) (Math.random() * 12) + 1;
        System.out.println("Number of consumers: " + k + ".");
        Producer p = new Producer(path, directories);
        Consumer[] c = new Consumer[k];
        for (int i = 0; i < k; i++) {
            c[i] = new Consumer(directories, "Consumer-" + i);
        }
        p.start();
        for (int i = 0; i < k; i++) {
            c[i].start();
        }
    }
}
