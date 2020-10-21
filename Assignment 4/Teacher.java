// Puri Rachele 547938

public class Teacher extends Thread {

    String teacherName;
    int priority;
    TutorMonitor TM;
    int k;
    long timer1;
    long timer2;

    public Teacher(String name, int priority, TutorMonitor TM, int k) {
        teacherName = name;
        this.priority = priority;
        this.TM = TM;
        this.k = k;
        timer1 = (long) (Math.random() * 5000);
        timer2 = (long) (Math.random() * 6000);
    }

    @Override
    public void run() {
        for (int i = 0; i < k; i++) {
            TM.acquire(teacherName, priority);
            System.out.println("The whole laboratory is available.");
            TM.setAllComputersAsBusy();
            System.out.println(teacherName + " is working in the laboratory.");
            try {
                Thread.sleep(timer1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TM.setAllComputersAsAvailable();
            System.out.println(teacherName + " finished working.");
            TM.release(priority);
            try {
                Thread.sleep(timer2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
