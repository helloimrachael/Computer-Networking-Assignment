public class Main {
    public static void main(String[] args) throws InterruptedException {
        String accuracy = args[0];
        String timeMax = args[1]; //tempo massimo di attesa
        double acc = Double.parseDouble(accuracy);
        Long tMax = Long.parseLong(timeMax);
        piGreco p = new piGreco(acc);
        Thread t = new Thread(p);
        t.start();
        System.out.println("Start piGreco calculation");
        try{
            //Thread.sleep(tMax);
            t.join(tMax);
            t.interrupt();
            if(t.isInterrupted() == true)
                System.out.println(t.getName() + " interrupted");
            System.out.println(Thread.currentThread().getName() + " is awake");
        } catch (InterruptedException e){
            System.out.println("Thread interrupted");
        }  
    }
}
