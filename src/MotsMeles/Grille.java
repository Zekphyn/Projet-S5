package MotsMeles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;

public class Grille extends JPanel implements MouseListener {
	public int longueur;
	public int largeur;
	private Color couleurLignes ;
	public int largeurCaseGraphique;
	MotsMeles motsmeles;
	public int mouseX;
	public int mouseY;
	public int[] caseDebut = new int[3];
	public int[] caseFin = new int[3];
	public int orientation;
	public int inverse;
	public String mot;
	
	public Grille(MotsMeles motsmeles) {
		super();
		this.motsmeles=motsmeles;
		this.longueur=motsmeles.getHauteurGrille();
		this.largeur=motsmeles.getLargeurGrille();
		couleurLignes = Color.BLACK;	
		this.caseDebut[2] = 0;
		this.caseFin[2] = 0;
		majGrille();
		addMouseListener(this);
	}
	
	public void ajouter(char c) {
		Border blackline = BorderFactory.createLineBorder(couleurLignes,1); 
		JLabel text = new JLabel(Character.toString(c) ,JLabel.CENTER);
		text.setMinimumSize(new Dimension(75,75));
		text.setPreferredSize(new Dimension(100,100));
		text.setFont(new Font("Serif", Font.PLAIN, 36));
		text.setBorder(blackline);
		add(text);
		
	}
	
	public void ajouter(String s)
	{
		Border blackline = BorderFactory.createLineBorder(couleurLignes,1); 
		JLabel text = new JLabel(s ,JLabel.RIGHT);
		text.setMinimumSize(new Dimension(75,75));
		text.setPreferredSize(new Dimension(100,100));
		text.setFont(new Font("Serif", Font.PLAIN, 36));
		text.setBorder(blackline);
		add(text);
	}
	
	public void majGrille()
	{
		int i;
		int j;
		setLayout(new GridLayout(longueur,largeur));
		for(i=0;i< longueur;i++)
		{
			for(j=0;j< largeur;j++)
			{
				ajouter(motsmeles.getA().get(i).get(j));
			}
		}
	}

	public boolean verif()
	{
		boolean verif = false;
		if(caseDebut[0] == caseFin[0])
		{
			System.out.println("ligne");
			verif = true;
			orientation = 0;
			if(caseDebut[1]<caseFin[1]) {
				inverse = 0;
			}
			else
				inverse = 1;
		}
		else if(caseDebut[1] == caseFin[1])
		{
			verif = true;
			orientation = 1;
			if(caseDebut[0] < caseFin[0])
			{
				inverse = 0;
			}
			else inverse = 1;
		}
		else if((caseDebut[0]-caseFin[0]) == (caseDebut[1]-caseFin[1]))
		{
			verif = true;
			orientation = 2;
			if(caseDebut[0] < caseFin[0])
			{
				inverse = 0;
			}
			else inverse = 1;
		}
		else if((caseDebut[0]-caseFin[0]) == -(caseDebut[1]-caseFin[1]))
		{
			verif = true;
			orientation = 3;
			if(caseDebut[0] < caseFin[0])
			{
				inverse = 0;
			}
			else inverse = 1;
		}
		 return verif;
	}
	
	public void parcours()
	{
		mot = "";
		//VerifLigne
		if(orientation == 0)
		{
			if(inverse == 0)
			{
				System.out.println("clonne");
				for(int j = caseDebut[1];j<=caseFin[1];j++)
				{
					mot = mot + Character.toString(motsmeles.getA().get(0).get(j));
					System.out.println(caseDebut[0]);
				}
			}
			else
			{
				for(int j = caseDebut[1];j>=caseFin[1];j--)
				{
					mot = mot + Character.toString(motsmeles.getA().get(caseDebut[0]).get(j));
					System.out.println(j);
				}
			}
		}
		//VerifColonne
		if(orientation == 1)
		{
			if(inverse == 0)
			{
				for(int i = caseDebut[0];i<=caseFin[0];i++)
				{
					mot = mot + Character.toString(motsmeles.getA().get(i).get(caseDebut[1]));
				}
			}
			else
			{
				for(int i = caseDebut[0];i>=caseFin[0];i--)
				{
					mot = mot + Character.toString(motsmeles.getA().get(i).get(caseDebut[1]));
				}
			}
		}
		//VerifDiag1
		if(orientation == 2)
		{
			if(inverse == 0)
			{
				for(int  i = 0;i <= caseFin[0]-caseDebut[0]; i++)
				{
					mot = mot + Character.toString(motsmeles.getA().get(caseDebut[0]+i).get(caseDebut[1]+i));
				}
			}
			else
			{
				for(int  i = 0;i <= caseDebut[0]-caseFin[0]; i++)
				{
					mot = mot + Character.toString(motsmeles.getA().get(caseDebut[0]-i).get(caseDebut[1]-i));
				}
			}
		}
		//verifDiag2 //pb
		if(orientation == 3)
		{
			if(inverse == 0)
			{
				System.out.println("lololol");
				for(int j = 0;j <= caseFin[1]-caseDebut[1]; j++)
				{
					mot = mot + Character.toString(motsmeles.getA().get(caseDebut[0]-j).get(caseDebut[1]+j));
				}
			}
			else
			{
				System.out.println("lololol1");
				for(int i = 0;i <= caseDebut[1]-caseFin[1]; i++)
				{
					mot = mot + Character.toString(motsmeles.getA().get(caseDebut[0]+i).get(caseDebut[1]-i));
				}
			}
		}
		System.out.println(orientation);
		System.out.println(mot);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
			int gridx=0;
			int gridy=0;
			for(int i=0; i<longueur; i++)
			{
				gridy=0;
				for(int j=0; j<largeur; j++)
				{
					if((e.getX() >= gridx && e.getY() >= gridy) && (e.getX() < (gridx+100) && e.getY() < (gridy+100)))
							{
								System.out.println(motsmeles.getA().get(gridy/100).get(gridx/100));
								mouseY= gridx/100;
								mouseX = gridy/100;
							}
					gridy+=100;
				}
				gridx +=100;
			}
					if(caseDebut[2] == 0)
					{
						caseDebut[0]= mouseX;
						caseDebut[1]= mouseY;
						caseDebut[2]=1;
					}
						else if(caseFin[2] == 0)
						{
							caseFin[0]= mouseX;
							caseFin[1]= mouseY;
							caseFin[2]=1;
						}
					if(caseDebut[2] == 1 && caseFin[2] == 1)
					{
						if(verif())
						{
							parcours();
							caseDebut[2]=0;
							caseFin[2]=0;
						}
					}
					
			
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
