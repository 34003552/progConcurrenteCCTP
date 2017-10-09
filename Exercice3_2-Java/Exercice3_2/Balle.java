package Exercice3_2;

import java.awt.Color;

public class Balle {
	int posX, posY;
	int movX, movY;
	Color color;
	public Balle(int ball_size, int win_width, int win_height) {
		this.posX = (int)(Math.random()*(win_width-ball_size+1));
		this.posY = (int)(Math.random()*(win_height-ball_size+1));
		this.movX = ((int)(Math.random()*2) == 0) ? 1 : -1;   
		this.movY = ((int)(Math.random()*2) == 0) ? 1 : -1;
		this.color = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
	}
	public int getPosX() {
		return this.posX;
	}
	public int getPosY() {
		return this.posY;
	}
	public Color getColor() {
		return this.color;
	}
	public int getMovX() {
		return this.movX;
	}
	public int getMovY() {
		return this.movY;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public void setMovX(int movX) {
		this.movX = movX;
	}
	public void setMovY(int movY) {
		this.movY = movY;
	}
}