package projet;

import pendu.Fenetre;
import pendu.Model;
import pendu.Observable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Jeu extends Menu implements ActionListener {
	
	//Menu menu = new Menu();
	
	public static void main(String[] args){
		new Jeu("Jeu");
	}
	JFrame fenetre;

	public Jeu() {
		
	}
	public Jeu(Menu menu) {
		
	}
	public Jeu(String nom) {
		fenetre = new JFrame(nom);
		fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);     
        fenetre.setContentPane(getPanelMenu());
		fenetre.setVisible(true);
	}
	
	public void lancerPendu() {
		System.out.println("doit lancer le pendu");
		Observable model = new Model();
		Fenetre pendu = new Fenetre(model);
		fenetre.setContentPane(pendu.getPanel());
		fenetre.add(pendu.getMenu());
		fenetre.revalidate();
	}
	
	public void lancerMotus() {
		System.out.println("doit lancer le motus");
	}
	
	public void lancerMotsMeles() {
		System.out.println("doit lancer MotsMeles");
	}
	
	public void lancerSudoku() {
		System.out.println("doit lancer sudoku");
	}
	
	public void lancerClassement() {
		System.out.println("doit afficher les classements");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Pendu")) {
			lancerPendu();
		} else if (e.getActionCommand().equals("Motus")) {
			lancerMotus();
		} else if (e.getActionCommand().equals("Mots Meles")) {
			lancerMotsMeles();
		} else if (e.getActionCommand().equals("Sudoku")) {
			lancerSudoku();
		} else if (e.getActionCommand().equals("Classement")) {
			lancerClassement();
		} else if (e.getActionCommand().equals("Quitter")) {
			System.exit(1);
		}
		
	}
	
}
