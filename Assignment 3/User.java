// Puri Rachele 547938

public class User extends Thread {

    private String userName;
    private int priority;
    private int k;
    private int computerNumber;
    Tutor computers;

    public User(String userName, int priority, int k, Tutor computers, int computerNumber) {
        this.userName = userName;
        this.priority = priority;
        this.k = k;
        this.computers = computers;
        this.computerNumber = computerNumber;
    }

    @Override
    public void run() {
        long timer;
        for (int i = 0; i < k; i++) {

            ////////// THE USER IS A STUDENT \\\\\\\\\\
            if (priority == 0) {
                computers.l.lock();
                while (computers.numberOfAvailableComputers() == 0) {
                    System.out.println(userName + " is waiting for a computer.");
                    try {
                        computers.aComputerIsAvailable.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                computerNumber = computers.findTheFirstAvailableComputer();
                System.out.println(
                        "There is an available computer for " + userName + ", computer-" + computerNumber + ".");
                computers.l.unlock();
                try {
                    timer = (long) (Math.random() * 3000);
                    System.out.println(userName + " started working on the computer-" + computerNumber + ".");
                    Thread.sleep(timer);
                    System.out.println(userName + " has finished working on the computer-" + computerNumber + ".");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                computers.l.lock();
                computers.setComputerXAsAvailable(computerNumber);
                computers.allComputersAreAvailable.signalAll();
                computers.computerXIsAvailable.signal();
                computers.aComputerIsAvailable.signal();
                computers.l.unlock();
            }

            ////////// THE USER IS AN UNDERGRADUATE \\\\\\\\\\
            if (priority == 1) {
                computers.l.lock();
                while (!computers.isComputerXAvailable(computerNumber)) {
                    System.out.println(userName + " is waiting the computer-" + computerNumber + ".");
                    try {
                        computers.computerXIsAvailable.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("The computer-" + computerNumber + " is available for " + userName + ".");
                computers.setComputerXAsBusy(computerNumber);
                computers.l.unlock();
                try {
                    timer = (long) (Math.random() * 4000);
                    System.out.println(userName + " started working on the computer-" + computerNumber + ".");
                    Thread.sleep(timer);
                    System.out.println(userName + " has finished working on the computer-" + computerNumber + ".");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                computers.l.lock();
                computers.setComputerXAsAvailable(computerNumber);
                computers.allComputersAreAvailable.signalAll();
                computers.computerXIsAvailable.signal();
                computers.aComputerIsAvailable.signal();
                computers.l.unlock();
            }

            ////////// THE USER IS A TEACHER \\\\\\\\\\
            if (priority == 2) {
                computers.l.lock();
                while (!computers.theWholeLaboratoryIsAvailable()) {
                    System.out.println(userName + " is waiting that all computers are available.");
                    try {
                        computers.allComputersAreAvailable.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("The whole laboratory is available for " + userName);
                computers.setAllComputersAsBusy();
                computers.l.unlock();
                try {
                    timer = (long) (Math.random() * 5000);
                    System.out.println(userName + " started working in the laboratory.");
                    Thread.sleep(timer);
                    System.out.println(userName + " has finished working in the laboratory.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                computers.l.lock();
                computers.setAllComputersAsAvailable();
                computers.allComputersAreAvailable.signal();
                computers.computerXIsAvailable.signal();
                computers.aComputerIsAvailable.signal();
                computers.l.unlock();
            }

            try {
                timer = (long) (Math.random() * 6000);
                Thread.sleep(timer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}