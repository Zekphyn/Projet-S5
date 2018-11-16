package motus;

import java.io.IOException;
import java.text.Normalizer;
import java.util.Random;
import java.util.Scanner;

import projet.Dictionnaire;
import projet.Jeu;
import projet.Joueur;

public class Motus extends Jeu {
	private Dictionnaire dico;
	private String motRech;
	private Joueur joueur;
	private boolean[] trouve;
	private String[] jeu;
	private int[][] tabVerif;
	private int tailleMot;
	private int nbCoups;
	private int nbCoupsMax;
	
	public Dictionnaire getDico() {
		return dico;
	}

	public String getMotRech() {
		return motRech;
	}


	public Joueur getJoueur() {
		return joueur;
	}


	public boolean[] getTrouve() {
		return trouve;
	}


	public String[] getJeu() {
		return jeu;
	}


	public int[][] getTabVerif() {
		return tabVerif;
	}
	
	public int getTabVerif(int i, int j) {
		return tabVerif[i][j];
	}

	public int getTailleMot() {
		return tailleMot;
	}


	public int getNbCoups() {
		return nbCoups;
	}


	public int getNbCoupsMax() {
		return nbCoupsMax;
	}


	public Motus() throws IOException
	{
		this.dico = new Dictionnaire();
		this.joueur=joueur;
		this.nbCoups = 0;
		//if(nbCoupsMax<0) {
			//this.nbCoupsMax = 10;
		//}
		//else
			//this.nbCoupsMax=nbCoupsMax;
		this.nbCoupsMax=10;
		this.jeu = new String[nbCoupsMax];
		Random random = new Random();
		//Taille du mot aléatoire entre 7 et 10 lettres
		this.tailleMot = random.nextInt(4)+6;
		motRech = dico.motAlea(tailleMot).toUpperCase();
		this.trouve = new boolean[tailleMot];
		this.trouve[0]=true;
		for(int i=1;i<tailleMot;i++)
			trouve[i]=false;
		this.tabVerif = new int[nbCoupsMax][tailleMot];
	}
	
	private void affichage1() {
		for(int i=0; i<tailleMot; i++) {
			if(trouve[i])
				System.out.print(motRech.charAt(i));
			else
				System.out.print("_");
		}
	}
	
	private void affichage2() {
		for(int i=0;i<=nbCoups;i++)
		{
			System.out.println(jeu[i]);
		}
		System.out.println("\n");
		System.out.print("Lettre bien placée : ");
		for(int i=0; i<tailleMot;i++)
			if(tabVerif[nbCoups][i]==1)
				System.out.print(" " + i + "("+ jeu[nbCoups].charAt(i) + ")" );
		
		System.out.println("\n");
		System.out.print("Lettres mal placées : ");
		for(int i=0; i<tailleMot;i++)
			if(tabVerif[nbCoups][i]==2)
				System.out.print(" " + i + "("+ jeu[nbCoups].charAt(i) + ")" );
		System.out.println("\n");
	}
	
	public String saisieMot(Scanner sc) {
		String str = "";
		boolean motChar = false;
		while(!(str.length() == tailleMot) || !motChar) {
			System.out.println("saisir un mot de " + tailleMot + " lettres");
			if(sc.hasNextLine())
				str = sc.nextLine();
			str = str.toUpperCase();
			str = normaliseMotMaj(str);
			if(verifLettres(str))
				motChar=true;
			else
				System.out.println("Erreur : le mot saisis ne contient pas que des lettres");
			}
		return str;
	}
	
	public boolean verifLettres(String str) {
		str = str.toUpperCase();
		boolean ver = true;
		for(char c : str.toCharArray())
			if(c < 'A' || c>'Z')
				ver = false;
		return ver;
	}
	
	public String normaliseMotMaj(String mot) {
		String motf = "";
		mot = mot.toUpperCase();
		if(Normalizer.normalize(mot, Normalizer.Form.NFD).compareTo(mot) !=0 ) {
			mot = Normalizer.normalize(mot, Normalizer.Form.NFD);
			for(char c : mot.toCharArray()) {
				if(c >= 'A' && c <= 'Z' )
					motf = motf + Character.toString(c);
			}
		}
		else
			motf=mot;
		return motf;
	}
	
	
	public boolean partieGagnee(String mot)
	{
		if(mot.compareTo(motRech)== 0) {
			System.out.println("bravo vous avez gagné en " + nbCoups + " coups");
			return true;
		}
		return false;
	}
	
	//0 pour pas dans le mot, 1 dedans, 2 si mal placé
	
	public void verify(String mot) {
		boolean[] charUtilise = new boolean[tailleMot];
		for(int i = 0; i<tailleMot; i++)
		{
			if(mot.charAt(i) == motRech.charAt(i)) {
				tabVerif[nbCoups][i] = 1;
				charUtilise[i]=true;
			}
			else {
				tabVerif[nbCoups][i]=0;
				charUtilise[i]=false;
			}
		}
		for(int j=0; j<tailleMot;j++)
			if(tabVerif[nbCoups][j] != 1)
				for(int k = 0; k<tailleMot; k++)
					if((mot.charAt(j) == motRech.charAt(k)) && (charUtilise[k]==false))
					{
						tabVerif[nbCoups][j]=2;
						charUtilise[k]=true;
					}
	}
	
	public void majTrouve() {
		for(int i = 0; i<tailleMot; i++)
			if(tabVerif[nbCoups][i]==1)
				this.trouve[i]= true;
	}
	
	
	public boolean jouerCoup(Scanner sc)
	{
		String motSaisi;
		motSaisi = saisieMot(sc).toUpperCase();
		jeu[nbCoups]=motSaisi;
		if(this.dico.motExiste(motSaisi))
		{
			verify(motSaisi);
			majTrouve();
		}
		affichage2();
		this.nbCoups++;
		return partieGagnee(motSaisi);
	}
	
	public void jouerPartie()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("azerty");
		trouve[0]= true;
		boolean gagner = false;
		while(nbCoups != nbCoupsMax && !gagner) {
			affichage1();
			gagner = jouerCoup(sc);
		}
		sc.close();
	}
	
}
