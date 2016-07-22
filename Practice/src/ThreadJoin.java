class myThread extends Thread {
	public void run() {
		System.out.println("Thread " + this.getName() + " will run for 1 minute");
		try {
			this.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end");

	}
}

public class ThreadJoin {
	public static void main(String[] args) {
		myThread t = new myThread();
		t.setName("t thread");
		t.start();
		synchronized (t) {
			try {
				System.out.println("Before wait");
				t.wait();
				System.out.println("After wait");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < 10; i++)
			System.out.println(
					"Thread " + Thread.currentThread().getName() + " will continue after join and print : " + i);

	}
}
