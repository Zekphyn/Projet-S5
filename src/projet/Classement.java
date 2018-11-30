package projet;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;


public class Classement {
	
	private static Path path = Paths.get("src/file/score.csv");
	private ArrayList<Joueur> listeScore = new ArrayList<>();
	
	 // Va lire le fichier csv et transformer chaque ligne en objet Joueur qu'on stock dans listeScore
	public Classement() {
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
        try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  ArrayList<Joueur> getListeScore() {
		return listeScore;
	}
	
	public String toString() {
		String s ="";
		String jeu ="";
		for ( Joueur joueur : listeScore) {
			if( joueur.getScore() !=0) {
				if( ! joueur.getJeu().equals(jeu)) {
					s = s + "\r\n";
					jeu = joueur.getJeu();
				}
				s = s + " " + joueur + "\r\n";
			}
		}
		return s;
		
	}
	
	// retourne l'objet Joueur qui a le plus gros score pour le jeu rentré en param
	public Joueur getHighScore(String jeu) {
		int highscore = 0;
		Joueur joueurHighscore = new Joueur();
		for( int i = 0 ; i < listeScore.size() ; i++) {
			if(listeScore.get(i).getJeu().equalsIgnoreCase(jeu) && listeScore.get(i).getScore() > highscore) {
				highscore = listeScore.get(i).getScore();
				joueurHighscore = listeScore.get(i);
			}
		}
		return joueurHighscore;
	}
	
	// Va remplacer le plus petit score si le score de joueur est plus élévé dans son jeu
	public void setScoreCSV(Joueur joueur) {
		int index = -1;
		joueur.setScore(new Date());
		for( int i = 0 ; i < listeScore.size() ; i++) {
			if(listeScore.get(i).getJeu().equalsIgnoreCase(joueur.getJeu()) && joueur.getScore() >= listeScore.get(i).getScore()){
				index = i;
				if(joueur.getScore() > listeScore.get(i).getScore()) {
					break;
				}
			}
		}		
		if(index != -1) {
			listeScore.add(index, joueur);
			index++;
			while(index % 5 != 0) {
				index++;
			}
			listeScore.remove(index);
			// Si il y a modification du score, on réécrit 
			try (BufferedWriter writer = Files.newBufferedWriter(path,StandardCharsets.UTF_8)) {

	            StatefulBeanToCsv<Joueur> beanToCsv = new StatefulBeanToCsvBuilder(writer).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();        
	            beanToCsv.write(listeScore);

	        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException ex) {
	        }
		}
	}
	
	/*public static void main(String[] args) throws IOException, InterruptedException{
		new Classement();
		System.out.println(listeScore);
		Joueur joueur = new Joueur("Mots meles","Julien",0);
		TimeUnit.SECONDS.sleep(5);
		joueur.setScore(new Date());
		setScoreCSV(joueur);
		System.out.println(listeScore);
		getHighScore("Mots meles");
	}*/
}
