package pendu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class AccueilPendu {
	
	private JPanel panel;
	
	public AccueilPendu(Dimension dim){
		this.panel = new JPanel();
		this.panel.setPreferredSize(dim);
		this.panel.setBackground(Color.white);
		initPanel();
	}

	public void initPanel(){
		JLabel titre = new JLabel("Bienvenue dans le jeu du pendu\n");
		titre.setHorizontalAlignment(JLabel.CENTER);
		this.panel.add(titre, BorderLayout.BEFORE_FIRST_LINE);
		
		this.panel.add(new JLabel(new ImageIcon("src/images/accueil.jpg")), BorderLayout.EAST);
		
		JTextArea texte = new JTextArea(	"Vous avez sept coups pour trouver le mot caché. Si vous réussissez, on recommence !\n" +
											"Plus vous trouvez de mots, plus votre score augmente. Alors, à vous de jouer !\n" +
											"Proverbe :\t« Pas vu, pas pris !\n" +
                      						"\tPris ! PENDU ! »");
		texte.setEditable(false);
		texte.setBackground(Color.white);
		
		this.panel.add(texte, BorderLayout.SOUTH);
		

	}
	
	public JPanel getPanel() {
		return this.panel;
	}
	
}
