package Exercice3_2;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.BorderLayout;

public class Panneau extends JPanel {
	Fenetre fenetre;
	Moteur moteur;
	public Panneau(Fenetre fenetre) {
		this.fenetre = fenetre;
		this.moteur = new Moteur(this, 50, 30);
		Thread thm = new Thread(this.moteur);
		thm.setDaemon(true);
		thm.start();

		this.setBackground(Color.darkGray);
		this.setLayout(new BorderLayout());

		JPanel jpan = new JPanel();
		jpan.setBackground(Color.gray);
		jpan.add(new Bouton(this.moteur, "start"));
		jpan.add(new Bouton(this.moteur, "+"));
		jpan.add(new Bouton(this.moteur, "-"));
		this.add(jpan, BorderLayout.SOUTH);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension dimension = this.fenetre.getBounds().getSize();
		int width  = (int)dimension.getWidth()-20;
		int height = (int)dimension.getHeight()-80;
		g.setColor(Color.lightGray);
		g.drawRect(2, 2, width, height);
		this.moteur.setWinDim(width, height);
		this.moteur.drawBalls(g);
	}
}