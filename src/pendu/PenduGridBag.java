package pendu;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import projet.BackgroundPanel;
import projet.Classement;
import projet.Jeu;
import projet.Joueur;

public class PenduGridBag extends BackgroundPanel{
	
	private static final long serialVersionUID = 1L;
	char[] lettres = {'a','z','e','r','t','y','u','i','o','p','q','s','d','f','g','h','j','k','l','m','w','x','c','v','b','n'};
	JPanel mainPanel = new BackgroundPanel("src/images/backgroundMenu.png");
	public Joueur joueur;
	private JButton bouton[];
	private JLabel mot, erreur;
	private ImageLabel image;
	int width ,height;
	Mot word;

	
	public PenduGridBag(int width, int height) throws IOException {
		super("src/images/backgroundMenu.png");
		joueur = new Joueur("Pendu","",0);
		word = new Mot();
		this.width = width;
		this.height = height;
		init();
	}
	
	public PenduGridBag(Joueur j,int width,int height) throws IOException {
		super("src/images/backgroundMenu.png");
		joueur = j;
		word = new Mot();
		this.width = width;
		this.height = height;
		init();
	}
	
	private void init() {
		this.setPreferredSize(new Dimension(width,height));
		if(joueur.getNom() == "") {
			String nom = JOptionPane.showInputDialog(null, "Bonjour ! Quelle est votre Pseudo ?");
			joueur.setNom(nom);
			if(nom.equals("")) joueur.setNom("joueur");
		}
		setSize(width,height);
		this.setMinimumSize(new Dimension(width,height));
		setLayout(new GridBagLayout());

		
		
		//L'objet servant à positionner les composants
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.weightx = 1;
	    gbc.weighty = 1;
		
		Dimension dim = new Dimension(width,height/6);
		
		// HEADER
		JPanel head = new JPanel(new BorderLayout());
		head.setBackground(Color.ORANGE);
		head.setMinimumSize(dim);
		head.setPreferredSize(dim);
		JLabel nomJoueur = new JLabel("Joueur : "+joueur.getNom());
		
		JLabel titre = new JLabel("Bienvenue dans le Jeu du Pendu");
		titre.setFont(new Font("Arial", Font.BOLD, 30));
		nomJoueur.setFont(new Font("Arial", Font.BOLD, 30));
		titre.setHorizontalAlignment(JLabel.CENTER);
		head.add(nomJoueur,BorderLayout.WEST);
		head.add(titre,BorderLayout.CENTER);
		head.setOpaque(false);
		
		
		
		//LEFTCONTEND
		JPanel left = new JPanel( new GridBagLayout());
		dim =new Dimension(width/2,2*height/3);
		left.setPreferredSize(dim);
		left.setMinimumSize(dim);
		

		JPanel clavier = new JPanel();
		clavier.setPreferredSize(new Dimension(7*width/24,height/8));
		clavier.setMinimumSize(new Dimension(7*width/24,height/8));
		
		BoutonListener bl = new BoutonListener();
		Dimension buttonDimension = new Dimension(50,30);
		this.bouton = new JButton[26];
		int i = 0;
		for(char c : lettres){
			this.bouton[i] = new JButton(String.valueOf(c).toUpperCase());
			bouton[i].addActionListener(bl);
			bouton[i].setPreferredSize(buttonDimension);
			clavier.add(bouton[i]);
			i++;
		}
		
		mot =  new JLabel(word.getMotSecret());
		mot.setFont(new Font("Comics Sans MS", Font.BOLD, 50));
		mot.setHorizontalAlignment(JLabel.CENTER);
		
		erreur = new JLabel("Vous avez le droit à "+(5 -word.getNombreErreur())+" erreurs.");
		erreur.setFont(new Font("Arial",Font.BOLD, 20));
		erreur.setHorizontalAlignment(JLabel.CENTER);


		clavier.setOpaque(false);
		left.setOpaque(false);
		
		left.add(mot,gbc);
		gbc.fill = GridBagConstraints.NONE;
		left.add(clavier,gbc);
		gbc.fill = GridBagConstraints.BOTH;
		left.add(erreur,gbc);
		
		
		//RIGHTCONTEND
		
		JPanel right = new JPanel(new GridBagLayout());
		this.image = new ImageLabel(); 
		this.image.setSize(dim);
		this.image.setVerticalAlignment(JLabel.CENTER);
		right.setBackground(Color.YELLOW);
		right.add(image,gbc);
		right.setPreferredSize(dim);
		right.setMinimumSize(dim);
		right.setOpaque(false);
		
		//BOTTOM
		JPanel bottom = new JPanel(new GridBagLayout());
		bottom.setOpaque(false);
		dim = new Dimension(width,height/6);
		bottom.setSize(dim);
		bottom.setMinimumSize(dim);
		bottom.setBackground(Color.ORANGE);
		
		
		JLabel regles = new JLabel("LES REGLES SONT LES SUIVANTES :");
		regles.setFont(new Font("Arial",Font.BOLD, 20));
		regles.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel regles2 = new JLabel("Vous devez trouver le mot secret en réalisant le moins de coup possible et en faisant le moins d'erreur possibles.");
		regles2.setFont(new Font("Arial",Font.BOLD, 15));
		regles2.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel regles3 = new JLabel	(" ATTENTION : si vous faites trop d'erreurs vous êtes pendu !");
		regles3.setFont(new Font("Arial",Font.BOLD, 15));
		regles3.setHorizontalAlignment(JLabel.CENTER);
		
		
		bottom.add(regles,gbc);
		bottom.add(regles2, gbc);
		bottom.add(regles3, gbc);
			
		
	    add(head, gbc,0,0,width,height/6);
	    add(left, gbc,0,height/4,width/2,height/2);	
	    add(right, gbc,width/2,height/4,width/2,height/2);	
	    add(bottom, gbc,0,height/4+height/2,width,height/6);

	    revalidate();
		repaint();
	    this.setVisible(true);		  
	}
	
	private void add(Component c, GridBagConstraints gbc, int x, int y, int w,
			int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		add(c, gbc);
	}
	
	class BoutonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){	
			((JButton)e.getSource()).setEnabled(false);
			erreur.setText("Vous avez le droit à "+(5 - word.getNombreErreur())+" erreurs.");
			//Custom button text
			Object[] options = {"Rejouer", "Menu","Quitter"};
			
			
			word.verifierMot(((JButton)e.getSource()).getText().charAt(0));
			mot.setText(word.getMotSecret());
			image.setImagePath("src/images/pendu"+(1+word.getNombreErreur())+".jpg");
			                   
			
			if(word.estFini()) {
				Classement classement = new Classement();
				classement.setScoreCSV(joueur);
				int option = JOptionPane.showOptionDialog(null,"Vous avez trouvé le mot " + word.getMot() +
                        " en " +
                        word.getNombreCoup() + " coup" + ((word.getNombreCoup() > 1) ? "s" : "") +
						", avec " + word.getNombreErreur() +
                        " erreur" + ((word.getNombreErreur() > 1) ? "s" : "") + ".\n",
					    "Bravo", JOptionPane.YES_NO_CANCEL_OPTION,
					    JOptionPane.QUESTION_MESSAGE,
					    null,
					    options,
					    options[2]);
				
				if(option == JOptionPane.OK_OPTION) {
					Jeu.fenetre.dispose();
					Jeu j =new Jeu("Jeu");
					try {
						j.lancerPendu(joueur);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}else if(option == JOptionPane.NO_OPTION) {
					Jeu.fenetre.dispose();
					new Jeu("Jeu");
				}else if(option == JOptionPane.CANCEL_OPTION){
					System.exit(0);
				}
				
				
				
				
				
			}else if(word.getNombreErreur()== 5) {
				int option = JOptionPane.showOptionDialog(null,"Dommage, vous avez perdu le mot était "+word.getMot()+".\n",
					    "Vous avez perdu", JOptionPane.YES_NO_CANCEL_OPTION,
					    JOptionPane.QUESTION_MESSAGE,
					    null,
					    options,
					    options[2]);
				
				if(option == JOptionPane.OK_OPTION) {
					Jeu.fenetre.dispose();
					Jeu j =new Jeu("Jeu");
					try {
						j.lancerPendu(joueur);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				
				}else if(option == JOptionPane.NO_OPTION) {
					Jeu.fenetre.dispose();
					new Jeu("Jeu");

				}else if(option == JOptionPane.CANCEL_OPTION){
					System.exit(0);
				}

			}
			
		}		
	}
}
