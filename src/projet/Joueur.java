package projet;
import java.util.Date;

import com.opencsv.bean.CsvBindByName;

public class Joueur {

	@CsvBindByName
	private String nom;
	@CsvBindByName
	private int score;
	@CsvBindByName
	private String jeu;
	private Date creation;
	
	public Joueur() {
		
	}
	
	public Joueur(String jeu, String nom, int score) {
		this.nom=nom;
		this.score=score;
		this.jeu = jeu;
		this.creation = new Date();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getScore() {
		return score;
	}

	public void setScore(Date timeScore) {
		int s = (int) (timeScore.getTime()/1000) - (int) (creation.getTime()/1000);
		if(s > 5000) {
			this.score = 0;
		}else {
			this.score = 5000 - s;
		}
	}

	public String getJeu() {
		return jeu;
	}

	public void setJeu(String jeu) {
		this.jeu = jeu;
	}
	
	@Override
	public String toString() {
		return jeu + " : " + nom + "  -  " + score ;
	}
}
