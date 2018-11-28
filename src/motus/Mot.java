package motus;

import java.text.Normalizer;

public class Mot {
	private String str;
	private boolean valable;
	
	public Mot(String str) {
		this.str=str;
		str = str.toUpperCase();
		normaliseMotMaj();
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
	
	public boolean verifLettres() {
		str = str.toUpperCase();
		boolean ver = true;
		for(char c : str.toCharArray())
			if(c < 'A' || c>'Z')
				ver = false;
		return ver;
	}
	
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
