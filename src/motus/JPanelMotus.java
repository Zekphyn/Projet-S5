package motus;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import projet.BackgroundPanel;
import projet.Classement;
import projet.Jeu;
import projet.Joueur;
import projet.Menu;

public class JPanelMotus extends BackgroundPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel mainPanel = new BackgroundPanel("src/images/backgroundMenu.png");
	JPanel panelMotus = new JPanel();
	JPanel panelSouth = new JPanel();
	JPanel panelTop = new JPanel();
	//JPanel panelLeft = new JPanel();
	JPanel grille = new JPanel();
	JFrame fenetre = new JFrame("Rejouer");
	JButton rejouer = new JButton("rejouer");
	JButton b = new JButton("Valider");
	JTextField jtf = new JTextField();
	//JLabel name;
	JLabel cherche;
	Motus motus;
	int width, height;
	
	GridBagConstraints gbc = new GridBagConstraints();
	
	public JPanelMotus(int width, int height) {
		super("src/images/backgroundMenu.png");
		try {
			this.motus = new Motus();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(motus.getJoueur().getNom() == "") {
			String nom = JOptionPane.showInputDialog(null, "Bonjour ! Quelle est votre Pseudo ?");
			motus.getJoueur().setNom(nom);
			if(nom == null) motus.getJoueur().setNom("joueur");
		}
		this.width=width;
		this.height=height;
		setSize(width, height);
		this.setOpaque(true);
		this.grille = new Grille(motus);
		this.setLayout(new BorderLayout());
		panelMotus.setOpaque(false);
		panelSouth.setOpaque(false);
		panelTop.setOpaque(false);
		this.add(panelMotus,BorderLayout.CENTER);
		this.add(panelSouth, BorderLayout.SOUTH);
		this.add(panelTop, BorderLayout.NORTH);
		Font police = new Font("Arial", Font.BOLD, 14);
	    jtf.setFont(police);
	    jtf.setPreferredSize(new Dimension(150, 30));
	    this.cherche = new JLabel("Le mot caché est un mot de " + motus.getTailleMot() + " lettres");
		panelTop.setLayout(new BorderLayout());
	    cherche.setFont(new Font("Arial", Font.BOLD,35));
	    cherche.setForeground(Color.RED);
	/*    this.nom = new JLabel("Nom Joueur : "+ motus.getJoueur().getNom());
	    nom.setFont(new Font("Arial", Font.BOLD,35));
	    nom.setForeground(Color.BLACK);
	   panelLeft.setOpaque(false);
	    panelLeft.setLayout(new BorderLayout());
	    this.add(panelLeft, BorderLayout.WEST);
	    panelLeft.add(nom, BorderLayout.SOUTH);*/
	    creerJeu();
	    seetButton(b,100,30);
	}
	
	public JPanelMotus(Joueur joueur, int width, int height) {
		super("src/images/backgroundMenu.png");
		try {
			this.motus = new Motus();
		} catch (IOException e) {
			e.printStackTrace();
		}
		motus.setJoueur(joueur);
		this.width=width;
		this.height=height;
		setSize(width, height);
		this.setOpaque(true);
		this.grille = new Grille(motus);
		this.setLayout(new BorderLayout());
		panelMotus.setOpaque(false);
		panelSouth.setOpaque(false);
		panelTop.setOpaque(false);
		this.add(panelMotus,BorderLayout.CENTER);
		this.add(panelSouth, BorderLayout.SOUTH);
		this.add(panelTop, BorderLayout.NORTH);
		Font police = new Font("Arial", Font.BOLD, 14);
	    jtf.setFont(police);
	    jtf.setPreferredSize(new Dimension(150, 30));
	    this.cherche = new JLabel("Le mot caché est un mot de " + motus.getTailleMot() + " lettres");
	    cherche.setFont(new Font("Arial", Font.BOLD,35));
	    cherche.setForeground(Color.RED);
	    creerJeu();
	    seetButton(b,100,30);
	}
	
	public void creerJeu() {
		panelMotus.setLayout(new GridBagLayout());
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 1;
		creerGrille(grille);
		panelTop.setLayout(new GridBagLayout());
		panelSouth.add(jtf,gbc);
		panelTop.add(cherche);
	}
	
/*	public void creerPanelTop() {
		panelTop.setLayout(new GridBagLayout());
		gbc.weightx = 10;
		gbc.weighty= 10;
		gbc.gridheight = 10;
		gbc.gridwidth = 10;
		gbc.gridx =8;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		panelTop.add(cherche,gbc);
	}*/
	
	public void refresh(){
		this.revalidate();
	}
	public void jouerCoup(String mot) {
		motus.jouerCoup(mot);
		panelMotus.removeAll();
		creerJeu();
		updateButton(b, 100,30);
	}
	
	public void seetButton(JButton button, int width, int height) {
		button.setPreferredSize(new Dimension(width,height));
		button.addActionListener(new BoutonListener());
		panelSouth.add(button,gbc);
		//button.addActionListener(this);
	}

	public void updateButton(JButton button, int width, int height)
	{
		button.setPreferredSize(new Dimension(width,height));
		//button.addActionListener(new BoutonListener());
		panelSouth.add(button,gbc);
	}
	
	public void creerGrille(JPanel grille){
		mainPanel.remove(grille);
		panelMotus.add(new Grille(motus),gbc);
	}


	public JPanel getJPanelMotus() {
		return mainPanel;
	}

	
	  class BoutonListener implements ActionListener{
		    //Redéfinition de la méthode actionPerformed()
		    public void actionPerformed(ActionEvent arg0) {
		    	Object[] options = {"Rejouer", "Menu","Quitter"};
		    	int option=0;
		    	System.out.println(motus.getMotRech());
		    	Mot mot = new Mot(jtf.getText());
		   		System.out.println(mot.getMot());
		   		jtf.setText("");   
		   		if(mot.getMot().length() == motus.getTailleMot() && mot.getValable())
		   			jouerCoup(mot.getMot());
	    		if(motus.getGagne()) {
	    			refresh();
	    			panelTop.removeAll();
		    		panelSouth.removeAll();
			    	Classement classement = new Classement();
			    	classement.setScoreCSV(motus.getJoueur());
					option = JOptionPane.showOptionDialog(null,"Bravo !! Vous avez trouvé le mot "+ motus.getMotRech()+
	                        " en " +
	                        motus.getNbCoups()+ " coup" + ((motus.getNbCoups() > 1) ? "s" : "") 
						    , null, JOptionPane.YES_NO_CANCEL_OPTION,
						    JOptionPane.QUESTION_MESSAGE,
						    null,
						    options,
						    options[2]);
					
					
		    	}
		    	
		    	if(motus.perdu())
		    	{
		    		refresh();
		    		panelTop.removeAll();
		    		panelSouth.removeAll();
					option = JOptionPane.showOptionDialog(null,"Perdu !! Le mot caché etait "+ motus.getMotRech() 
					,null, JOptionPane.YES_NO_CANCEL_OPTION,
						    JOptionPane.QUESTION_MESSAGE,
						    null,
						    options,
						    options[2]);
		    	}
		    	refresh();
		    	if(motus.perdu() || motus.getGagne()) {
					refresh();
					if(option == JOptionPane.OK_OPTION) {
						//Classement.setScore(joueur);
						removeAll();
						add(new JPanelMotus(motus.getJoueur(),width,height));
						revalidate();
						repaint();
						
				}else if(option == JOptionPane.NO_OPTION) {
					revalidate();
					Jeu.fenetre.dispose();
					new Jeu("Jeu");
					repaint();
					}else if(option == JOptionPane.CANCEL_OPTION){
						System.exit(0); 
					}
		    	}
		  }
	  
	  			
	 }
}
