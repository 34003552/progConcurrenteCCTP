package Exercice3_2;

import javax.swing.JButton;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Bouton extends JButton implements MouseListener {
	Moteur moteur;
	String name;
	public Bouton(Moteur moteur, String name) {
		super(name);
		this.moteur = moteur;
		this.name = name;
		this.addMouseListener(this);
	}
	public void mouseClicked(MouseEvent event) {
		if(this.name == "start") {
			this.name = "stop";
			this.setText("stop");
			this.moteur.start();
		} 
		else if(this.name == "stop") {
			this.name = "start";
			this.setText("start");
			this.moteur.stop();
		}
		else if(this.name == "+") {
			this.moteur.newBall();
		}
		else if(this.name == "-") {
			this.moteur.remBall();
		}
	}
	public void mouseEntered(MouseEvent event) { }
	public void mouseExited(MouseEvent event) { }
	public void mousePressed(MouseEvent event) { }
	public void mouseReleased(MouseEvent event) { }
}