package MotsMeles;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import projet.BackgroundPanel;

public class JPanelMotsMeles {
	JPanel mainPanel = new BackgroundPanel("src/images/backgroundMenu.png");
	JPanel panelMotsMeles = new JPanel();
	JPanel grille = new JPanel();
	//JTextField jtf = new JTextField();
	JButton b = new JButton("Valider");
	MotsMeles motsmeles;
	GridBagConstraints gbc = new GridBagConstraints();
	GrilleMots rep;
	
	public JPanelMotsMeles()
	{
		try {
			this.motsmeles = new MotsMeles();
		} catch (IOException e) {
			e.printStackTrace();
		}
		rep = new GrilleMots(motsmeles);
		this.grille = new Grille(motsmeles,rep);
		mainPanel.setLayout(new BorderLayout());
		panelMotsMeles.setOpaque(false);
		mainPanel.add(panelMotsMeles,BorderLayout.CENTER);
	    creerJeu();
	}

	public JPanel getJPanelMotsMeles() {
		return this.mainPanel;
	}
	
	public void creerJeu() {
		//creerGrille(grille,1000,1000);
		creerGrille(grille, 500, 500);
	//	panelMotsMeles.add(jtf);		
	}

	public void creerGrille(JPanel grille, int width, int height){
		grille.setPreferredSize(new Dimension(width, height));
		mainPanel.remove(grille);
		panelMotsMeles.add(new Grille(motsmeles,rep));
		panelMotsMeles.add(rep);
	}

	public static void addMouseListener(MouseListener mouseListener) {
		// TODO Auto-generated method stub
	}
}
