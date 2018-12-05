package MotsMeles;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import projet.BackgroundPanel;
import projet.Joueur;

public class JPanelMotsMeles extends BackgroundPanel {
	JPanel mainPanel = new BackgroundPanel("src/images/backgroundMenu.png");
	JPanel panelMotsMeles = new JPanel();
	JPanel grille = new JPanel();
	//JTextField jtf = new JTextField();
	JButton b = new JButton("Valider");
	MotsMeles motsmeles;
	GridBagConstraints gbc = new GridBagConstraints();
	GrilleMots rep;
	int width, height;
	
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
	
	public void creerJeu() {
		//creerGrille(grille,1000,1000);
		creerGrille(grille, 500, 500);
	//	panelMotsMeles.add(jtf);		
	}

	public void creerGrille(JPanel grille, int width, int height){
		grille.setPreferredSize(new Dimension(width, height));
		mainPanel.remove(grille);
		panelMotsMeles.add(new Grille(motsmeles,rep));
		panelMotsMeles.add(rep);
	}
}