package motus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import projet.BackgroundPanel;
import projet.Jeu;
import projet.Menu;

public class JPanelMotus extends BackgroundPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//JPanel mainPanel = new BackgroundPanel("src/images/backgroundMenu.png");
	JPanel panelMotus = new JPanel();
	JPanel panelSouth = new JPanel();
	JPanel panelTop = new JPanel();
	JPanel grille = new JPanel();
	JFrame fenetre = new JFrame("Rejouer");
	JButton rejouer = new JButton("rejouer");
	JButton b = new JButton("Valider");
	JTextField jtf = new JTextField();
	JLabel gagne = new JLabel("Bravo, vous avez gagné !!");
	JFrame retour = new JFrame("XXX");
	JButton bq = new JButton("Quitter");
	JButton bm = new JButton("retour Menu");
	JLabel cherche;
	Motus motus;
	
	GridBagConstraints gbc = new GridBagConstraints();
	
	public JPanelMotus() {
		super("src/images/backgroundMenu.png");
		try {
			this.motus = new Motus();
		} catch (IOException e) {
			e.printStackTrace();
		}
	//	mainPanel.setOpaque(true);
		this.setOpaque(true);
		this.grille = new Grille(motus);
		this.setLayout(new BorderLayout());
		panelMotus.setOpaque(false);
		panelSouth.setOpaque(false);
		panelTop.setOpaque(false);
		this.add(panelMotus,BorderLayout.CENTER);
		this.add(panelSouth, BorderLayout.SOUTH);
		this.add(panelTop, BorderLayout.NORTH);
		Font police = new Font("Arial", Font.BOLD, 14);
	    jtf.setFont(police);
	    jtf.setPreferredSize(new Dimension(150, 30));
	    setButton(b,100,30);
	    gagne.setFont(new Font("Arial", Font.BOLD, 35));
	    gagne.setForeground(Color.RED);
	    this.cherche = new JLabel("Le mot caché est un mot de " + motus.getTailleMot() + " lettres");
	    cherche.setFont(new Font("Arial", Font.BOLD,35));
	    cherche.setForeground(Color.RED);
		fenetre.setSize(new Dimension(100,50));
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);
	    creerJeu();
		
	}
	
	public void creerJeu() {
		panelMotus.setLayout(new GridBagLayout());
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 1;
		creerGrille(grille);
		panelTop.setLayout(new GridBagLayout());
		gbc.anchor = GridBagConstraints.SOUTH;
		panelTop.add(cherche,gbc);
		panelSouth.add(jtf,gbc);		
	}
	
	public void refresh(){
		this.revalidate();
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
		panelSouth.add(button,gbc);
		//button.addActionListener(this);
	}
	
	public void setFenetreQuitter() {
		retour.setPreferredSize(new Dimension(300,200));
	//	retour.setDefaultCloseOperation(bq.act);
		retour.setVisible(true);
		
	}
	public void updateButton(JButton button, int width, int height)
	{
		button.setPreferredSize(new Dimension(width,height));
		//button.addActionListener(new BoutonListener());
		panelSouth.add(button,gbc);
	}
	
	public void creerGrille(JPanel grille){
		this.remove(grille);
		panelMotus.add(new Grille(motus),gbc);
	}


	public JPanel getJPanelMotus() {
		return this;
	}

	
	  class BoutonListener implements ActionListener{
		    //Redéfinition de la méthode actionPerformed()
		    public void actionPerformed(ActionEvent arg0) {
		    	System.out.println(motus.getMotRech());
		    	Mot mot = new Mot(jtf.getText());
		   		System.out.println(mot.getMot());
		   		jtf.setText("");   
		   		if(mot.getMot().length() == motus.getTailleMot() && mot.getValable())
		   			jouerCoup(mot.getMot());
	    		if(motus.getGagne()) {
	    			panelTop.removeAll();
	    			panelTop.add(gagne, gbc);
		    		panelSouth.removeAll();
		    		}
		    	
		    	if(motus.perdu())
		    	{
		    		panelTop.removeAll();
		    		panelSouth.removeAll();
		    		gagne.setText("Vous avez perdu !! \n le mot caché était : " + motus.getMotRech());
		    		panelTop.add(gagne);
		    	}
		    	refresh();
		    	/*if(motus.getGagne() || motus.perdu()) {
		    		rejouer.setPreferredSize(new Dimension(200,100));
		    		rejouer.addActionListener(new BoutonListener());
		    		fenetre.add(rejouer);
		            fenetre.setContentPane(rejouer);
		            fenetre.pack();
		    		fenetre.setVisible(true);
		    		this = new JPanelMotus();
		    	}*/
		    }
		  }
	  
	  			
	  		

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	
}
