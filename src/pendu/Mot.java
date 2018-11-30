package pendu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import javax.swing.JOptionPane;

import projet.Dictionnaire;

public class Mot {

	Dictionnaire  d;
	private String mot = "", motSecret = "";
	private char[] tabChar;
	private int erreur = 0;
	private int nbreCoup = 0;

	public Mot() throws IOException{
		d = new Dictionnaire();
		initMot();
	}
	
	public void initMot(){
		int i = (int)(Math.random() * 15);

		while(this.mot == "") {
			this.mot = d.motAlea(i);
			i = (int)(Math.random() * 15);

		}

		System.out.println(this.mot);
		for(int j = 0; j < this.mot.length(); j++)
		{
			this.motSecret += (this.mot.charAt(j) != '\'' && this.mot.charAt(j) != '-') ? "*" : this.mot.charAt(j);
		}
		this.tabChar = this.motSecret.toCharArray();
		this.erreur = 0;
	}
	
	public int verifierMot(char c){
		
		boolean bok = false;
		
		for(int i = 0; i < this.mot.length(); i++){
			
			if(this.mot.toUpperCase().charAt(i) == Character.toUpperCase(c)){
				this.tabChar[i] = Character.toUpperCase(c);
				bok = true;
			}
		}
		++this.nbreCoup;
		this.motSecret = new String(this.tabChar);
		System.out.println("Mot secret = " + this.mot);
		if(bok == false) this.erreur++;
		return (bok == true) ? 1 : -1;
	}
	
	public int verifierMot(char[] tc){
		
		boolean bok = false;
		
		for(char c : tc)
		{
			for(int i = 0; i < this.mot.length(); i++){
				
				if(this.mot.toUpperCase().charAt(i) == c){
					this.tabChar[i] = c;
					System.out.println("OK !");
					bok = true;
				}
			}
		}	
		++this.nbreCoup;	
		this.motSecret = new String(this.tabChar);
		System.out.println("Mot secret = " + this.mot);
		
		return (bok == true) ? 1 : -1;
	}
	
	public boolean estFini(){
		
		for(char c : this.tabChar){
			if(c == '*')
				return false;
		}
		return true;
	}
	
	public String getMot() {
		return mot;
	}

	public String getMotSecret() {
		return motSecret;
	}
	
	public int getNombreErreur(){
		return this.erreur;
	}
	
	public void setNombreErreur(int nbre){
		this.erreur = nbre;
	}

	public int getNombreCoup() {
		return nbreCoup;
	}
}
