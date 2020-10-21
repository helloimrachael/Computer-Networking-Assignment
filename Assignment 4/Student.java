// Puri Rachele 547938

public class Student extends Thread {

    String studentName;
    int priority;
    TutorMonitor TM;
    int k;
    long timer1;
    long timer2;

    public Student(String name, int priority, TutorMonitor TM, int k) {
        studentName = name;
        this.priority = priority;
        this.TM = TM;
        this.k = k;
        timer1 = (long) (Math.random() * 3000);
        timer2 = (long) (Math.random() * 4000);
    }

    @Override
    public void run() {
        int computerNumber;
        for (int i = 0; i < k; i++) {
            TM.acquire(studentName, priority);
            computerNumber = TM.findTheFirstAvailableComputer();
            System.out.println("There is an available computer: computer " + computerNumber);
            TM.setComputerXAsBusy(computerNumber);
            System.out.println(studentName + " is working on computer " + computerNumber);
            try {
                Thread.sleep(timer1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TM.setComputerXAsAvailable(computerNumber);
            System.out.println(studentName + " finished working.");
            TM.release(priority);
            try {
                Thread.sleep(timer2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
