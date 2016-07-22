import java.util.concurrent.CountDownLatch;

public class ThreadPriority {
	public static void main(String[] args) throws Exception {

		// Create a countdown latch for 1 thread. (We just need to wait for 1
		// thread to complete)
		CountDownLatch countDown = new CountDownLatch(2);

		// Create two threads
		Thread firstThread = new Thread(new Task(countDown));
		Thread secondThread = new Thread(new Task(countDown));
		Thread secondThread1 = new Thread(new Task(countDown));

		// Set their names
		firstThread.setName("JobOne");
		secondThread.setName("JobTwo");
		secondThread1.setName("JobThree");

		// Start first thread
		firstThread.start();
		secondThread1.start();
		// Wait for countdown to decrease
		countDown.await();

		// Start second thread
		secondThread.start();

	}
}

/**
 * The class represents a Task.
 * 
 * @author sgaur
 *
 */
class Task implements Runnable {
	CountDownLatch countLatch = null;

	public Task(CountDownLatch countDown) {
		countLatch = countDown;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(i + " Running " + Thread.currentThread().getName());
		}

		// After the process is complete, decrement the countDown
		countLatch.countDown();
	}

}