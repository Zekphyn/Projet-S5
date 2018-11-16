package penduConsole;

import java.util.Scanner;


public class Pendu {

	Mot mot;
	int nb_essaie_restant;

	public Pendu() {
		this.mot = new Mot();
	}

	
	public int NouvellePartie() {
		int resultat = 0;
		System.out.println("Bienvenue dans le jeu du pendu");
		Scanner scan = new Scanner(System.in); 
		while(!this.mot.estFini() && this.mot.getNombreErreur() < 5 ) {
			System.out.println(this.mot.getMotSecret());
			System.out.println("this avez "+ (5-this.mot.getNombreErreur()) +" erreurs restantes");
			System.out.println("Veuillez saisir une lettre :"); 
			char lettre =  scan.next().charAt(0);
			resultat = this.mot.verifierMot(lettre);
		}
		
		if(resultat == 1) {
			System.out.println("Bravo vous avez trouver le mot : " + this.mot.getMot());
		}else {
			System.out.println("Dommage le mot était : " + this.mot.getMot());
		}
		System.out.println("Voulez vous rejouer (yes or no) ?");
		char rejouer = scan.next().charAt(0);
		scan.close();
		return (rejouer == 'y') ? 1 : -1;
	}

	
}
