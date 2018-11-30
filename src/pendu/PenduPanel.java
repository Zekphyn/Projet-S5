package pendu;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;

import projet.Classement;
import projet.Jeu;
import projet.Joueur;
import projet.Menu;



public class PenduPanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	char[] lettres = {'a','z','e','r','t','y','u','i','o','p','q','s','d','f','g','h','j','k','l','m','w','x','c','v','b','n'};
	public Joueur joueur;
	private JButton bouton[];
	private JLabel mot, erreur;
	private ImageLabel image;
	int width ,height;


	Mot word;
	
	
	public PenduPanel(int width, int height) throws IOException {
		joueur = new Joueur("Pendu","",0);
		word = new Mot();
		this.width = width;
		this.height = height;
		init(width,height);
	}
	
	public PenduPanel(Joueur j,int width,int height) throws IOException {
		joueur = j;
		word = new Mot();
		init(width,height);
	}
	
	
	public void init(int width,int height){
		if(joueur.getNom() == "") {
			String nom = JOptionPane.showInputDialog(null, "Bonjour ! Quelle est votre Pseudo ?");
			joueur.setNom(nom);
			if(nom.equals("")) joueur.setNom("joueur");
		}
		setSize(width,height);
		Dimension dim = new Dimension(width,height/5);
		//this.setPreferredSize();
		
		// HEADER
		JPanel head = new JPanel(new BorderLayout());
		head.setBackground(Color.ORANGE);
		head.setPreferredSize(dim);
		JLabel nomJoueur = new JLabel("Joueur : "+joueur.getNom());
		
		JLabel titre = new JLabel("Bienvenue dans le Jeu du Pendu");
		titre.setFont(new Font("Arial", Font.BOLD, 30));
		nomJoueur.setFont(new Font("Arial", Font.BOLD, 30));
		titre.setHorizontalAlignment(JLabel.CENTER);
		head.add(nomJoueur,BorderLayout.WEST);
		head.add(titre,BorderLayout.CENTER);
		
		
		//LEFTCONTEND
		JPanel left = new JPanel(new BorderLayout());
		left.setBackground(Color.YELLOW);
		dim =new Dimension(width/2,height/2);
		left.setPreferredSize(dim);
		left.setMinimumSize(new Dimension(width/4,height/3));
		
		JPanel motSecret =new JPanel(new BorderLayout());
		motSecret.setBackground(Color.yellow);
		mot =  new JLabel(word.getMotSecret());
		mot.setFont(new Font("Comics Sans MS", Font.BOLD, 50));
		mot.setHorizontalAlignment(JLabel.CENTER);
		motSecret.setPreferredSize(new Dimension(width/7,height/7));
		motSecret.add(mot,BorderLayout.CENTER);
		left.add(motSecret,BorderLayout.NORTH);
		
		JPanel clavier = new JPanel();
		clavier.setBackground(Color.YELLOW);
		clavier.setPreferredSize(new Dimension(width/4,height/10));
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
		left.add(clavier);

		JPanel err = new JPanel(new BorderLayout());
		err.setPreferredSize(new Dimension(width/7,height/6));
		err.setBackground(Color.yellow);
		erreur = new JLabel("Vous avez le droit à "+(5 -word.getNombreErreur())+" erreurs.");
		erreur.setFont(new Font("Arial",Font.BOLD, 20));
		erreur.setHorizontalAlignment(JLabel.CENTER);
		err.add(erreur,BorderLayout.CENTER);
		left.add(err,BorderLayout.SOUTH);
		
		//RIGHTCONTEND
		dim =new Dimension(width/2,height/3);
		JPanel right = new JPanel(new BorderLayout());
		this.image = new ImageLabel(); 
		this.image.setSize(dim);
		this.image.setVerticalAlignment(JLabel.CENTER);
		right.add(image,BorderLayout.CENTER);
		right.setBackground(Color.WHITE);
		right.setPreferredSize(dim);
		
		
		
		//BOTTOM
		JPanel bottom = new JPanel(new BorderLayout());
		dim = new Dimension(width,height/10);
		bottom.setSize(dim);
		JLabel regles = new JLabel("LES REGLES SONT LES SUIVANTES :");
		regles.setHorizontalAlignment(JLabel.CENTER);
		bottom.add(regles,BorderLayout.NORTH);
		JLabel regles2 = new JLabel("Vous devez trouver le mot secret en réalisant le moins de coup possible et en faisant le moins d'erreur possibles.\n"
				+"\n ATTENTION : si vous faites trop d'erreurs vous êtes pendu !");
		regles2.setHorizontalAlignment(JLabel.CENTER);
		bottom.add(regles2, BorderLayout.CENTER);
		
		setLayout(new BorderLayout());
		setBackground(Color.BLUE);
		
		add(head, BorderLayout.NORTH);
		add(left, BorderLayout.WEST);
		add(right, BorderLayout.EAST);
		add(bottom, BorderLayout.SOUTH);
		setVisible(true);
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
					//Classement.setScore(joueur);
					removeAll();
						try {
							add(new PenduPanel(joueur,width,height));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

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
				
				
				
				
				
			}else if(word.getNombreErreur()== 5) {
				int option = JOptionPane.showOptionDialog(null,"Dommage, vous avez perdu le mot était "+word.getMot()+".\n",
					    "Vous avez perdu", JOptionPane.YES_NO_CANCEL_OPTION,
					    JOptionPane.QUESTION_MESSAGE,
					    null,
					    options,
					    options[2]);
				
				if(option == JOptionPane.OK_OPTION) {
					try {
						add(new PenduPanel(joueur,width,height));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					revalidate();
					repaint();

					
				}else if(option == JOptionPane.NO_OPTION) {
					Jeu.fenetre.dispose();
					new Jeu("Jeu");

				}else if(option == JOptionPane.CANCEL_OPTION){
					System.exit(0);
				}

			}
			
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		
	}




}
