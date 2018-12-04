package projet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Dictionnaire {
	private ArrayList<String> dico;
	
	public Dictionnaire() throws IOException {
		dico = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("src/file/dico.txt"));
		}
		catch(FileNotFoundException e) {}
		try {
			String line;
			while ((line = br.readLine()) != null) {
				dico.add(line);}
			br.close();
		}
		catch(IOException io) {
			System.out.println("Erreur");
		}
	}
	
	//retourne true si le mot est dans le dico sinon retourne false
	public boolean motExiste(String mot)
	{
		return this.dico.contains(mot.toUpperCase());
	}
	
	
	//retourne un mot aleatoire de nbChar caractères (retourne null si aucun mots n'est trouvé)
	
		public String motAlea(int nbChar) {
		ArrayList<String> cond = new ArrayList<String>();
		Random random = new Random();
		int nbAleatoire;
		for(String s : dico)
			if(s.length() == nbChar)
				cond.add(s);
		if(cond.isEmpty())
			return null;
		else
		{
			nbAleatoire= random.nextInt(cond.size());
			return cond.get(nbAleatoire);
		}
		
	}
		//retourne un mot aléatorie entre 3 et 12 lettres
		
		public String initMot(){
			String mot = "";
			Random rand = new Random();
			int i = rand.nextInt(10)+3;
			while(mot == "") {
				mot = motAlea(i);
				i = rand.nextInt(10)+3;
			}
			return mot;
		}
		
	
}
