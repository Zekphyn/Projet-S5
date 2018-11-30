package MotsMeles;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import projet.Dictionnaire;
import projet.Jeu;

public class MotsMeles extends Jeu {
	private int orientation;
	private boolean inversion;
	private int[] caseDebut = new int[2];
	private int[] caseFin = new int[2];
	private int nbLigne;
	private int nbColonne;
	private String grille[][];
	private int tailleMotMax;
	private Dictionnaire dico;
	private ArrayList<ArrayList<Character>> a = new ArrayList<ArrayList<Character>>();
	private ArrayList<String> listeMots = new ArrayList<String>();
	
	
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
	
	/*orientation = 0 = horizontale
	 * orientation = 1 = verticale
	 * orientation = 2 = diagonale*/
	
	public MotsMeles() throws IOException
	{
	//	int tailleMot;
	//	this.nbLigne = nbLigne;
	//	this.nbColonne = nbColonne;
	//	this.tailleMotMax=min(nbLigne,nbColonne);
	//	Random random = new Random();
	//	this.orientation = /*random.nextInt();*/ 1;	
	//	this.inversion = /*getRandomBoolean();*/false;
	//	this.caseDebut[0] = random.nextInt(nbLigne-1);
	//	this.caseFin[0] = random.nextInt(nbColonne-1);
	//	tailleMot = random.nextInt(tailleMotMax-2)+3;
	//	this.grille = new String[nbLigne][nbColonne]; 
		for(int i=0; i<nbLigne; i++)
		{
	//		System.out.println("\n");
			for(int j=0; j<nbColonne;j++)
			{
	//			grille[i][j] = '.';
				//System.out.print((grille[i][j]));
			}
		}
	//	if(orientation == 0)
		//{
			//this.caseFin[1] = this.caseDebut[1] + random.nextInt((nbLigne-1)-this.caseDebut[1]);
			//this.caseFin[0] = this.caseDebut[0];
			//this.caseFin[1] = this.caseDebut[1];
		//}
		//else if(orientation == 1)
		//{
			//this.caseFin[1] = this.caseDebut[1];
			//this.caseFin[0] = this.caseDebut[0] + random.nextInt((nbColonne-1)-this.caseDebut[0]);
		//}
		grilleRandom();
	}
	public int min(int a, int b)
	{
		if(a<b)
			return a;
		else return b;
	}
	
	public void ajoutMot() throws IOException
	{
		int i = 0;
		int j = 0;
		int tailleMot;
		String mot;
		this.dico = new Dictionnaire();
		Random random = new Random();
		tailleMot = random.nextInt(tailleMotMax-2)+3;
		mot = dico.motAlea(tailleMot).toUpperCase();
		System.out.println(mot.length());
		System.out.println(caseDebut[0]);
		System.out.println(caseFin[0]+mot.length());
		//System.out.println(nbColonne-1);
		System.out.println(nbLigne-1);
		System.out.println(mot);
		grilleRandom();
		while(i<mot.length() && j<mot.length())
		{
				if(orientation == 0)
				{
					if(caseFin[0]+mot.length()<=nbColonne)
					{
	//					if(grille[caseDebut[0]][caseFin[0]+i] == '.')
						{
	//					grille[caseDebut[0]][caseFin[0]+i] = mot.charAt(i);
						i++;
						}
					}
					else
					{
					caseFin[0]--;
					}
				}
				else if(orientation == 1)
				{
					if(caseFin[0]+mot.length()<=nbLigne)
					{
		//				if(grille[caseDebut[0]+i][caseFin[0]] == '.')
						{
			//			grille[caseDebut[0]+i][caseFin[0]] = mot.charAt(i);
						i++;
						}
					}
					else
					{
						caseFin[0]--;
						if(caseDebut[0]>0)
						caseDebut[0]--;
					}
				}
				else
				{
					if(caseFin[1]+mot.length()<=nbColonne || caseDebut[0]+mot.length()<=nbLigne)
					{
				//	grille[caseDebut[0]+i][caseFin[1]+j] = mot.charAt(i);
					i++;
					j++;
					}
					else
					{
						if(caseFin[0]+mot.length()>nbColonne)
						{
							caseFin[0]--;
							if(caseFin[1]+mot.length()>nbLigne)
							{
								caseFin[1]--;
							}
						}
					}
				}
		}
	}
	
	public void affichage(int nbLigne, int nbColonne)
	{
		for(int i=0;i<nbLigne;i++)
		{
			System.out.println("\n");
			for(int j=0;j<nbColonne;j++)
			{
				System.out.print(grille[i][j]);
			}
		}
	}
	
	public boolean getRandomBoolean() {
	    Random random = new Random();
	    return random.nextBoolean();
	}
	public void normalizer()
	{
		
	}
	public void grilleRandom()
	{
		String sh;
		String sh2;
		String grille ="src/file/GrilleMotsMeles/Grille2";
		String listeMots = "src/file/GrilleMotsMeles/listeMotsGrille2";
		int c=0;
		int s=0;
		String j;
		     try{
 
			InputStream ips=new FileInputStream(grille); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			LineNumberReader reader = new LineNumberReader(br);
 
			
					while ((sh=br.readLine())!=null)
					{
					a.add(list(sh));
					c++;
			}
			br.close();
		}catch(Exception e){e.printStackTrace();}
		     try{
		    	 
					InputStream ips2=new FileInputStream(listeMots); 
					InputStreamReader ipsr2=new InputStreamReader(ips2);
					BufferedReader br2=new BufferedReader(ipsr2);
					LineNumberReader reader2 = new LineNumberReader(br2);
		 
					
							while ((sh2=br2.readLine())!=null)
							{
							this.listeMots.add(sh2);
							s++;
					}
					br2.close();
				}catch(Exception e2){e2.printStackTrace();}
		  //   System.out.print(this.listeMots);
				
				
}
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
	

	public void jouer()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrez les coordonnées de départ :\n");
		int x = sc.nextInt();
		int y = sc.nextInt();
		System.out.println("Entrez les coordonnées d'arrivée :\n");
		int xx = sc.nextInt();
		int yy = sc.nextInt();
	}

	public ArrayList<ArrayList<Character>> getA() {
		return a;
	}

	public ArrayList<String> getListeMots() {
		return listeMots;
	}
}
