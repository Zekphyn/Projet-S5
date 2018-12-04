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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import pendu.PenduPanel.BoutonListener;
import projet.Classement;
import projet.Jeu;
import projet.Joueur;

public class PenduGridBag extends JPanel{
	
	private static final long serialVersionUID = 1L;
	char[] lettres = {'a','z','e','r','t','y','u','i','o','p','q','s','d','f','g','h','j','k','l','m','w','x','c','v','b','n'};
	public Joueur joueur;
	private JButton bouton[];
	private JLabel mot, erreur;
	private ImageLabel image;
	int width ,height;
	Mot word;

	
	public PenduGridBag(int width, int height) throws IOException {
		joueur = new Joueur("Pendu","",0);
		word = new Mot();
		this.width = width;
		this.height = height;
		init();
	}
	
	public PenduGridBag(Joueur j,int width,int height) throws IOException {
		joueur = j;
		word = new Mot();
		this.width = width;
		this.height = height;
		init();
	}
	
	private void init() {
		System.out.println(width+"   "+height);
		this.setPreferredSize(new Dimension(width,height));
		/*if(joueur.getNom() == "") {
			String nom = JOptionPane.showInputDialog(null, "Bonjour ! Quelle est votre Pseudo ?");
			joueur.setNom(nom);
			if(nom.equals("")) joueur.setNom("joueur");
		}*/
		setSize(width,height);
		GridBagLayout gb = new GridBagLayout();
		setLayout(gb);
		setBackground(Color.BLUE);
		Dimension dim = new Dimension(width,height/5);
		
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
		
		
		//LEFTCONTEND
		JPanel left = new JPanel(new BorderLayout());
		left.setBackground(Color.YELLOW);
		dim =new Dimension(width/2,2*height/3);
		left.setPreferredSize(dim);
		left.setMinimumSize(dim);
		
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
		for(char cha : lettres){
			this.bouton[i] = new JButton(String.valueOf(cha).toUpperCase());
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
		dim =new Dimension(width/2,2*height/3);
		JPanel right = new JPanel(new BorderLayout());
		this.image = new ImageLabel(); 
		this.image.setSize(dim);
		this.image.setVerticalAlignment(JLabel.CENTER);
		right.setMinimumSize(dim);
		right.add(image,BorderLayout.CENTER);
		right.setBackground(Color.WHITE);
		right.setPreferredSize(dim);
		
		
		
		//BOTTOM
		JPanel bottom = new JPanel(new BorderLayout());
		dim = new Dimension(width,height/4);
		bottom.setSize(dim);
		JLabel regles = new JLabel("LES REGLES SONT LES SUIVANTES :");
		regles.setHorizontalAlignment(JLabel.CENTER);
		bottom.add(regles,BorderLayout.NORTH);
		JLabel regles2 = new JLabel("Vous devez trouver le mot secret en réalisant le moins de coup possible et en faisant le moins d'erreur possibles.\n"
				+"\n ATTENTION : si vous faites trop d'erreurs vous êtes pendu !");
		regles2.setHorizontalAlignment(JLabel.CENTER);
		bottom.add(regles2, BorderLayout.CENTER);
			
		
		
			
	    //L'objet servant à positionner les composants
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.anchor = GridBagConstraints.NORTH;
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.weightx = 1;
	    gbc.weighty = 1;
	    add(head, gbc,0,0,width,height/4);
	    gbc.anchor = GridBagConstraints.SOUTH;
	    add(bottom, gbc,0,height/4+height/2,width,height/5);
	    
	    //---------------------------------------------
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.weightx = 1;
	    gbc.weighty = 1;		
	    gbc.anchor = GridBagConstraints.WEST;

	    add(left, gbc,0,height/4,width/2,height/2);	
	    
	    //---------------------------------------------
		gbc.anchor = GridBagConstraints.EAST;
	    add(right, gbc,width/2,height/4,width/2,height/2);	
	    //---------------------------------------------
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
					//Classement.setScore(joueur);
					removeAll();
						try {
							PenduPanel test = new PenduPanel(joueur,width,height);
							add(test);
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
					removeAll();
					try {
						PenduPanel test = new PenduPanel(joueur,width,height);
						add(test);					
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
}
