// Puri Rachele 547938

public class Task implements Runnable{
	
	private String taskName;
	
	public Task(String taskName) {
		this.taskName = taskName;
		}

	@Override
	public void run() {
        // Task execution time.
		Long executionTimer = (long) (Math.random()*4000);
		try {
            Thread.sleep(executionTimer);
            System.out.println(taskName + " terminated");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}