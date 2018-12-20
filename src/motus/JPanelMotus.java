package motus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
	//JPanel² panelLeft = new JPanel();
	JPanel grille = new JPanel();
	JFrame fenetre = new JFrame("Rejouer");
	JButton rejouer = new JButton("rejouer");
	JButton b = new JButton("Valider");
	JTextField jtf = new JTextField();
	JLabel name;
	JLabel cherche;
	Motus motus;
	int width, height;
	
	GridBagConstraints gbc = new GridBagConstraints();
	
	public JPanelMotus(int width, int height) {
		//background
		super("src/images/backgroundMenu.png");
		//creer Motus
		try {
			this.motus = new Motus();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//creer joueur
		if(motus.getJoueur().getNom() == "") {
			String nom = JOptionPane.showInputDialog(null, "Bonjour ! Quelle est votre Pseudo ?");
			motus.getJoueur().setNom(nom);
			if(nom == null) motus.getJoueur().setNom("joueur");
		}
		//taille du JPanel + opacité du fond
		this.width=width;
		this.height=height;
		setSize(width, height);
		this.setOpaque(true);
		//crée panel grille de motus
		this.grille = new Grille(motus);
		//crée la grille pour placer les panels
		this.setLayout(new BorderLayout());
		//place les panels
		panelMotus.setOpaque(false);
		panelSouth.setOpaque(false);
		panelTop.setOpaque(false);
		this.add(panelMotus,BorderLayout.CENTER);
		this.add(panelSouth, BorderLayout.SOUTH);
		this.add(panelTop, BorderLayout.NORTH);
		//crée le champs de text
		Font police = new Font("Arial", Font.BOLD, 14);
	    jtf.setFont(police);
	    jtf.setPreferredSize(new Dimension(150, 30));
	    //crée un JLabel
	    this.cherche = new JLabel("Le mot caché est un mot de " + motus.getTailleMot() + " lettres");
	    cherche.setFont(new Font("Arial", Font.BOLD,35));
	    cherche.setForeground(Color.RED);
	    cherche.setHorizontalAlignment(JLabel.CENTER);
	    //crée un JLabl pour le nom
	  this.name = new JLabel("Nom Joueur : "+ motus.getJoueur().getNom());
	    name.setFont(new Font("Arial", Font.BOLD,35));
	    name.setForeground(Color.BLACK);
	    //crée le jeu
	    creerJeu();
	    seetButton(b,100,30);
	}
	
	public JPanelMotus(Joueur joueur, int width, int height) {
		//background
		super("src/images/backgroundMenu.png");
		//creer Motus
		try {
			this.motus = new Motus();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//creer joueur
		motus.setJoueur(joueur);
		//taille du JPanel + opacité du fond
		this.width=width;
		this.height=height;
		setSize(width, height);
		this.setOpaque(true);
		//crée panel grille de motus
		this.grille = new Grille(motus);
		//crée la grille pour placer les panels
		this.setLayout(new BorderLayout());
		//place les panels
		panelMotus.setOpaque(false);
		panelSouth.setOpaque(false);
		panelTop.setOpaque(false);
		this.add(panelMotus,BorderLayout.CENTER);
		this.add(panelSouth, BorderLayout.SOUTH);
		this.add(panelTop, BorderLayout.NORTH);
		//crée le champs de text
		Font police = new Font("Arial", Font.BOLD, 14);
	    jtf.setFont(police);
	    jtf.setPreferredSize(new Dimension(150, 30));
	    //crée un JLabel
	    this.cherche = new JLabel("Le mot caché est un mot de " + motus.getTailleMot() + " lettres");
	    cherche.setFont(new Font("Arial", Font.BOLD,35));
	    cherche.setForeground(Color.RED);
	    cherche.setHorizontalAlignment(JLabel.CENTER);
	    //crée un JLabl pour le nom
	    this.name = new JLabel("Nom Joueur : "+ motus.getJoueur().getNom());
	    name.setFont(new Font("Arial", Font.BOLD,35));
	    name.setForeground(Color.BLACK);
	    //crée le jeu
	    creerJeu();
	    //crée le bouton b;
	    seetButton(b,100,30);
	}
	//crée le jeu : crée la grille, la place, place les JLabel et JTextField et mets a jour le bouton
	public void creerJeu() {
		panelMotus.setLayout(new GridBagLayout());
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 1;
		creerGrille(grille);
		panelTop.setLayout(new BorderLayout());
		panelTop.add(cherche);
		panelSouth.add(jtf,gbc);
		panelTop.add(name, BorderLayout.WEST);
		updateButton(b, 100,30);
	}
	
	public void creerPanelTop() {
		//panelTop.setLayout(new GridLayout());
		
		/*panelTop.setLayout(new GridBagLayout());
		gbc.weightx = 10;
		gbc.weighty= 10;
		gbc.gridheight = 10;
		gbc.gridwidth = 10;
		gbc.gridx =8;
		gbc.gridy = 0;
		panelTop.add(cherche,gbc);*/
	}
	
	public void refresh(){
		this.revalidate();
	}
	
	//joue un coup et mets à jour l'affichage.
	public void jouerCoup(String mot) {
		motus.jouerCoup(mot);
		panelMotus.removeAll();
		creerJeu();

	}
	
	//crée un bouton et l'ajoute au panel du bas
	public void seetButton(JButton button, int width, int height) {
		button.setPreferredSize(new Dimension(width,height));
		button.addActionListener(new BoutonListener());
		panelSouth.add(button,gbc);
	}

	//mets a jour un bouton sur le pannel du bas.
	public void updateButton(JButton button, int width, int height)
	{
		button.setPreferredSize(new Dimension(width,height));
		panelSouth.add(button,gbc);
	}
	
	//mets a jour la grille
	public void creerGrille(JPanel grille){
		mainPanel.remove(grille);
		panelMotus.add(new Grille(motus),gbc);
	}


	public JPanel getJPanelMotus() {
		return mainPanel;
	}

	//lorsqu'on appuie sur le bouton : ((ecrit le motRech dans la console pour les tests),
	//joue un coup si le mots est de la bonne longueur
	//si on a gagné : mets a jour le classement etc et propose de rejouer
	//perdu : propose de rejouer ou quitter ou retour menu
	//
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
					//SI Rejouer : rejouer
					if(option == JOptionPane.OK_OPTION) {
						removeAll();
						add(new JPanelMotus(motus.getJoueur(),width,height));
						revalidate();
						repaint();
						//si menu ; reoturn menu
				}else if(option == JOptionPane.NO_OPTION) {
					revalidate();
					Jeu.fenetre.dispose();
					new Jeu("Jeu");
					repaint();
					//quitter
					}else if(option == JOptionPane.CANCEL_OPTION){
						System.exit(0); 
					}
		    	}
		  }
	  
	  			
	 }
}
