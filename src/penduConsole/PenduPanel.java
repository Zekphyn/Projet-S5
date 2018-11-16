package penduConsole;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class PenduPanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	char[] lettres = {'a','z','e','r','t','y','u','i','o','p','q','s','d','f','g','h','j','k','l','m','w','x','c','v','b','n'};
	private JButton bouton[];
	private JLabel mot, erreur;
	private ImageLabel image;
	Pendu pendu;
	
	
	public PenduPanel() {
		pendu = new Pendu();
		init();
	}
	
	
	public void init(){
		this.setSize(1600, 1500);
		System.out.println(this.getWidth());
		Dimension dim = new Dimension(this.getWidth(),150);
		//this.setPreferredSize();
		
		// HEADER
		JPanel head = new JPanel(new BorderLayout());
		head.setBackground(Color.ORANGE);
		head.setPreferredSize(dim);
		
		
		JLabel titre = new JLabel("Bienvenue dans le Jeu du Pendu");
		titre.setFont(new Font("Arial", Font.BOLD, 30));
		titre.setHorizontalAlignment(JLabel.CENTER);
		head.add(titre,BorderLayout.CENTER);
		
		
		//LEFTCONTEND
		JPanel left = new JPanel();
		left.setBackground(Color.YELLOW);
		dim =new Dimension(this.getWidth()/2,650);
		left.setPreferredSize(dim);
		
		mot =  new JLabel(pendu.mot.getMotSecret());
		mot.setFont(new Font("Comics Sans MS", Font.BOLD, 50));
		mot.setHorizontalAlignment(JLabel.CENTER);

		left.add(mot);
		
		BoutonListener bl = new BoutonListener();
		Dimension buttonDimension = new Dimension(50,30);
		this.bouton = new JButton[26];
		int i = 0;
		for(char c : lettres){
			this.bouton[i] = new JButton(String.valueOf(c).toUpperCase());
			bouton[i].addActionListener(bl);
			bouton[i].setPreferredSize(buttonDimension);
			left.add(bouton[i]);
			i++;
		}
		
		erreur = new JLabel("Vous avez le droit à "+(5 -pendu.mot.getNombreErreur())+" erreurs.");
		erreur.setFont(new Font("Arial",Font.BOLD, 20));
		erreur.setHorizontalAlignment(JLabel.CENTER);

		left.add(erreur);
		
		//RIGHTCONTEND
		JPanel right = new JPanel(new BorderLayout());
		this.image = new ImageLabel(); 
		this.image.setPreferredSize(dim);
		this.image.setVerticalAlignment(JLabel.CENTER);
		right.add(image,BorderLayout.CENTER);
		right.setBackground(Color.WHITE);
		right.setPreferredSize(dim);
		
		
		
		//BOTTOM
		JPanel bottom = new JPanel(new BorderLayout());
		dim = new Dimension(this.getWidth(),50);
		bottom.setPreferredSize(dim);
		JLabel regles = new JLabel("LES REGLES SONT LES SUIVANTES :");
		regles.setHorizontalAlignment(JLabel.CENTER);
		bottom.add(regles,BorderLayout.NORTH);
		JLabel regles2 = new JLabel("Vous devez trouver le mot secret en réalisant le moins de coup possible et en faisant le moins d'erreur possibles.\n"
				+"\n ATTENTION : si vous faites trop d'erreurs vous perdez");
		regles2.setHorizontalAlignment(JLabel.CENTER);
		bottom.add(regles2, BorderLayout.CENTER);
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.BLUE);
		
		this.add(head, BorderLayout.NORTH);
		this.add(left, BorderLayout.WEST);
		this.add(right, BorderLayout.EAST);
		this.add(bottom, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	

	class BoutonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			((JButton)e.getSource()).setEnabled(false);
			erreur.setText("Vous avez le droit à "+(5 - pendu.mot.getNombreErreur())+" erreurs.");
			if(pendu.mot.estFini()) {
				JOptionPane.showMessageDialog(null,
                        "Vous avez trouvé le mot " + pendu.mot.getMot() +
                        " en " +
                        pendu.mot.getNombreCoup() + " coup" + ((pendu.mot.getNombreCoup() > 1) ? "s" : "") +
						", avec " + pendu.mot.getNombreErreur() +
                        " erreur" + ((pendu.mot.getNombreErreur() > 1) ? "s" : "") + ".\n",
						"Résultat", JOptionPane.INFORMATION_MESSAGE);
			}else if(pendu.mot.getNombreErreur()== 5) {
				JOptionPane.showMessageDialog(null,
                        "Le mot était :\n\t" +
                        pendu.mot.getMot(),
						"Vous avez perdu", JOptionPane.NO_OPTION);
			}else {
				pendu.mot.verifierMot(((JButton)e.getSource()).getText().charAt(0));
				mot.setText(pendu.mot.getMotSecret());
				String path = "src/images/pendu"+pendu.mot.getNombreErreur()+".jpg";
				image.setImagePath(path);
				System.out.println(path);
			}
		}		
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
