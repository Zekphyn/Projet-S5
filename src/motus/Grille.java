package motus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Grille extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int longueur;
	public int largeur;
	private Color couleurLignes ;
	//public int largeurCaseGraphique;
	Motus motus;
	//private Motus motus
	
	public Grille(Motus motus) {
		super();
		this.motus=motus;
		this.longueur=motus.getNbCoupsMax();
		this.largeur=motus.getTailleMot();
		couleurLignes = Color.BLACK;
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
		text.setMinimumSize(new Dimension(75,75));
		text.setPreferredSize(new Dimension(100,100));
		text.setFont(new Font("Serif", Font.PLAIN, 36));
		text.setBorder(blackline);
		add(text);
	}
	
	public void ajouter(char c, int i, int j) {
		Border blackline = BorderFactory.createLineBorder(couleurLignes,1); 
		JLabel text = new JLabel(Character.toString(c) ,JLabel.CENTER);
		text.setMinimumSize(new Dimension(100,100));
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
		int i;
		int j;
		setLayout(new GridLayout(longueur,largeur));
		this.setPreferredSize(new Dimension(800,600));
		for(i=  0 ; i<this.motus.getNbCoups(); i++) {	
			for( j = 0; j< largeur;j++) {
				ajouter(this.motus.getJeu()[i].charAt(j),i,j);
			}
		}
		if(motus.getGagne()==false) {
			if(this.motus.getNbCoups()<this.motus.getNbCoupsMax())
				for( j =0 ; j< largeur; j++) {
					if(motus.getTrouve()[j] == true)
						ajouter(motus.getMotRech().charAt(j));
					else
						ajouter('.');
					}
			for(i=motus.getNbCoups()+1;i<longueur;i++)
				for( j=0; j < largeur ;j++)
					ajouter(' ');
			}
		else
		{
			for(i=motus.getNbCoups();i<longueur;i++)
				for( j=0; j < largeur ;j++)
					ajouter(' ');
		}
	}
}
