// PURI RACHELE 547938

public class piGreco extends Thread{
    
    private double p;
    private double acc; //accuracy
    private double diff; //differce between p and Math.PI

    public piGreco(double acc) {
        this.acc = acc;
    }

    @Override
    public void run() {
        double i = 1;
        p = 4.00/i; //valore iniziale di p è 4 poichè i = 1 
        i = i + 2;
        int flag = 0; //variabile che uso per alternare + e -
        diff = Math.abs(p - Math.PI);
        while(diff >= acc && (Thread.currentThread().isInterrupted() == false)) {
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
        System.out.println("piGreco calcolato: " + p);
        System.out.println("piGreco Math.PI: " + Math.PI);
    }
}
