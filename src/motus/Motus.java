package motus;

import java.io.IOException;
//import java.text.Normalizer;
import java.util.Random;


import projet.Dictionnaire;
import projet.Jeu;
import projet.Joueur;

public class Motus extends Jeu {
	private Dictionnaire dico;
	private String motRech;
	Joueur joueur;
	private boolean[] trouve;
	private String[] jeu;
	private int[][] tabVerif;
	private int tailleMot;
	private int nbCoups;
	private int nbCoupsMax;
	private boolean gagne;
	
	public boolean getGagne() {
		return gagne;
	}
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
	
	public void setJoueur(Joueur joueur) {
		this.joueur=joueur;
	}

	public Motus() throws IOException
	{
		
		this.dico = new Dictionnaire();
		//initialise le nombre de coup jou� � 0, le nombre de coups maximum avant de perdre � 6
		this.nbCoups = 0;
		this.nbCoupsMax=6;
		//tableau en fonction du nombre de coups max
		this.jeu = new String[nbCoupsMax];
		
		//Taille du mot al�atoire entre 6 et 9 lettres
		Random random = new Random();
		this.tailleMot = random.nextInt(4)+6;
		motRech = dico.motAlea(tailleMot).toUpperCase();
		
		//initialise le tableau trouve qui sert � savoir quelles lettres du mot ont �t� trouv�es durant la partie � False
		this.trouve = new boolean[tailleMot];
		this.trouve[0]=true;
		for(int i=1;i<tailleMot;i++)
			trouve[i]=false;
		//initialisation du tableau de v�rification, de la variable gagne qui sert � savoir si on a gagn� ou pas et le joueur.
		this.tabVerif = new int[nbCoupsMax][tailleMot];
		this.gagne = false;
		this.joueur= new Joueur("motus","",0);
	}
	
	//C'�tait pour le  jeu en console.
	
	/*private void affichage1() {
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
		System.out.print("Lettre bien plac�e : ");
		for(int i=0; i<tailleMot;i++)
			if(tabVerif[nbCoups][i]==1)
				System.out.print(" " + i + "("+ jeu[nbCoups].charAt(i) + ")" );
		
		System.out.println("\n");
		System.out.print("Lettres mal plac�es : ");
		for(int i=0; i<tailleMot;i++)
			if(tabVerif[nbCoups][i]==2)
				System.out.print(" " + i + "("+ jeu[nbCoups].charAt(i) + ")" );
		System.out.println("\n");
	}*/
	

	/*public String saisieMot(Scanner sc) {
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
	}*/
	
	//V�rifie si le mot saisi est le bon ou non.
	//Si oui gagne passe a true sinon a faux.
	
	public void partieGagnee(String mot)
	{
		if(mot.compareTo(motRech)== 0) {
			System.out.println("bravo vous avez gagn� en " + nbCoups + " coups");
			this.gagne = true;
		}
		else
			this.gagne = false;
	}
	
	//0 pour pas dans le mot, 1 dedans, 2 si mal plac�
	//Verifie quelles lettres du mots saisie se trouvent dans le mot o� sont bien plac�es, et
	// mets a jour le tableau 2 dimensions tabVerif
	//si tabVerif[i]=0 : lettre pas trouv�e
	//              =1 : lettre au bon endroit;
	//              =2 : lettre trouv�e mais mal plac�e*
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
	//mets � jour le tableau trouve qui dit garde en m�moire quelles lettres dut mots ont �t� trouv�es.
	//si trouve[i] == true on a d�j� trouv� motRech.charAt(i) dans la partie.
	public void majTrouve() {
		for(int i = 0; i<tailleMot; i++)
			if(tabVerif[nbCoups][i]==1)
				this.trouve[i]= true;
	}
	
	//retourne vrai si perdu, sinon faux.
	public boolean perdu() {
		return (nbCoups >= nbCoupsMax);
	}
	
	//permets de jouer un coup.
	//Prend le mots saisie en parametre, cherche si il existe puis le compare au mots recherch� et mets a jour le tableau correspondant
	//a la grille de jeu, le tableau trouve, la variable gagne et incr�mente le nombre de coups.
	public void jouerCoup(String motSaisi)
	{
		jeu[nbCoups]=motSaisi;
		if(this.dico.motExiste(motSaisi))
		{
			verify(motSaisi);
			majTrouve();
		}
		//affichage2();
		this.nbCoups++;
		partieGagnee(motSaisi);
	}
	
/*	public void jouerPartie()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("azerty");
		trouve[0]= true;
		boolean gagner = false;
		while(nbCoups != nbCoupsMax && !gagner) {
			affichage1();
			jouerCoup(sc);
		}
		sc.close();
	}
	*/
}
