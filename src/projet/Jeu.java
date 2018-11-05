package projet;

import pendu.AccueilPendu;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Jeu extends Menu implements ActionListener {
	
	//Menu menu = new Menu();
	
	public static void main(String[] args){
		new Jeu("Jeu");
	}
	JFrame fenetre;
	JPanel testPendu = new JPanel();
	JButton testbuttonPendu = new JButton("testPendu");
	public Jeu() {
		
	}
	public Jeu(Menu menu) {
		
	}
	public Jeu(String nom) {
		fenetre = new JFrame(nom);
		fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testbuttonPendu.addActionListener(this);
        getPanelMenu().add(testbuttonPendu);
     
        fenetre.setContentPane(getPanelMenu());
		fenetre.setVisible(true);
	}
	
	public void lancerPendu() {
		System.out.println("allo");
		AccueilPendu acceuilpendu = new AccueilPendu(new Dimension(900,600));
		
		fenetre.setContentPane(acceuilpendu.getPanel());
		fenetre.revalidate();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Pendu")) {
			System.out.println("testButton");
			lancerPendu();
		}
		
	}
	
}
