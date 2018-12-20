package projet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Menu implements ActionListener {
	
	// le menu a un mainpanel qui contient redessine le fond d'�cran et qui contient un sous panel compos� de boutons
	JPanel mainPanel = new BackgroundPanel("src/images/backgroundMenu.png");
	JPanel panelMenu = new JPanel();
	JButton buttonPendu = new JButton("Pendu");
	JButton buttonMotus = new JButton("Motus");
	JButton buttonMotsMeles = new JButton("Mots Meles");
	JButton buttonSudoku = new JButton("Sudoku");
	JButton buttonClassement = new JButton("Classement");
	JButton buttonQuitter = new JButton("Quitter");
	GridBagConstraints gbc = new GridBagConstraints();
	
	public Menu() {
		mainPanel.setLayout(new BorderLayout());
		panelMenu.setOpaque(false);
		mainPanel.add(panelMenu,BorderLayout.CENTER);
		setPanelButton();
	}
	
	// positionne les boutons
	public void setPanelButton() {
		panelMenu.setLayout(new GridBagLayout());
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 1;

		setButton(buttonPendu, 120, 40);
		setButton(buttonMotus, 120, 40);
		setButton(buttonMotsMeles, 120, 40);
		setButton(buttonSudoku, 120, 40);
		setButton(buttonClassement, 120, 40);
		setButton(buttonQuitter, 120, 40);
	}
	// ajoute les boutons au panel du menu et prepare leur listener
	public void setButton(JButton button, int width, int height) {
		button.setPreferredSize(new Dimension(width,height));
		panelMenu.add(button,gbc);
		button.addActionListener(this);
	}
	// recuperer le panel permet de l'ins�rer dans le panel prncipal de la classe Jeu
	public JPanel getPanelMenu() {
		return this.mainPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}
	