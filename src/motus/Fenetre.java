package motus;

import java.awt.Dimension;
import javax.swing.*;

public class Fenetre extends JPanelMotus{
	 //JTextField texte;
	 //JLabel label = new JLabel("Un JTextField");
	// JButton b;
	  JFrame fenetre;
	
	public Fenetre() {
		fenetre = new JFrame("Motus");
		fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLocationRelativeTo(null);
		fenetre.setContentPane(getJPanelMotus());
		fenetre.setMinimumSize(new Dimension(900,768));
		fenetre.pack();
		fenetre.setVisible(true);	
	}
}
