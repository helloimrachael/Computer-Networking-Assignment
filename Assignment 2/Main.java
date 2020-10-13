// Puri Rachele 547938

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        int numOfPostalCounters  = 4;
        int dimSecondQueue = (int) (Math.random()*20); // Number of tasks in the second room.
        int dimFirstQueue = (int) (Math.random()*20); // Number of tasks in the first room.
        ArrayBlockingQueue<Task> firstQueue = new ArrayBlockingQueue<>(dimFirstQueue); // Queue of the first room.
        PostOffice postOffice = new PostOffice(dimSecondQueue, numOfPostalCounters);
        boolean postOfficeIsNotEmpty = true;

        while(postOfficeIsNotEmpty) {
            // Creation of dimFirstQueue tasks.
            for(int i = 0; i < dimFirstQueue; i++) {
                Task task = new Task("Task" + i);
                try {
                    firstQueue.put(task);
                    System.out.println("The task" + i + " has been placed in waiting room number 1");
				} catch (InterruptedException e) {
					e.printStackTrace();
                }
            }

            // Execution of dimSecondQueue tasks.
            int j = 0;
            while(j < dimSecondQueue && firstQueue.isEmpty() == false){
                try {
                    postOffice.executeTask(firstQueue.take());
                    j++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Execution of the remaining tasks.
            while(!firstQueue.isEmpty()) {
                if(postOffice.sizeQueue() < dimSecondQueue) {
                    try {
                        postOffice.executeTask(firstQueue.take());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            postOfficeIsNotEmpty = false;
        }

        // Closing of the post office.
        postOffice.closePostOffice();
        System.out.println("The post office is closed.");
    }
}
