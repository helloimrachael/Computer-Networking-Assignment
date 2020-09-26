// Puri Rachele 547938

public class Main {
    public static void main(String[] args) throws InterruptedException {
        if(args.length < 2)
            System.out.println("Usage: java Main arg1 arg2");
        String accuracy = args[0];
        String timeMax = args[1]; //tempo massimo di attesa
        double acc = Double.parseDouble(accuracy);
        Long tMax = Long.parseLong(timeMax);
        pI p = new pI(acc);
        Thread t = new Thread(p);
        t.start();
        System.out.println(t.getName() + " is active");
        System.out.println("Start PI calculation");
        try{
            t.join(tMax);
            t.interrupt();
            if(t.isInterrupted())
                System.out.println(t.getName() + " interrupted");
            System.out.println(Thread.currentThread().getName() + " is awake");
        } catch (InterruptedException e){
            System.out.println("Thread interrupted");
        }  
    }
}
