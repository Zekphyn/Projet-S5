package MotsMeles;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Fenetre extends JPanelMotsMeles {
	JFrame fenetre;
	
	
	public Fenetre()
	{
	fenetre = new JFrame("Mots Meles");
	fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH);
    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fenetre.setLocationRelativeTo(null);
    fenetre.setContentPane(getJPanelMotsMeles());
    fenetre.setMinimumSize(new Dimension(900, 768));
    fenetre.pack();
	fenetre.setVisible(true);
}


}
