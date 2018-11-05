package pendu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Fenetre extends JFrame{

	private JMenuBar menu = null;

	  private JMenu fichier = null;
	  private JMenuItem nouveau = null;
	  private JMenuItem quitter = null;
	  private JMenu apropos = null;
	  private JMenuItem apropos2 = null;
	  private JMenuItem rules = null;
	  private JPanel conteneur = new JPanel();
	  private Dimension size;
	  
	  
	  public Fenetre(){
		this.setTitle("Le pendu");
	    this.setSize(1200, 800);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    

	    this.size = new Dimension(this.getWidth(), this.getHeight());
	    
	    menu = new JMenuBar();

	    fichier = new JMenu("Fichier");
	    fichier.setMnemonic('f');
	    //fichier.addActionListener(this);

	    nouveau = new JMenuItem("Nouveau");
	    nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
	                                                  InputEvent.CTRL_MASK));
	    nouveau.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				conteneur.removeAll();
				conteneur.add(new JPanelPendu(size).getPanel());
				conteneur.revalidate();
			}	    	
	    });

	    quitter = new JMenuItem("Quitter");
	    quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
	                                                  KeyEvent.CTRL_MASK));
	    quitter.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		System.exit(0);
	    	}
	    });

	    fichier.add(nouveau);
	    fichier.addSeparator();
	    fichier.add(quitter);

	    apropos = new JMenu("À propos");
	    apropos.setMnemonic('o');
	    //apropos.addActionListener(this);

	    rules = new JMenuItem("Règles du jeu");
	    rules.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				conteneur.removeAll();
				conteneur.add(new ReglePendu(size).getPanel(), BorderLayout.CENTER);
				conteneur.revalidate();
			}	    	
	    });

	    apropos2 = new JMenuItem("   ?   ");
	    apropos2.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		JOptionPane.showMessageDialog(null,
							    		          "Créateur : Projet Algo & Dev",
							    		          "Informations", JOptionPane.NO_OPTION);
	    		conteneur.removeAll();
	    		conteneur.add(new AccueilPendu(size).getPanel());
	    		conteneur.revalidate();
	    	}
	    });

	    apropos.add(rules);
	    apropos.add(apropos2);

	    menu.add(fichier);
	    menu.add(apropos);
	    
	    this.conteneur.setPreferredSize(this.size);
	    this.conteneur.setBackground(Color.white);
	    this.conteneur.add(new AccueilPendu(this.size).getPanel());
	    this.setContentPane(this.conteneur);
	    
	    this.setJMenuBar(menu);
	}
	

	
	public void accueil(){
		System.out.println("Mise à jour de l'accueil…");
		conteneur.removeAll();
		conteneur.add(new AccueilPendu(size).getPanel(), BorderLayout.CENTER);
		conteneur.revalidate();
	}


	public void update(String mot, int pts, String imgPath, int nbreMot) {}
	public void restart(String word) {}

}
