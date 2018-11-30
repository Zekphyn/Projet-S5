package MotsMeles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GrilleMots extends JPanel {
	private int largeur;
	MotsMeles motsmeles;
	private Color couleurLignes ;
	
	public GrilleMots(MotsMeles motsmeles)
	{	
		super();
		this.motsmeles = motsmeles;
		this.largeur = motsmeles.getHauteurlisteMots();
		couleurLignes = Color.BLACK;
		majGrille();
		
	}
	
	public void ajouter(String s)
	{
		Border blackline = BorderFactory.createLineBorder(couleurLignes,1); 
		JLabel text = new JLabel(s ,JLabel.CENTER);
		text.setMinimumSize(new Dimension(75,75));
		text.setPreferredSize(new Dimension(200,20));
		text.setFont(new Font("Serif", Font.PLAIN, 20));
		text.setBorder(blackline);
		add(text);
	}
	
	public void majGrille()
	{
		setLayout(new GridLayout(largeur,0));
		for(int i=0; i<largeur; i++)
		{
			ajouter(motsmeles.getListeMots().get(i));
		}
	}
}
