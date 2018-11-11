package sudoku;

public class Test {

	public static void main(String[] args){
		/** Grille g = new Grille();
		 
		g.lectureGrille();
		//g.afficheGrille();
		Position pos = new Position();
		pos.ligne=2;
		pos.colonne=1;
		
		//System.out.println(g.estDansColonne(8, 1, g.grille));
		//System.out.println(g.estDansRegion(2, pos));
		//boolean x=g.placer(0,0);
		//g.afficheGrille();
		System.out.println("tour joue : "+g.jouerTour(1, 2, 2));
		//System.out.println(g.aideTour());
		g.afficheGrille();
		g.enleverValeur(1, 2);
		System.out.println();
		g.afficheGrille();
		// Pour tester sur la region : System.out.println("tour joue : "+g.jouerTour(8, 6, 1));

		// Pour tester sur la colonne : System.out.println("tour joue : "+g.jouerTour(2, 2, 2));
		//g.afficheGrille();
		//System.out.println("Ligne :"+g.estDansLigne(2, 3));
		//System.out.println("Colonne :"+g.estDansColonne(2, 5))
		**/
		GrilleLettres grilleLettres = new GrilleLettres ();
		grilleLettres.lectureGrille();
		System.out.println(grilleLettres.getUtilisable());
		//grilleLettres.afficheGrille();
		System.out.println();
		grilleLettres.jouerTour(1, 2, 'Y');
		grilleLettres.afficheGrille();
		grilleLettres.enleverValeur(1, 2);
		grilleLettres.afficheGrille();
	}
	

	
}
