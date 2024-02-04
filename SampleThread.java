// Sample program to run functions using threads.

// Sample program to run functions using threads.

class Sample implements Runnable 
{
	Thread thread1;
	Sample() {
		thread1 = new Thread(this);
		thread1.start();

	}
	void printName() {
		System.out.println("Sahith");
	}
	public void run() {
		try {
			while (true) {
				printName();
				Thread.sleep(200); // Corrected to Thread.sleep() instead of Thread1.sleep()
			}
		}
		catch (InterruptedException exception) {
			System.out.println(exception);
		}
	}
}
class SampleThread
{
	public static void main(String[] args) {
		new Sample();
	}
}
