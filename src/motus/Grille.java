package motus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.text.StyleConstants;

public class Grille extends JPanel{
	public int longueur;
	public int largeur;
	private Color couleurLignes ;
	public int largeurCaseGraphique;
	Motus motus;
	//private Motus motus
	
	public Grille(Motus motus) {
		super();
		this.motus=motus;
		this.longueur=motus.getNbCoupsMax();
		this.largeur=motus.getTailleMot();
		couleurLignes = Color.BLACK;
		this.setPreferredSize(new Dimension(500,500));
		majGrille();


	}
	
/*	public void initGrille() {
		Border blackline = BorderFactory.createLineBorder(couleurLignes,1); 
		setLayout(new GridLayout(longueur, largeur));
		JLabel text;
		for(int i =0  ; i<longueur; i++)
			for(int j = 0; j<largeur; j++) {
				if(i == 0 && j == 0) {
					text = new JLabel(Character.toString(motus.getMotRech().charAt(0)),JLabel.CENTER);
				}
				else 
				{
					text = new JLabel(".",JLabel.CENTER);
				}
				text.setFont(new Font("Serif", Font.PLAIN, 36));
				text.setBorder(blackline);
				add(text);
			}
	} */
	
	public void ajouter(char c) {
		Border blackline = BorderFactory.createLineBorder(couleurLignes,1); 
		JLabel text = new JLabel(Character.toString(c) ,JLabel.CENTER);
		text.setFont(new Font("Serif", Font.PLAIN, 36));
		text.setBorder(blackline);
		add(text);
	}
	
	public void ajouter(char c, int i, int j) {
		Border blackline = BorderFactory.createLineBorder(couleurLignes,1); 
		JLabel text = new JLabel(Character.toString(c) ,JLabel.CENTER);
		text.setFont(new Font("Serif", Font.PLAIN, 36));
		text.setBorder(blackline);
		if(motus.getTabVerif()[i][j]==1) {
			text.setBackground(Color.RED);
			text.setOpaque(true);
		}
		if(motus.getTabVerif()[i][j]==2) {
			text.setBackground(Color.YELLOW);
			text.setOpaque(true);
		}
		add(text);
	}
	
	public void majGrille() {
		setLayout(new GridLayout(longueur,largeur));
		for(int i=  0 ; i<this.motus.getNbCoups(); i++) {	
			for(int j = 0; j< largeur;j++) {
				ajouter(this.motus.getJeu()[i].charAt(j),i,j);
			}
		}
		if(this.motus.getNbCoups()<this.motus.getNbCoupsMax()) 
			for(int j =0 ; j< largeur; j++) {
				if(motus.getTrouve()[j] == true)
					ajouter(motus.getMotRech().charAt(j));
				else
					ajouter(' ');
		}
		
			for(int i=this.motus.getNbCoups()+1;i<longueur;i++)
				for(int j=0; j < largeur ;j++)
					ajouter(' ');
	}
}
