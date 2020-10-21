// Puri Rachele 547938

public class Undergraduate extends Thread {

    String undergraduateName;
    int priority;
    TutorMonitor TM;
    int computerNumber;
    int k;
    long timer1;
    long timer2;

    public Undergraduate(String name, int priority, TutorMonitor TM, int computerNumber, int k) {
        undergraduateName = name;
        this.priority = priority;
        this.TM = TM;
        this.computerNumber = computerNumber;
        this.k = k;
        timer1 = (long) (Math.random() * 4000);
        timer2 = (long) (Math.random() * 5000);
    }

    @Override
    public void run() {
        for (int i = 0; i < k; i++) {
            TM.acquire(undergraduateName, priority);
            System.out.println("Computer " + computerNumber + " is available for " + undergraduateName + ".");
            TM.setComputerXAsBusy(computerNumber);
            System.out.println(undergraduateName + " is working on computer " + computerNumber + ".");
            try {
                Thread.sleep(timer1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TM.setComputerXAsAvailable(computerNumber);
            System.out.println(undergraduateName + " finished working.");
            TM.release(priority);
            try {
                Thread.sleep(timer2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
