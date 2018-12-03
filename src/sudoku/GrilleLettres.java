package sudoku;




import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import projet.Classement;
import projet.Jeu;
import projet.Joueur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
public class GrilleLettres extends JFrame implements ActionListener {
	JeuLettres jeu;
	Joueur joueur = new Joueur("Sudoku chiffre","",0);
	boolean isSudokuChiffre = true;
	// Fichier courant
	File fichierCourant;
	Container c;
	JPanel panel  , panelGeneral;
	JPanel[][] jp = new JPanel[3][3];
	
	// Boutons représentants les cases 
	JButton cases[][]=new JButton[9][9];
	GridLayout grille;
	JMenu menuFichier;
	JMenuBar menu;
	JMenuItem fermer,ouvrir,resoudre;
	
	// Constructeur par defaut qui cree la grille et la fenetre en placant tous les elements et l'affiche
	public GrilleLettres()
	{
		// On cree la fenetre : Utilisé pendant les tests du jeu 
		//super("Sudoku");
		
		// On cree le jeu
		jeu=new JeuLettres();
		
		// On definit la fin du programme a la fermeture de la fenetre
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// La taille de la fenetre
		setSize(650,650);
		
		// On ajoute la barre des menus
		menu = new JMenuBar();
		menuFichier= new JMenu("Fichier");
		ouvrir=new JMenuItem("Ouvrir");
		fermer=new JMenuItem("Fermer");
		resoudre= new JMenuItem("Resoudre ce sudoku");
		
		
		// On ajoute les elements au menu
		
		menuFichier.add(ouvrir);
		menuFichier.addSeparator();
		menuFichier.add(resoudre);
		menuFichier.addSeparator();
		menuFichier.add(fermer);
		
		menu.add(menuFichier);
		
		// On ajoute les ListenerS aux elements du menu
		ouvrir.addActionListener(this);
		fermer.addActionListener(this);
		resoudre.addActionListener(this);
		setJMenuBar(menu);
		
		if(joueur.getNom() == "") {
			String nom = JOptionPane.showInputDialog(null, "Bonjour ! Quelle est votre Pseudo ?");
			joueur.setNom(nom);
			if(nom.equals("")) joueur.setNom("joueur");
		}
	
		Object stringArray[] = { "Chiffre", "Lettre" };
	    int choixSudoku = JOptionPane.showOptionDialog(null, "Quel type de sudoku", "Choisissez",
	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray,
	        stringArray[0]);
	    if (choixSudoku == JOptionPane.NO_OPTION) {
	        isSudokuChiffre = false;
	      }

		// On cree un conteneur et un panel avec une GridLayout puis on ajoute les boutons
		c = getContentPane();
		grille= new GridLayout(3,3);
		panel= new JPanel();
		panel.setLayout(grille);
		panelGeneral=new JPanel();
		panelGeneral.setLayout(new BorderLayout());
		panelGeneral.add(panel,BorderLayout.CENTER);
		
		// On cree les panels qui representent les 9 regions de 3x3 cases
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
			{
				jp[i][j]= new JPanel();
				(jp[i][j]).setLayout(grille);
				(jp[i][j]).setBorder(BorderFactory.createEtchedBorder());
			}
		
		// On cree les boutons et on les ajoute aux panels
		
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				Integer in = new Integer((jeu.getCaseChar(i, j)));
				
				if (in==0)
					cases[i][j]=new JButton("");
				else 
					cases[i][j]=new JButton(in.toString());
				
				if(jeu.getCaseFixe(i, j))
					(cases[i][j]).setFont(new java.awt.Font("Helvetic",java.awt.Font.BOLD,25));
				else(cases[i][j]).setFont(new java.awt.Font("Helvetica",java.awt.Font.PLAIN,25));
				
				(cases[i][j]).setSize(10,10);
				
				(jp[(int)(i/3)][(int)(j/3)]).add(cases[i][j]);
				
				panel.add(jp[(int)(i/3)][(int)(j/3)]);
				
				// On ajoute un Listener au bouton i,j
				(cases[i][j]).addActionListener(this);
				
				
			}
		}
		c.add(panelGeneral);
		// on affiche la fenetre : Utilisé pendant les tests unitaires
		// show();
		setGrilleDefault();
	}
	
	public void setGrilleDefault() {
		if(isSudokuChiffre) {
			this.fichierCourant = new File(""+Paths.get("src/sudoku/g.txt"));
		}else {
			this.fichierCourant = new File(""+Paths.get("src/sudoku/gl.txt"));
		}
		JeuLettres jeuTmp = new JeuLettres(fichierCourant);
		jeu = jeuTmp;
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				char in = ((jeu.getCaseChar(i, j)));
				if (in=='0')
					(cases[i][j]).setText("");
				else
					(cases[i][j]).setText(Character.toString(in));
				
				this.boutonFixe(i, j);
			}
		}
	}
	// Afficher les cases fixe en gras
	public void boutonFixe(int i , int j)
	{
		if(jeu.getCaseFixe(i, j))
			(cases[i][j]).setFont(new java.awt.Font("Helvetica",java.awt.Font.BOLD,25));
	}
	
	// Colore ( ou decolore ) les colonnes , les lignes et les regions apres verification
	public void verif(int i,int j)
	{
		this.boutonFixe(i, j);
		this.coloreRegion(i,j);
		
		if(jeu.ligneComplete(i))
			coloreLigne(i);
		else decoloreLigne(i);
		
		if(jeu.colonneComplete(j))
			coloreColonne(j);
		else decoloreColonne(j);
		
		if(jeu.gagne()) {
			Classement classement = new Classement();
			classement.setScoreCSV(joueur);
			JOptionPane.showMessageDialog(this, " Vous avez gagné , quel champion ! Votre score : " + joueur.getScore(), "Félicitations", JOptionPane.PLAIN_MESSAGE);
			Jeu.fenetre.dispose();
			new Jeu("Jeu");
		}
	}
	
	
	// Colore la region si elle contient tous les chiffres de 1 a 9
	public void coloreRegion(int i,int j)
	{
		// On verifie si la region de la case ,sur laquelle on a cliqué , est valide , si oui on la colorie
		if (jeu.getRegionDeCase(i, j).regionComplete())
			(jp[(int)(i/3)][(int)(j/3)]).setBorder(BorderFactory.createLineBorder(Color.RED));
		else
			(jp[(int)(i/3)][(int)(j/3)]).setBorder(BorderFactory.createEtchedBorder());
		
	}
	
		
	// Colore la ligne si elle contient tout les chiffres de 1 a 9
	public void coloreLigne(int i)
	{
		for(int j=0;j<9;j++)
		{
			if(cases[i][j].getForeground()==Color.GREEN)
			{
				(cases[i][j]).setForeground(Color.RED);
				this.boutonFixe(i, j);
			}	
			
			else
			{
				(cases[i][j]).setForeground(Color.BLUE);
				(cases[i][j]).setFont(new java.awt.Font("Helvetica", java.awt.Font.PLAIN ,25));
				this.boutonFixe(i, j);
			}
		}
	
				
	}
	
	// Decolore la ligne si elle ne contient plus les chiffres de 1 a 9
	public void decoloreLigne(int i)
	{
		for(int j=0;j<9;j++)
		{
			if( (cases[i][j].getForeground()==Color.RED) || ( cases[i][j].getForeground()== Color.GREEN ))
			{
				(cases[i][j]).setForeground(Color.GREEN);
				this.boutonFixe(i, j);
			}	
			
			else
			{
				(cases[i][j]).setForeground(Color.BLACK);
				(cases[i][j]).setFont(new java.awt.Font("Helvetica", java.awt.Font.PLAIN ,25));
				this.boutonFixe(i, j);
			}
		}
	}
	
	// Colore la colonne si elle contient tous les chiffres de 1 a 9 
	public void coloreColonne(int j)
	{
		for(int i=0;i<9;i++)
		{
			if(cases[i][j].getForeground()==Color.BLUE)
			{
				(cases[i][j]).setForeground(Color.RED);
				this.boutonFixe(i, j);
			}	
			
			else
			{
				(cases[i][j]).setForeground(Color.GREEN);
				(cases[i][j]).setFont(new java.awt.Font("Helvetica", java.awt.Font.PLAIN ,25));
				this.boutonFixe(i, j);
			}
		}
	}
	
	// Decolore la colonne si elle ne contient plus les chiffres de 1 a 9
	public void decoloreColonne(int j)
	{
		for(int i=0;i<9;i++)
		{
			if((cases[i][j].getForeground()==Color.RED) || (cases[i][j].getForeground()==Color.BLUE) )
			{
				(cases[i][j]).setForeground(Color.BLUE);
				this.boutonFixe(i, j);
			}	
			
			else
			{
				(cases[i][j]).setForeground(Color.BLACK);
				(cases[i][j]).setFont(new java.awt.Font("Helvetica", java.awt.Font.PLAIN ,25));
				this.boutonFixe(i, j);
			}
		}
	}

	// Incremente le chiffre du bouton quand on appuie dessus
	public void appuieBouton(int i,int j)
	{
		if(!jeu.getCaseFixe(i, j))
		{
			if(jeu.caracteresAutorises.indexOf(jeu.getCaseChar(i,j))<8)
				jeu.setCaseChar(i, j,jeu.caracteresAutorises.get(jeu.caracteresAutorises.indexOf(jeu.getCaseChar(i, j))+1));
			else
				jeu.setCaseChar(i, j,jeu.caracteresAutorises.get(0) );
			
			char in = (jeu.getCaseChar(i,j));
			
			if (in == '0')
				(cases[i][j]).setText("");
			else (cases[i][j]).setText(Character.toString(in));
		}
	}
	
	
	// Ouvrir un fichier enregistre sur l'ordi
	public void ouvrirFichier()
	{
		String nomFic = new String("");
		JFileChooser choix = new JFileChooser();
		choix.setDialogTitle("Choisir le fichier");
		choix.setApproveButtonText("Valider");
		
		int returnVal=choix.showOpenDialog(this);
		
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			File fichier = choix.getSelectedFile();
			JeuLettres jeuTmp = new JeuLettres(fichier);
			jeu = jeuTmp;
			
			for(int i=0;i<9;i++)
			{
				for(int j=0;j<9;j++)
				{
					char in = ((jeu.getCaseChar(i, j)));
					if (in=='0')
						(cases[i][j]).setText("");
					else
						(cases[i][j]).setText(Character.toString(in));
					
					this.boutonFixe(i, j);
				}
			}
			fichierCourant=choix.getSelectedFile();
		}
		
	}
	
	public void resoudre()
	{
		if(jeu.resoudre(0, 0))
		{
			for(int i=0;i<9;i++)
			{
				for(int j=0;j<9;j++)
				{
					Character in=new Character (jeu.getCaseChar(i, j));
					cases[i][j].setText(in.toString());
					this.boutonFixe(i, j);
					
				}
			}
			
		}
		else {
			JOptionPane.showMessageDialog(this, "Impossible de resoudre cette grille avec ce solveur simple ", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	// definitions des sources
	
	
	public void actionPerformed(ActionEvent e)
	{
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
			{
				if (e.getSource()==cases[i][j])
				{
					this.appuieBouton(i, j);
					this.verif(i, j);
					
				}
			}
		
		// On définit les actions des sous menu
		if(e.getSource()==ouvrir)
			this.ouvrirFichier();
		else if(e.getSource()==resoudre)
			this.resoudre();
		else if(e.getSource()==fermer)
			System.exit(0);
		
	}
	
	public JPanel getPanel() {
		return this.panelGeneral;
	}
	
	public JMenuBar getMenuSudoku() {
		return menu;
	}
		
		
}
	