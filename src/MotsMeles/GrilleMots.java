package MotsMeles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GrilleMots extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int largeur;
	MotsMeles motsmeles;
	private Color couleurLignes ;
	
	public GrilleMots(MotsMeles motsmeles)
	{	
		super();
		this.motsmeles = motsmeles;
		this.largeur = motsmeles.getHauteurlisteMots();
		couleurLignes = Color.BLACK;
		init();
	}
	
	// initialise la grille de mot et le visuel
	public void init() {
		setLayout(new GridLayout(largeur,0));
		for(int i=0; i<largeur; i++)
		{
			initGrilleMot(motsmeles.getListeMots().get(i), i);
		}
	}
  
	// cr�er le visuel de la grille de mot
	public void initGrilleMot(String s, int i)
	{
		Border blackline = BorderFactory.createLineBorder(couleurLignes,1); 
		JLabel text = new JLabel(s ,JLabel.CENTER);
		text.setMinimumSize(new Dimension(75,75));
		text.setPreferredSize(new Dimension(200,20));
		text.setFont(new Font("Serif", Font.PLAIN, 20));
		text.setBorder(blackline);
		text.setOpaque(true);
		add(text);
	}
	
	// change la couleur du mot se trouvant dans le jlabel numero i s'il n'est pas déjà de cette couleur et incrémente le
	// nombre de mot trouvé
	public void majGrille(int i)
	{
		if(motsmeles.getTabListeMot()[i] == true)
		{
			if(this.getComponent(i).getBackground() != Color.RED) {
			this.getComponent(i).setBackground(Color.RED);
			motsmeles.nbMotTrouve++;
			}
		}
	}
}
