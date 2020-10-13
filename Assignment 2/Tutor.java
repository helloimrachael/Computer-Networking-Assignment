// Puri Rachele 547938

import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class Tutor {

    int numOfComputers = 20;
    ArrayList<Integer> computers;
    
    // Lock
    final Lock l;

    // Condition Variables
    final Condition allComputersAreAvailable;
    final Condition computerXIsAvailable;
    final Condition aComputerIsAvailable;

    public Tutor() {
        computers = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            computers.add(i, 0); // If computers.get(i) == 0 means that it is available, otherwise it is busy.
        }

        l = new ReentrantLock();
        allComputersAreAvailable = l.newCondition();
        computerXIsAvailable = l.newCondition();
        aComputerIsAvailable = l.newCondition();
    }

    // This method returns the number of available computer.
    public int numberOfAvailableComputers() { 
        int counter = 0;
        for(int i = 0; i < 20; i++) {
            if(computers.get(i) == 0)
                counter++;
        }
        return counter;
    }

    // This method returs the index of the first available computer.
    public int findTheFirstAvailableComputer() {
        for(int i = 0; i < 20; i++) {
            if(computers.get(i) == 0) {
                computers.set(i, 1);
                return i;
            }
        }
        return -1;
    }

    // This method sets computer X as available.
    public void setComputerXAsAvailable(int x) {
        computers.set(x, 0);
    }

    // This method returns true if computer X is available, otherwise it returs false.
    public boolean isComputerXAvailable(int x) {
        if(computers.get(x) == 0)
            return true;
       
        return false;
    }

    // This method sets computer X as busy.
    public void setComputerXAsBusy(int x) {
        computers.set(x, 1);
    }

    // This method returns true if the whole laboratory is available, otherwise it returns false.
    public boolean theWholeLaboratoryIsAvailable() {
        for(int i = 0; i <20; i++) {
            if(computers.get(i) == 1)
                return false;
        }
        return true;
    }

    // This method sets all computers in the laboratory as busy.
    public void setAllComputersAsBusy() {
        for(int i = 0; i < 20; i++) {
            computers.set(i, 1);
        }
    }

    // This method sets all computers in the laboratory as available.
    public void setAllComputersAsAvailable() {
        for(int i = 0; i < 20; i++) {
            computers.set(i, 0);
        }
    }
}
