package MotsMeles;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import projet.BackgroundPanel;
import projet.Joueur;

public class JPanelMotsMeles extends BackgroundPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel mainPanel = new BackgroundPanel("src/images/backgroundMenu.png"); //Arriere-plan
	JPanel panelMotsMeles = new JPanel();
	JPanel grille = new JPanel();
	MotsMeles motsmeles;
	GridBagConstraints gbc = new GridBagConstraints();
	GrilleMots rep;
	int width, height;
	
	//Si joueur déjà défini
	public JPanelMotsMeles(Joueur joueur, int width, int height)
	{
		super("src/images/backgroundMenu.png");
		try {
			this.motsmeles = new MotsMeles();
		} catch (IOException e) {
			e.printStackTrace();
		}
		motsmeles.setJoueur(joueur);
		this.width = width;
		this.height = height;
		setSize(width, height);
		this.setOpaque(true);
		rep = new GrilleMots(motsmeles);
		this.grille = new Grille(motsmeles,rep);
		mainPanel.setLayout(new BorderLayout());
		panelMotsMeles.setOpaque(false);
		mainPanel.add(panelMotsMeles,BorderLayout.CENTER);
	    creerJeu();
	}
	
	//Sinon
	public JPanelMotsMeles(int width, int height)
	{
		super("src/images/backgroundMenu.png");
		try {
			this.motsmeles = new MotsMeles();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(motsmeles.getJoueur().getNom() == "") {
			String nom = JOptionPane.showInputDialog(null, "Bonjour ! Quelle est votre Pseudo ?");
			motsmeles.getJoueur().setNom(nom);
			if(nom == null) motsmeles.getJoueur().setNom("joueur");
		}
		this.width = width;
		this.height = height;
		setSize(width, height);
		this.setOpaque(true);
		rep = new GrilleMots(motsmeles);
		this.grille = new Grille(motsmeles,rep);
		mainPanel.setLayout(new BorderLayout());
		panelMotsMeles.setOpaque(false);
		mainPanel.add(panelMotsMeles,BorderLayout.CENTER);
	    creerJeu();
	}

	public JPanel getJPanelMotsMeles() {
		return this.mainPanel;
	}
	
	//Initialisation des grilles dans le JPanel
	public void creerJeu() {
		creerGrille(grille, 500, 500);
	}

	//Ajoute la grille et la grille de mots au JPanel
	public void creerGrille(JPanel grille, int width, int height){
		grille.setPreferredSize(new Dimension(width, height));
		mainPanel.remove(grille);
		panelMotsMeles.add(new Grille(motsmeles,rep));
		panelMotsMeles.add(rep);
	}
}