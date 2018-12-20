package motus;

import java.text.Normalizer;

public class Mot {
	private String str;
	private boolean valable;
	
	//retire les accents d'une chaine de caractere, la passe en majuscule et dit si le mot est valable pour le motus ou pas
	//(elle est valable si il n'y a pas de caract�re sp�ciaux du genre ' ' ou '/' ...)
	public Mot(String str) {
		this.str=str;
		str = str.toUpperCase();
		normaliseMotMaj();
		valable = verifLettres();
	}
	
	/*public Mot(String str, int length){
		this.str=str;
		str = str.toUpperCase();
		normaliseMotMaj();
		valableMotus(length);
		if(valable == false) {
			System.out.println("le mot n'est pas valable");
		}
	}*/

	//verifie que le mot ne contient que des lettres (MAJUSCULES), retourne vrai si c'est bon sinon faux.
	public boolean verifLettres() {
		str = str.toUpperCase();
		boolean ver = true;
		for(char c : str.toCharArray())
			if(c < 'A' || c>'Z')
				ver = false;
		return ver;
	}
	
	//transforme une chaine de caract�re quelconque en cette m�me chaine sans les accents (normalize les caract�res du genre �,�,...
	// str prend cette chaine normalis�e comme valeur
	public void normaliseMotMaj() {
		String motf = "";
		str = str.toUpperCase();
		if(Normalizer.normalize(str, Normalizer.Form.NFD).compareTo(str) !=0 ) {
			str = Normalizer.normalize(str, Normalizer.Form.NFD);
			for(char c : str.toCharArray()) {
				if(c >= 'A' && c <= 'Z' )
					motf = motf + Character.toString(c);
			}
			str = motf;
		}
	
	}

    //SI le mot est de la longueur pass�e en parametre pass� valable a vrai sinon � faux (on se sert de cette m�thode pour savoir si le
	//mot est de la m�me taille que le mot recherch� dans le motus
	public void valableMotus(int length) {
		if(str.length() != length) {
			System.out.println("Le mot n'est pas de la bonne taille");
			valable = false;
		}
		else
			valable = verifLettres();
	}
	
	public String getMot() {
		return str;
		}
	
	public boolean getValable() {
		return this.valable;
	}
}
