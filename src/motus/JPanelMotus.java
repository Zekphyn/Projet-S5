package motus;

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

public class JPanelMotus implements ActionListener{
	JPanel mainPanel = new BackgroundPanel("src/images/backgroundMenu.png");
	JPanel panelMotus = new JPanel();
	JPanel grille = new JPanel();
	JButton b = new JButton("Valider");
	JTextField jtf = new JTextField();
	JLabel gagne = new JLabel("Bravo, vous avez gagné !!");
	Motus motus;
	GridBagConstraints gbc = new GridBagConstraints();
	
	public JPanelMotus() {
		try {
			this.motus = new Motus();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.grille = new Grille(motus);
		mainPanel.setLayout(new BorderLayout());
		panelMotus.setOpaque(false);
		mainPanel.add(panelMotus,BorderLayout.CENTER);
		Font police = new Font("Arial", Font.BOLD, 14);
	    jtf.setFont(police);
	    jtf.setPreferredSize(new Dimension(150, 30));
	    setButton(b,100,30);
		creerJeu();
		
	}
	
	public void creerJeu() {
		panelMotus.setLayout(new GridBagLayout());
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 1;
		//creerGrille(grille,1000,1000);
		creerGrille(grille, 1000, 500);
		panelMotus.add(jtf);		
	}
	
	public void jouerCoup(String mot) {
		motus.jouerCoup(mot);
		panelMotus.removeAll();
		updateButton(b, 100,30);
		creerJeu();
	}
	
	public void setButton(JButton button, int width, int height) {
		button.setPreferredSize(new Dimension(width,height));
		button.addActionListener(new BoutonListener());
		panelMotus.add(button,gbc);
		//button.addActionListener(this);
	}
	
	public void updateButton(JButton button, int width, int height)
	{
		button.setPreferredSize(new Dimension(width,height));
		//button.addActionListener(new BoutonListener());
		panelMotus.add(button,gbc);
	}
	
	public void creerGrille(JPanel grille, int width, int height){
		grille.setPreferredSize(new Dimension(width, height));
		mainPanel.remove(grille);
		panelMotus.add(new Grille(motus));
	}


	public JPanel getJPanelMotus() {
		return this.mainPanel;
	}

	  class BoutonListener implements ActionListener{
		    //Redéfinition de la méthode actionPerformed()
		    public void actionPerformed(ActionEvent arg0) {
		    	if(motus.getNbCoups()<motus.getNbCoupsMax()) {
		    		System.out.println(motus.getMotRech());
		    		Mot mot = new Mot(jtf.getText());
		    		jtf.setText("");
		    		if(mot.getMot().length() == motus.getTailleMot())
		    			jouerCoup(mot.getMot());
		    		if(motus.getGagne()==true) {
		    			mainPanel.add(gagne);
		    		}
		    		mainPanel.revalidate();
		    	}
		    }
		  }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
