package Exercice3_2;

import java.awt.Color;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.awt.Graphics;

public class Moteur implements Runnable {
	ConcurrentLinkedQueue<Balle> queue;
	Panneau pan;
	Horloge horloge;
	int ball_limit, ball_size;
	int collisions;
	boolean running;
	int win_width, win_height;
	public Moteur(Panneau pan, int ball_limit, int ball_size) {
		this.queue = new ConcurrentLinkedQueue<Balle>();
		this.pan = pan;
		this.horloge = new Horloge();
		this.ball_limit = ball_limit;
		this.collisions = 0;
		this.ball_size = ball_size;
		this.running = false;

		Thread thh = new Thread(this.horloge);
		thh.setDaemon(true);
		thh.start();
	}
	public void setWinDim(int width, int height) {
		this.win_width = width;
		this.win_height = height;
	}
	public void newBall() {
		if(this.queue.size() >= this.ball_limit) return;
		this.queue.add(new Balle(this.ball_size, this.win_width, this.win_height));
	}
	public void remBall() {
		this.queue.poll();
	}
	public void drawBalls(Graphics g) {
		Object array[] = this.queue.toArray();
		for(int i=0; i<this.queue.size(); i++) {
			Balle balle = (Balle)array[i];
			g.setColor(balle.getColor());
			g.fillOval(balle.getPosX(), balle.getPosY(), this.ball_size, this.ball_size);
		}
		g.setColor(Color.white);
		g.drawString("Temps: "+this.horloge.getTime()+"s", 4, 14);
		g.drawString("Balles: "+array.length+"/"+this.ball_limit, 4, 24);
		g.drawString("Score: "+this.collisions, 4, 34);
		g.drawString("Cadre: "+this.win_width+"x"+this.win_height, 4, 44);
	}
	public boolean Collision(Balle b1, Balle b2) {
		int posX1 = b1.getPosX();
		int posY1 = b1.getPosY();
		int posX2 = b2.getPosX();
		int posY2 = b2.getPosY();
		if((posX1-posX2)*(posX1-posX2)+(posY1-posY2)*(posY1-posY2) < this.ball_size*this.ball_size) return true;
		return false;
	}
	public void beginMove() {
		Object array[] = this.queue.toArray();
		for(int i=0; i<array.length; i++) {
			Balle balle = (Balle)array[i];
			int posX = balle.getPosX();
			int posY = balle.getPosY();
			if(posX < 0 || posX > this.win_width-this.ball_size) {
				balle.setMovX(-balle.getMovX());
			}
			if(posY < 0 || posY > this.win_height-this.ball_size) {
				balle.setMovY(-balle.getMovY());
			}
			balle.setPosX(posX+balle.getMovX());
			balle.setPosY(posY+balle.getMovY());
		}
		for(int i=0; i<array.length; i++) {
			for(int j=i+1; j<array.length; j++) {
				if(Collision((Balle)array[i], (Balle)array[j])) {
					this.collisions++;
					this.queue.remove(array[i]);
					this.queue.remove(array[j]);
				}
			}
		}
	}
	public void start() {
		this.running = true;
		this.horloge.start();
	}
	public void stop() {
		this.running = false;
		this.horloge.stop();
	}
	public void run() {
		try {
			while(true) {
				if(this.running) beginMove();
				this.pan.repaint();
				Thread.sleep(6);
			}
		} catch(InterruptedException e) {}
	}
}