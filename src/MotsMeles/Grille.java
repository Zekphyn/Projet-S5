package MotsMeles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import projet.Classement;
import projet.Jeu;

public class Grille extends JPanel implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int longueur; //Longueur de la grille
	public int largeur;	//Largeur de la grille
	private Color couleurLignes ; //couleur des lignes de la grille
	public int largeurCaseGraphique; //Largeur des cases de la grille
	MotsMeles motsmeles;
	public int mouseX;	//coordonnées d'1 clique sur une case de la grille en abscisse
	public int mouseY;  //coordonnées d'1 clique sur une case de la grille en ordonnée
	public int[] caseDebut = new int[3]; //case contenant la 1ere lettre d'un mot (caseDebut[0] = coordonnées en X de la grille, [1] = en Y, [2] = 0 ou 1
	public int[] caseFin = new int[3]; //case contenant la derniere lettre d'un mot (idem)
	public int orientation; //oritentation du mot
	public int inverse; //inversion du mot
	public String mot;
	public GrilleMots grilleMots;
	public int nbMotTrouve;
	
	public Grille(MotsMeles motsmeles, GrilleMots gm) {
		super();
		this.motsmeles=motsmeles;
		this.grilleMots = gm;
		this.longueur=motsmeles.getHauteurGrille();
		this.largeur=motsmeles.getLargeurGrille();
		couleurLignes = Color.BLACK;	
		this.caseDebut[2] = 0;
		this.caseFin[2] = 0;
		this.nbMotTrouve = 0;
		initGrille();
		addMouseListener(this);
	}
	
	//Initialise la grille et le visuel
	public void initGrille()
	{
		int i;
		int j;
		setLayout(new GridLayout(longueur,largeur));
		for(i=0;i< longueur;i++)
		{
			for(j=0;j< largeur;j++)
			{
				ajouter(motsmeles.getA().get(i).get(j), i, j);
			}
		}
	}
	
	//Création du visuel de la grille
	public void ajouter(char c, int i, int j) {
		Border blackline = BorderFactory.createLineBorder(couleurLignes,1); 
		JLabel text = new JLabel(Character.toString(c) ,JLabel.CENTER);
		text.setMinimumSize(new Dimension(75,75));
		text.setPreferredSize(new Dimension(100,100));
		text.setFont(new Font("Serif", Font.PLAIN, 36));
		text.setBorder(blackline);
		if(motsmeles.getTabMotTrouve()[i][j] == true) { //Si un mot est trouvé on met le fond de sa case en rouge 
			text.setBackground(Color.RED);
			text.setOpaque(true);
		}

		add(text);
	}

	//Vérification de l'orientation et de l'inversion du mot (orientation = 0 -> ligne, = 1 -> colonne, = 2 -> diagonale1; = 3 -> diagonale2)
	//par rapport a la case de début et de fin				 (inversion = 0 -> ex : LECOMTE
	//														  inversion = 1 -> ex : ETMOCEL)
	public boolean verif()
	{
		boolean verif = false;
		if(caseDebut[0] == caseFin[0])
		{
			System.out.println("ligne");
			verif = true;
			orientation = 0;	
			if(caseDebut[1]<caseFin[1]) {
				inverse = 0;
			}
			else
				inverse = 1;
		}
		else if(caseDebut[1] == caseFin[1])
		{
			verif = true;
			orientation = 1;
			if(caseDebut[0] < caseFin[0])
			{
				inverse = 0;
			}
			else inverse = 1;
		}
		else if((caseDebut[0]-caseFin[0]) == (caseDebut[1]-caseFin[1]))
		{
			verif = true;
			orientation = 2;
			if(caseDebut[0] < caseFin[0])
			{
				inverse = 0;
			}
			else inverse = 1;
		}
		else if((caseDebut[0]-caseFin[0]) == -(caseDebut[1]-caseFin[1]))
		{
			verif = true;
			orientation = 3;
			if(caseDebut[0] < caseFin[0])
			{
				inverse = 0;
			}
			else inverse = 1;
		}
		if(verif == false)
		{
			caseDebut[2] = 0;
			caseFin[2] = 0;
		}
		 return verif;
	}
	
	//Initialise les cases du tableau booléen superposant la grille a true selon l'orientation et l'inversion
	// true = le coup joué (caseDebut et caseFin) est correct, un mot peut être trouvé (en ligne, colonne, ou diagonales)
	public void parcours2()
	{
		//VerifLigne
				if(orientation == 0)
				{
					if(inverse == 0)
					{
						for(int j = caseDebut[1];j<=caseFin[1];j++)
						{
							motsmeles.getTabMotTrouve()[caseFin[0]][j] = true;
						}
					}
					else
					{
						for(int j = caseDebut[1];j>=caseFin[1];j--)
						{
							motsmeles.getTabMotTrouve()[caseFin[0]][j] = true;
						}
					}
				}
				//VerifColonne
				if(orientation == 1)
				{
					if(inverse == 0)
					{
						for(int i = caseDebut[0];i<=caseFin[0];i++)
						{
							motsmeles.getTabMotTrouve()[i][caseDebut[1]] = true;
						}
					}
					else
					{
						for(int i = caseDebut[0];i>=caseFin[0];i--)
						{
							motsmeles.getTabMotTrouve()[i][caseDebut[1]] = true;
						}
					}
				}
				//VerifDiag1
				if(orientation == 2)
				{
					if(inverse == 0)
					{
						for(int  i = 0;i <= caseFin[0]-caseDebut[0]; i++)
						{
							motsmeles.getTabMotTrouve()[caseDebut[0]+i][caseDebut[1]+i] = true;
						}
					}
					else
					{
						for(int  i = 0;i <= caseDebut[0]-caseFin[0]; i++)
						{
							motsmeles.getTabMotTrouve()[caseDebut[0]-i][caseDebut[1]-i] = true;
						}
					}
				}
				//verifDiag2
				if(orientation == 3)
				{
					if(inverse == 0)
					{
						for(int j = 0;j <= caseDebut[1]-caseFin[1]; j++)
						{
							motsmeles.getTabMotTrouve()[caseDebut[0]+j][caseDebut[1]-j] = true;
						}
					}
					else
					{
						for(int i = 0;i <= caseFin[1]-caseDebut[1]; i++)
						{
							motsmeles.getTabMotTrouve()[caseDebut[0]-i][caseDebut[1]+i] = true;
						}
					}
				}
	}
	
	//Trouve le mot obtenu après avoir jouer un coup et vérifie s'il se trouve dans la liste de mots
	public void parcours()
	{
		mot = "";
		//VerifLigne
		if(orientation == 0)
		{
			if(inverse == 0)
			{
				for(int j = caseDebut[1];j<=caseFin[1];j++)
				{
					mot = mot + Character.toString(motsmeles.getA().get(caseFin[0]).get(j));
				}
			}
			else
			{
				for(int j = caseDebut[1];j>=caseFin[1];j--)
				{
					mot = mot + Character.toString(motsmeles.getA().get(caseDebut[0]).get(j));
				}
			}
		}
		//VerifColonne
		if(orientation == 1)
		{
			if(inverse == 0)
			{
				for(int i = caseDebut[0];i<=caseFin[0];i++)
				{
					mot = mot + Character.toString(motsmeles.getA().get(i).get(caseDebut[1]));
				}
			}
			else
			{
				for(int i = caseDebut[0];i>=caseFin[0];i--)
				{
					mot = mot + Character.toString(motsmeles.getA().get(i).get(caseDebut[1]));
				}
			}
		}
		//VerifDiag1 (de bas gauche à haut droite)
		if(orientation == 2)
		{
			if(inverse == 0)
			{
				for(int  i = 0;i <= caseFin[0]-caseDebut[0]; i++)
				{
					mot = mot + Character.toString(motsmeles.getA().get(caseDebut[0]+i).get(caseDebut[1]+i));
				}
			}
			else
			{
				for(int  i = 0;i <= caseDebut[0]-caseFin[0]; i++)
				{
					mot = mot + Character.toString(motsmeles.getA().get(caseDebut[0]-i).get(caseDebut[1]-i));
				}
			}
		}
		//verifDiag2 (de bas droite à haut gauche)
		if(orientation == 3)
		{
			if(inverse == 0)
			{
				for(int j = 0;j <= caseDebut[1]-caseFin[1]; j++)
				{
					mot = mot + Character.toString(motsmeles.getA().get(caseDebut[0]+j).get(caseDebut[1]-j));
				}
			}
			else
			{
				for(int i = 0;i <= caseFin[1]-caseDebut[1]; i++)
				{
					mot = mot + Character.toString(motsmeles.getA().get(caseDebut[0]-i).get(caseDebut[1]+i));
				}
			}
		}
		//verifMotDansListeMots
		for(int i=0; i<motsmeles.getTailleListeMots(); i++)
		{
			if(mot.compareTo(motsmeles.getListeMots().get(i)) == 0)
			{
			//	nbMotTrouve++;
				parcours2();
				motsmeles.getTabListeMot()[i] = true;
			}
		}
	}
	
	//Gère le clic :
	//Permet de jouer un coup (2 cliques), gère après la victoire (rejouer, menu, quitter)
	@Override
	public void mouseClicked(MouseEvent e) {
			int gridx=0;
			int gridy=0;
			Object[] options = {"Rejouer", "Menu","Quitter"};
	    	int option=0;
	    	//Parcours la grille
			for(int i=0; i<longueur; i++)
			{
				gridy=0;
				for(int j=0; j<largeur; j++)
				{
					//les cases de la grille étant sur 100 pixels il faut diviser par 100 afin de pouvoir cliquer sur
					// une case et d'obtenir toujours la même valeur (ça revient à le rendre sur 1 pixel sans modifier
					// l'affichage)
					if((e.getX() >= gridx && e.getY() >= gridy) && (e.getX() < (gridx+100) && e.getY() < (gridy+100)))
							{
								mouseY= gridx/100;
								mouseX = gridy/100;
							}
					gridy+=100;
				}
				gridx +=100;
			}
					if(caseDebut[2] == 0) //Si pas de coup joué
					{
						caseDebut[0]= mouseX;
						caseDebut[1]= mouseY;
						caseDebut[2]=1;
					}
						else if(caseFin[2] == 0)
						{
							caseFin[0]= mouseX;
							caseFin[1]= mouseY;
							caseFin[2]=1;
						}
					if(caseDebut[2] == 1 && caseFin[2] == 1) //Coup joué
					{
						if(verif()) //Cliques correct
						{
							parcours(); //Mot trouvé ?
							caseDebut[2]=0; 	//On remet à 0 pour rejouer un coup
							caseFin[2]=0;
							this.removeAll();   //Supprime la grille
							initGrille();		//Re-initialise
							this.revalidate();	//Raffraichit l'affichage
							
							// va changer la couleur de la grille de mots � droite par rapport au mot trouv�
							grilleMots.majGrille(motsmeles.getListeMots().indexOf(mot));
							//Si gagné, on retire la grille et la grille de mots, et on affiche un JLabel avec 3 choix, rejouer, retour au menu, quitter
							if(motsmeles.gagner()) {
				    			this.revalidate();
				    			this.removeAll();
					    		grilleMots.removeAll();
						    	Classement classement = new Classement();		//Gère le score
						    	classement.setScoreCSV(motsmeles.getJoueur());
								option = JOptionPane.showOptionDialog(null,"Bravo !! Vous avez terminé la grille", null, JOptionPane.YES_NO_CANCEL_OPTION,
									    JOptionPane.QUESTION_MESSAGE,
									    null,
									    options,
									    options[2]);
								if(option == JOptionPane.OK_OPTION) { //Rejouer
									removeAll();
									add(new JPanelMotsMeles(motsmeles.getJoueur(), 500, 500)); //Relance une partie
									revalidate();
									repaint();
									
							}else if(option == JOptionPane.NO_OPTION) { //Menu
								revalidate();
								Jeu.fenetre.dispose();
								new Jeu("Jeu");
								repaint();
								}else if(option == JOptionPane.CANCEL_OPTION){ //Quitter
									System.exit(0); 
								}
					    	}
							
				    		}
						}
					}
	

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public int getNbMotTrouve() {
		return nbMotTrouve;
	}
}
