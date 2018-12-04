package projet;


import pendu.PenduGridBag;
import sudoku.GrilleLettres;
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
		PenduGridBag pendu2 = new PenduGridBag(fenetre.getWidth(),fenetre.getHeight());
		fenetre.setContentPane(pendu2);
		fenetre.revalidate();
	}
	
	public void lancerPendu(Joueur joueur) throws IOException {
		System.out.println("doit lancer le pendu ");
		PenduGridBag pendu2 = new PenduGridBag(joueur,fenetre.getWidth(),fenetre.getHeight());
		fenetre.setContentPane(pendu2);
		fenetre.revalidate();
	}
	
	public void lancerMotus() {
		System.out.println("doit lancer le motus");
		JPanelMotus motus = new JPanelMotus(fenetre.getWidth(),fenetre.getHeight());
		fenetre.setContentPane(motus);
		fenetre.revalidate();
	}
	
	public void lancerMotsMeles() {
		System.out.println("doit lancer MotsMeles");
	}
	
	public void lancerSudoku() {
		System.out.println("doit lancer sudoku");
		GrilleLettres sudoku = new GrilleLettres();
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
