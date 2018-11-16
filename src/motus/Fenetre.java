package motus;

import java.awt.Color;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;

public class Fenetre extends JFrame{
	private Motus motus;
	public Fenetre() {
		Scanner sc = new Scanner(System.in);
		try {
			this.motus=new Motus();
		} catch (IOException e) {
		}
		this.setTitle("Motus");
		this.setSize(1000,1000);
		JPanel pan = new JPanelMotus(getGraphics(),motus);
		this.setContentPane(pan);
		this.setVisible(true);
		while(motus.getNbCoups()<motus.getNbCoupsMax()) {
			motus.jouerCoup(sc);
		    pan = new JPanelMotus(getGraphics(),motus);
			this.setContentPane(pan);
			this.setVisible(true);
		}
	}
}
