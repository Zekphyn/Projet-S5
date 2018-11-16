package motus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelMotus extends JPanel{
	private JPanel grille;
	
	public JPanelMotus(Graphics g,Motus motus){
		this.grille = new Grille(motus);
		creerGrille(g);
	}
	
	public void creerGrille(Graphics g){
		this.setLayout(new BorderLayout());
		this.add(grille, BorderLayout.NORTH);
		this.setComponentZOrder(grille, 0);
		this.setBackground(Color.BLUE);
	}
	
}
