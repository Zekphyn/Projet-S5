package projet;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;


public class Classement {
	
	private static Path path = Paths.get("src/file/score.csv");
	private static ArrayList<Joueur> listeScore = new ArrayList<>();
	
	 // Va lire le fichier csv et transformer chaque ligne en objet Joueur qu'on stock dans listeScore
	public Classement() throws IOException {
		Reader reader = null;
		try {
			reader = Files.newBufferedReader(Paths.get("src/file/score.csv"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CsvToBean<Joueur> csvToBean = new CsvToBeanBuilder(reader).withType(Joueur.class).withIgnoreLeadingWhiteSpace(true).build();
        Iterator<Joueur> csvJoueurIterator = csvToBean.iterator();
        while (csvJoueurIterator.hasNext()) {
            Joueur joueurCSV = csvJoueurIterator.next();
            listeScore.add(joueurCSV);
        }
        reader.close();
	}
	
	public static ArrayList<Joueur> getListeScore() {
		return listeScore;
	}
	
	// retourne l'objet Joueur qui a le plus gros score pour le jeu rentré en param
	public static Joueur getHighScore(String jeu) {
		String highscore = "0";
		Joueur joueurHighscore = new Joueur();
		for( int i = 0 ; i < listeScore.size() ; i++) {
			if(listeScore.get(i).getJeu().equalsIgnoreCase(jeu) && Integer.parseInt(listeScore.get(i).getScore()) > Integer.parseInt(highscore)) {
				highscore = listeScore.get(i).getScore();
				joueurHighscore = listeScore.get(i);
			}
		}
		System.out.println(joueurHighscore);
		return joueurHighscore;
	}
	
	// Va remplacer le plus petit score si le score de joueur est plus élévé pour le jeu du joueur
	public static void setScore(Joueur joueur) {
		int index = -1;
		int scoreMin = 0;
		for( int i = 0 ; i < listeScore.size() ; i++) {
			if(listeScore.get(i).getJeu().equalsIgnoreCase(joueur.getJeu())){
				if( Integer.parseInt(joueur.getScore()) > scoreMin  && Integer.parseInt(listeScore.get(i).getScore()) <= scoreMin) {
					scoreMin = Integer.parseInt(listeScore.get(i).getScore());
					System.out.println(scoreMin);
					index = i;
				}
			}
		}
		if(index != -1) {
			listeScore.set(index, joueur);
		}
	}
	
	public static void main(String[] args) throws IOException{
		new Classement();
		System.out.println(listeScore);
		Joueur joueur = new Joueur("mots meles","Julien","6000");
		setScore(joueur);
		System.out.println(listeScore);
		getHighScore("Mots meles");
	}
}
