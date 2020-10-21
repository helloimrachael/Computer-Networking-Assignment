// Puri Rachele 547938

import java.util.ArrayList;

public class TutorMonitor {
    // Set of computers in the laboratory.
    ArrayList<Integer> computers;
    // Variable that substitutes methods lock() and unlock(). If available == true,
    // the user can possibly access at the laboratory.
    private boolean available;
    // The undergraduates can access a specific computer.
    int undergraduatesComputerNumber;

    public TutorMonitor(int undergraduatesComputerNumber) {
        available = true;
        computers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            computers.add(i, 0); // If computers.get(i) == 0 means that it is available, otherwise it is busy.
        }
        this.undergraduatesComputerNumber = undergraduatesComputerNumber;
    }

    public synchronized void acquire(String userName, int priority) {
        ////////// THE USER IS A STUDENT \\\\\\\\\\
        if (priority == 0) {
            while (!available || numberOfAvailableComputers() == 0) {
                try {
                    System.out.println(userName + " is waiting for a computer.");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // A student after he has logged into the laboratory sets the variable available
            // as true since
            // other users can enter with him as long as they are students or
            // undergraduates.
            available = true;
            this.notifyAll();
        }

        ////////// THE USER IS A UNDERGRADUATE \\\\\\\\\\
        if (priority == 1) {
            while (!available || !isComputerXAvailable(undergraduatesComputerNumber)) {
                try {
                    System.out.println(userName + " is waiting computer " + undergraduatesComputerNumber + ".");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // An undergraduate after he has logged into the laboratory sets the variable
            // available as true since
            // other users can enter with him as long as they are students or
            // undergraduates.
            available = true;
            this.notifyAll();
        }

        ////////// THE USER IS A TEACHER \\\\\\\\\\
        if (priority == 2) {
            while (!available || !theWholeLaboratoryIsAvailable()) {
                try {
                    System.out.println(userName + " is waiting for the laboratory.");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // The teacher sets the variable available as false because when he is in the
            // laboratory the other users can not access.
            available = false;
            this.notifyAll();
        }
    }

    public synchronized void release(int priority) {
        ////////// THE USER IS A STUDENT OR AN UNDERGRADUATE \\\\\\\\\\
        if (priority == 0 || priority == 1) {
            // The variable available can not be set as true if someone else has set it as
            // false.
            while (!available) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            available = true;
            this.notifyAll();

        }

        ////////// THE USER IS A STUDENT OR AN UNDERGRADUATE \\\\\\\\\\
        if (priority == 2) {
            while (available) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            available = true;
            this.notifyAll();
        }
    }

    // This method returns the number of available computer.
    public synchronized int numberOfAvailableComputers() {
        int counter = 0;
        for (int i = 0; i < 20; i++) {
            if (computers.get(i) == 0)
                counter++;
        }
        return counter;
    }

    // This method returs the index of the first available computer.
    public synchronized int findTheFirstAvailableComputer() {
        for (int i = 0; i < 20; i++) {
            if (computers.get(i) == 0) {
                computers.set(i, 1);
                return i;
            }
        }
        return -1;
    }

    // This method sets computer X as available.
    public synchronized void setComputerXAsAvailable(int x) {
        computers.set(x, 0);
    }

    // This method returns true if computer X is available, otherwise it returs
    // false.
    public synchronized boolean isComputerXAvailable(int x) {
        if (computers.get(x) == 0)
            return true;

        return false;
    }

    // This method sets computer X as busy.
    public synchronized void setComputerXAsBusy(int x) {
        computers.set(x, 1);
    }

    // This method returns true if the whole laboratory is available, otherwise it
    // returns false.
    public synchronized boolean theWholeLaboratoryIsAvailable() {
        for (int i = 0; i < 20; i++) {
            if (computers.get(i) == 1)
                return false;
        }
        return true;
    }

    // This method sets all computers in the laboratory as busy.
    public synchronized void setAllComputersAsBusy() {
        for (int i = 0; i < 20; i++) {
            computers.set(i, 1);
        }
    }

    // This method sets all computers in the laboratory as available.
    public synchronized void setAllComputersAsAvailable() {
        for (int i = 0; i < 20; i++) {
            computers.set(i, 0);
        }
    }
}
