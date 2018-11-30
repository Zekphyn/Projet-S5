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
	
	public JPanelMotsMeles()
	{
		try {
			this.motsmeles = new MotsMeles();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.grille = new Grille(motsmeles);
		mainPanel.setLayout(new BorderLayout());
		panelMotsMeles.setOpaque(false);
		mainPanel.add(panelMotsMeles,BorderLayout.CENTER);
	//	Font police = new Font("Arial", Font.BOLD, 14);
	//    jtf.setFont(police);
	 //   jtf.setPreferredSize(new Dimension(150, 30));
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
		panelMotsMeles.add(new Grille(motsmeles));
		panelMotsMeles.add(new GrilleMots(motsmeles));
	}

	public static void addMouseListener(MouseListener mouseListener) {
		// TODO Auto-generated method stub
		
	}
}
