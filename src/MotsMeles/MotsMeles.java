package MotsMeles;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import projet.Jeu;
import projet.Joueur;

public class MotsMeles extends Jeu {
	private ArrayList<ArrayList<Character>> a = new ArrayList<ArrayList<Character>>(); //Liste contenant une liste de caractères (caractères de la grille)
	private ArrayList<String> listeMots = new ArrayList<String>(); //Liste contenant les mots à trouver
	private boolean[][] TabMotTrouve; //Tableau superposant la grille
	private boolean[] tabListeMot; //Tableau superposant la grille de mot
	Joueur joueur;
	int nbMotTrouve; //Nombre de mots trouvé
	
	//Gagné si tous les mots ont été trouvé
	public boolean gagner()
	{
		if(nbMotTrouve == getTailleListeMots())
		{
			return true;
		}
		return false;
	}
	
	//initialise les 2 tableaux a false
	public void MotTrouve()
	{
		for(int i=0;i<a.size();i++)
		{
			for(int j=0;j<a.get(0).size();j++)
			{
				TabMotTrouve[i][j] = false;
			}
		}
		for(int k = 0; k<getTailleListeMots(); k++)
		{
			tabListeMot[k] = false;
		}
	}
	
	
	public int getHauteurlisteMots() {
		return listeMots.size();
	}
	
	public int getHauteurGrille() {
		return a.size();
	}
	
	public int getLargeurGrille() {
		return a.get(0).size();
		}
	
	public int getTailleListeMots()
	{
		return listeMots.size();
	}
	
	public MotsMeles() throws IOException
	{
		grilleRandom();
		this.TabMotTrouve = new boolean[getHauteurGrille()][getLargeurGrille()];
		this.tabListeMot = new boolean[getTailleListeMots()];
		this.joueur= new Joueur("motsmeles","",0);
		MotTrouve();
	}
	
	//Permet de lire la grille (de caractères) et les mots depuis un fichier choisi aléatoirement parmi la liste
	public void grilleRandom()
	{
		String sh;
		String sh2;
		Random random = new Random();
		int choix = random.nextInt(5)+2;
		String grille ="src/file/GrilleMotsMeles/Grille"+choix;
		String listeMots = "src/file/GrilleMotsMeles/listeMotsGrille"+choix;
		     try{ //grille
 
			InputStream ips=new FileInputStream(grille); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
 
			
					while ((sh=br.readLine())!=null)
					{
					a.add(list(sh));
			}
			br.close();
		}catch(Exception e){e.printStackTrace();}
		     try{ //mots
		    	 
					InputStream ips2=new FileInputStream(listeMots); 
					InputStreamReader ipsr2=new InputStreamReader(ips2);
					BufferedReader br2=new BufferedReader(ipsr2);
		 
					
							while ((sh2=br2.readLine())!=null)
							{
							this.listeMots.add(sh2);
					}
					br2.close();
				}catch(Exception e2){e2.printStackTrace();}
}
	
	//Permet de ne lire que les caractères de A à Z
	public ArrayList<Character> list(String sh)
	{
		char[] ch = sh.toCharArray();
		ArrayList<Character> t = new ArrayList<Character>();
		for(char l : ch) {
			if(l >= 'A' && l <= 'Z') {
				t.add(l);
			}
		}
		return t;
	}

	public ArrayList<ArrayList<Character>> getA() {
		return a;
	}

	public ArrayList<String> getListeMots() {
		return listeMots;
	}


	public boolean[][] getTabMotTrouve() {
		return TabMotTrouve;
	}


	public boolean[] getTabListeMot() {
		return tabListeMot;
	}


	public Joueur getJoueur() {
		return joueur;
	}


	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
}
