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
	
	public Menu() {
		panelMenu.setBackground(Color.BLACK);
		panelMenu.add(buttonPendu);
		panelMenu.add(buttonMotus);
		buttonPendu.addActionListener(this);
		buttonMotus.addActionListener(this);
	}
	
	public JPanel getPanelMenu() {
		return this.panelMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		if (e.getActionCommand().equals("Pendu")) {
			System.out.println("lance le pendu");
			//lancerPendu();
		}else if (e.getActionCommand().equals("Motus")) {
			System.out.println("Lancer le motus");
		}
	}
}
	