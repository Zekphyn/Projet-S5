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
	
	static Path path = Paths.get("src/file/score.csv");
	static ArrayList<Joueur> listeScore = new ArrayList<>();
	
	public Classement() {
		
	}

	public static void voirScore() throws IOException {
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
	
	public void getHighScore(String jeu) {
		
	}
	
	public static void main(String[] args) throws IOException{
		voirScore();
		System.out.println(listeScore);
	}
}
