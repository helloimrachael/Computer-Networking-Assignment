// Puri Rachele 547938

import java.util.concurrent.*;

public class PostOffice{
    private ThreadPoolExecutor executor;
    private ArrayBlockingQueue<Runnable> queue; // Queue of the second room.

    public PostOffice(int dimQueue, int postalCounters) {
        queue = new ArrayBlockingQueue<>(dimQueue);
        executor = new ThreadPoolExecutor(postalCounters, postalCounters, 60L, TimeUnit.MILLISECONDS, queue);
    }

	public void executeTask (Task task) {
		executor.execute(task);
    }
    
    public int sizeQueue() {
		return queue.size();
    }
    
    public void closePostOffice() {
        executor.shutdown();
        while(!executor.isTerminated()) {}
	}
}