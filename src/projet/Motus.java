package projet;

import java.text.Normalizer;
import java.util.Random;
import java.util.Scanner;

public class Motus extends Jeu {
	private Dictionnaire dico;
	private String motRech;
	private Joueur joueur;
	private boolean trouve[];
	private char[][] jeu;
	private int tailleMot;
	private int nbCoups;

	public Motus(Dictionnaire dico, Joueur joueur)
	{
		this.dico = dico;
		this.joueur=joueur;
		this.nbCoups = 10;
		Random random = new Random();
		//Taille du mot aléatoire entre 7 et 10 lettres
		this.tailleMot = random.nextInt(3)+7;
		motRech = dico.motAlea(tailleMot).toUpperCase();
	//	this.trouve = new 
	}
	
	//initialise une grille pouvant contenir 10 mots de tailleMot lettres
	
	private void init() {
		this.jeu = new char[10][tailleMot];
		jeu[0][0] = motRech.charAt(0);
	}
	
	public String saisieMot() {
		String str = "";
		String strf ="";
		while(str.length() != tailleMot) {
			Scanner sc = new Scanner(System.in);
			System.out.println("saisir un mot de " + tailleMot + " lettres");
			str = sc.nextLine();
			sc.close();
			}
		str.toUpperCase();
		return normaliseMotMaj(str);
	}
	
	public String normaliseMotMaj(String mot) {
		String motf = "";
		mot.toUpperCase();
		if(Normalizer.normalize(mot, Normalizer.Form.NFD).compareTo(mot) !=0 ) {
			mot = Normalizer.normalize(mot, Normalizer.Form.NFD);
			for(char c : mot.toCharArray())
				if(c >= 'A' || c <= 'Z' )
					motf.concat("" + c);
		}
		else
			motf=mot;
		return motf;
	}
	
	
	public boolean partieGagnee(String mot)
	{
		if(mot.compareTo(motRech)== 0)
			return true;
		return false;
	}
	
	//0 pour pas dans le mot, 1 dedans, 2 si mal placé
	public int[] verify(String mot) {
		int[] charVerif = new int[tailleMot];
		boolean[] charUtilise = new boolean[tailleMot];
		for(int i = 0; i<tailleMot; i++)
		{
			if(mot.charAt(i) == motRech.charAt(i)) {
				charVerif[i] = 1;
				charUtilise[i]=true;
			}
			else {
				charVerif[i]=0;
				charUtilise[i]=false;
			}
		}
		
		for(int j=0; j<mot.length();j++)
			if(charVerif[j] != 1)
				for(int k = 0; k<mot.length(); k++)
					if((mot.charAt(j) == motRech.charAt(k)) && (charUtilise[k]==false))
					{
						charVerif[j]=2;
						charUtilise[k]=true;
					}
		return charVerif;
	}
	
	//public boolean[] majCarTrouve(int[] tabVerif, boolean []) {
	
	
	
	public int jouer()
	{
		int [] tabVerif = new  int[motRech.length()];
		boolean [] trouve = new boolean[motRech.length()];
		trouve[0]= true;
		String motSaisi;
		boolean gagner = false;
		while(nbCoups != 0 || !gagner) {
			motSaisi = saisieMot().toUpperCase();
			if(motSaisi.length() > motRech.length())
				motSaisi.subSequence(0, motRech.length()-1);
			jeu[10-nbCoups] = motSaisi.toCharArray();
			tabVerif = verify(jeu[10-nbCoups].toString());
			//for(i)
			gagner = partieGagnee(motSaisi);
		}
		return nbCoups;
	}
}
