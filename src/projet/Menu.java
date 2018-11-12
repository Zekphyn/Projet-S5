package projet;

import java.awt.BorderLayout;
import java.awt.Color;
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
	
	JPanel mainPanel = new BackgroundPanel("src/images/backgroundMenu.png");
	JPanel panelMenu = new JPanel();
	JButton buttonPendu = new JButton("Pendu");
	JButton buttonMotus = new JButton("Motus");
	JButton buttonMotsMeles = new JButton("Mots Meles");
	JButton buttonSudoku = new JButton("Sudoku");
	JButton buttonClassement = new JButton("Classement");
	JButton buttonQuitter = new JButton("Quitter");
	
	public Menu() {
		mainPanel.setLayout(new BorderLayout());
		panelMenu.setOpaque(false);
		mainPanel.add(panelMenu,BorderLayout.CENTER);
		setPanelButton();
	}
	
	public void setPanelButton() {
		panelMenu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 1;

		panelMenu.add(buttonPendu,gbc);
		panelMenu.add(buttonMotus,gbc);
		panelMenu.add(buttonMotsMeles,gbc);
		panelMenu.add(buttonSudoku,gbc);
		panelMenu.add(buttonClassement,gbc);
		panelMenu.add(buttonQuitter,gbc);
		
		buttonPendu.addActionListener(this);
		buttonMotus.addActionListener(this);
		buttonMotsMeles.addActionListener(this);
		buttonSudoku.addActionListener(this);
		buttonClassement.addActionListener(this);
		buttonQuitter.addActionListener(this);
	}
	
	public JPanel getPanelMenu() {
		return this.mainPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
	}

}
	