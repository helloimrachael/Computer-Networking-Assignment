// Puri Rachele 547938

import java.util.concurrent.*;

public class ThreadPool {
    private ThreadPoolExecutor executor;
    private LinkedBlockingQueue<Runnable> queue;

    public ThreadPool(int dimQueue) {
        queue = new LinkedBlockingQueue<>();
        executor = new ThreadPoolExecutor(dimQueue, dimQueue, 60L, TimeUnit.MILLISECONDS, queue);
    }

    public void executeTask(Consumatore task) {
        executor.execute(task);
    }

    public void closeThreadPool() {
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
    }
}