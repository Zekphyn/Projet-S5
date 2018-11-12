package projet;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu implements ActionListener {
	
	JPanel panelMenu = new JPanel();
	JButton buttonPendu = new JButton("Pendu");
	JButton buttonMotus = new JButton("Motus");
	JButton buttonMotsMeles = new JButton("Mots Meles");
	JButton buttonSudoku = new JButton("Sudoku");
	JButton buttonClassement = new JButton("Classement");
	JButton buttonQuitter = new JButton("Quitter");
	
	public Menu() {
		panelMenu.setBackground(Color.BLACK);
		panelMenu.add(buttonPendu);
		panelMenu.add(buttonMotus);
		panelMenu.add(buttonMotsMeles);
		panelMenu.add(buttonSudoku);
		panelMenu.add(buttonClassement);panelMenu.add(buttonQuitter);
		buttonPendu.addActionListener(this);
		buttonMotus.addActionListener(this);
		buttonMotsMeles.addActionListener(this);
		buttonSudoku.addActionListener(this);
		buttonClassement.addActionListener(this);
		buttonQuitter.addActionListener(this);
	}
	
	public JPanel getPanelMenu() {
		return this.panelMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
	}
}
	