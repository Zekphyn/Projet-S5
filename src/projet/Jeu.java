package projet;


import pendu.PenduPanel;
import sudoku.Grille;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import motus.JPanelMotus;

public class Jeu extends Menu implements ActionListener {
	static Classement classement = new Classement();
	public static void main(String[] args){
		new Jeu("Jeu");
	}
	public static JFrame fenetre;
	
	public Jeu() {
		
	}
	
	public Jeu(String nom) {
		fenetre = new JFrame(nom);
		fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);
        fenetre.setContentPane(getPanelMenu());
        fenetre.setMinimumSize(new Dimension(900, 768));
        fenetre.pack();
		fenetre.setVisible(true);
	}
	
	public void lancerPendu() throws IOException {
		System.out.println("doit lancer le pendu ");
		PenduPanel pendu = new PenduPanel(fenetre.getWidth(),fenetre.getHeight());
		fenetre.setContentPane(pendu);
		fenetre.revalidate();
	}
	
	public void lancerMotus() {
		System.out.println("doit lancer le motus");
		fenetre.setContentPane(new JPanelMotus());
		fenetre.revalidate();
	}
	
	public void lancerMotsMeles() {
		System.out.println("doit lancer MotsMeles");
	}
	
	public void lancerSudoku() {
		System.out.println("doit lancer sudoku");
		Grille sudoku = new Grille();
		fenetre.setContentPane(sudoku.getPanel());
		fenetre.setJMenuBar(sudoku.getMenuSudoku());
		fenetre.revalidate();
	}
	
	public void lancerClassement() {
		JTextArea textArea = new JTextArea(classement.toString());
		JScrollPane scrollPane = new JScrollPane(textArea);  
		textArea.setLineWrap(true);  
		textArea.setWrapStyleWord(true); 
		scrollPane.setPreferredSize( new Dimension( 400, 600 ) );
		JOptionPane.showMessageDialog(null, scrollPane, "Classement",  JOptionPane.PLAIN_MESSAGE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Pendu")) {
			try {
				lancerPendu();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getActionCommand().equals("Motus")) {
			lancerMotus();
		} else if (e.getActionCommand().equals("Mots Meles")) {
			lancerMotsMeles();
		} else if (e.getActionCommand().equals("Sudoku")) {
			lancerSudoku();
		} else if (e.getActionCommand().equals("Classement")) {
			lancerClassement();
		} else if (e.getActionCommand().equals("Quitter")) {
			System.exit(0);
		}
		
	}
	
}
