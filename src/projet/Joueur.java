package projet;
import com.opencsv.bean.CsvBindByName;

public class Joueur {

	@CsvBindByName
	private String nom;
	@CsvBindByName
	private String score;
	@CsvBindByName
	private String jeu;
	
	public Joueur() {
		
	}
	
	public Joueur(String jeu, String nom, String score) {
		this.nom=nom;
		this.score=score;
		this.jeu = jeu;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getJeu() {
		return jeu;
	}

	public void setJeu(String jeu) {
		this.jeu = jeu;
	}
	
	@Override
	public String toString() {
		return "Jeu : " + jeu + " Nom : " +  nom + " Score : " + score ;
	}
}
