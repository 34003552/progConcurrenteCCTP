package Exercice3_2;

public class Horloge implements Runnable {
	int time;
	boolean running;
	public Horloge() {
		this.time = 0;
		this.running = false;
	}
	public synchronized int getTime() {
		return this.time;
	}
	public synchronized void incTime() {
		this.time++;
	}
	public void start() {
		this.running = true;
	}
	public void stop() {
		this.running = false;
	}
	public void run() {
		try {
			while(true) {
				Thread.sleep(1000);
				if(this.running) incTime();
			}
		} catch(InterruptedException e) {}
	}
}