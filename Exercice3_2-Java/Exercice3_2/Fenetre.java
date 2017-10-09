package Exercice3_2;

import javax.swing.JFrame;

public class Fenetre extends JFrame {
	public Fenetre() {
		this.setTitle("Balles en mouvement");
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Panneau pan = new Panneau(this);
		this.setContentPane(pan);
		this.setVisible(true);
	}
}