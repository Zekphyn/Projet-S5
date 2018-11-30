package projet;


import penduConsole.PenduPanel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Jeu extends Menu implements ActionListener {
	static Classement classement = new Classement();
	public static void main(String[] args){
		new Jeu("Jeu");
	}
	JFrame fenetre;
	
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
	
	public void lancerPendu() {
		System.out.println("doit lancer le pendu ");
		fenetre.setContentPane(new PenduPanel());
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
			System.exit(0);
		}
		
	}
	
}
