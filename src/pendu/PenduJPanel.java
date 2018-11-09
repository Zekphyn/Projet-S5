package pendu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PenduJPanel extends JPanel{

	private Dimension dimension = new Dimension();
	private int pts = 0;
	private ImageIcon img;
	private String mot;
	private JLabel 	nombreMot, 
					score,
					motSecret;
	private ImageLabel imageLabel;
	private JButton bouton[];

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		this.setBackground(Color.blue);
		Font font = new Font("Arial", Font.BOLD, 20);
		g.setFont(font);
		g.drawString("Jeu du Pendu", 500, 50);
		g.drawString("Nombre de mots trouvés : 0", 500, 1000);


		this.motSecret = new JLabel("fffffffffffffffffff");
		this.motSecret.setPreferredSize(new Dimension(400, 60));
		this.motSecret.setForeground(Color.blue);
		
		
		//this.motSecret.setFont(comics30);
		this.motSecret.setHorizontalAlignment(JLabel.CENTER);
		this.add(this.motSecret);

		
		char carac[] = {'a', 'b','c', 'd', 'e', 'f',
						'g', 'h', 'i', 'j', 'k', 'l', 
						'm', 'n', 'o', 'p', 'q', 'r', 
						's', 't' ,'u', 'v', 'w', 'x', 
						'y', 'z'};
		
		BoutonListener bl = new BoutonListener();
		Dimension buttonDimension = new Dimension(50,30);
		this.bouton = new JButton[26];
		int i = 0;
		for(char c : carac){
			this.bouton[i] = new JButton(String.valueOf(c).toUpperCase());
			bouton[i].addActionListener(bl);
			bouton[i].setPreferredSize(buttonDimension);
			this.add(bouton[i],BorderLayout.CENTER);
			i++;
		}
		
		
	}	
	public void restart(String word){		
		for(JButton bout : this.bouton)
			bout.setEnabled(true);
		
		this.imageLabel.setImagePath("src/images/pendu0.jpg");
		this.motSecret.setText(word);
	}

	public void update(String mot, int pts, String path, int nbreMot) {
		this.motSecret.setText(mot);
		this.score.setText("Votre score actuel est de " +pts+" point"+((pts > 1) ? "s" : "")+".");
		this.imageLabel.setImagePath(path);
		this.nombreMot.setText("Nombre de mots trouvés : " +nbreMot);	
		
	}
	
	public void accueil() {}


	class BoutonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			((JButton)e.getSource()).setEnabled(false);
		}		
	}
	
}
