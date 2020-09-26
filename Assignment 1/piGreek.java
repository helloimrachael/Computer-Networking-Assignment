// Puri Rachele 547938

public class piGreek extends Thread{
    
    private double acc; //accuracy
    double p;
    double diff; //differce between p and Math.PI

    public piGreek(double acc) {
        this.acc = acc;
    }

    @Override
    public void run() {
        // Gregory-Leibniz series ( PI Greek = 4/1 – 4/3 + 4/5 - 4/7 + 4/9 - 4/11 ...)
        double i = 1;
        int flag = 1; // variable that I use to alternate between - and +
        diff = Math.abs(p - Math.PI);
        while(diff >= acc && !Thread.currentThread().isInterrupted()) {
            if(flag == 0) {
                p = p - 4.00/i;
                diff = Math.abs(p - Math.PI);
                i = i + 2;
                flag = 1;
            }
            if(flag == 1) {
                p = p + 4.00/i;
                diff = Math.abs(p - Math.PI);
                i = i + 2;
                flag = 0;  
            }
        }
        System.out.println("PI Greek calculated: " + p);
        System.out.println("PI Greek from Math.PI: " + Math.PI);
    }
}
