package sudoku;

import java.util.ArrayList;
import java.util.List;

public class RegionLettres {
	// Tableau de 3x3 Cases
	public CaseLettres[][] region;
	
	// Tableau des caracteres autorisés
	List<Character> caracteresAutorises = new ArrayList<Character>();
	
	
	// Constructeur par defaut avec des cases vides :
	public RegionLettres()
	{
		region=new CaseLettres[3][3];
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				region[i][j]= new CaseLettres();
			}
		}
	}
	
	// Accesseur de region qui retourne la case de coordonnees i,j dans la region
	public CaseLettres getCase(int i , int j)
	{
		return region[i][j];
	}
	
	// Modifier region
	public void setCase(int i, int j, CaseLettres c)
	{
		(region[i][j]).setChar(c.getChar());
	}
	
	// Accesseur de region qui retourne la case de coordonnees i,j dans la region
	public char getCaseChar(int i,int j)
	{
		return (region[i][j]).getChar();
	}
	
	// Modifier region
	public void setCaseChar(int i ,int j,char c)
	{
		(region[i][j]).setChar(c);
	}
	
	// Accesseur de fixe qui retourne l'etat de la case de coordonnees i,j dans la region
	public boolean getCaseFixe(int i, int j)
	{
		return (region[i][j]).getFixe();
	}
	
	// Modifier fixe
	public void setCaseFixe(int i,int j, boolean decision)
	{
		(region[i][j]).setFixe(decision);
	}
	
	// Verifie si la region contient tous les chiffres de 1 a 9
	public boolean regionComplete()
	{
		boolean un=false ;
		boolean deux=false ;
		boolean trois=false ;
		boolean quatre=false ;
		boolean cinq=false ;
		boolean six=false ;
		boolean sept=false ;
		boolean huit=false ;
		boolean neuf=false ;
		
		for(int l=0;l<3;l++)
			for(int c=0;c<3;c++)
			{
				
				if((region[l][c].getChar())==caracteresAutorises.get(0))
					un=true;
				else if ((region[l][c].getChar())==caracteresAutorises.get(1))
					deux=true;
				else if ((region[l][c].getChar())==caracteresAutorises.get(2))
					trois=true;
				else if ((region[l][c].getChar())==caracteresAutorises.get(3))
					quatre=true;
				else if ((region[l][c].getChar())==caracteresAutorises.get(4))
					cinq=true;
				else if ((region[l][c].getChar())==caracteresAutorises.get(5))
					six=true;
				else if ((region[l][c].getChar())==caracteresAutorises.get(6))
					sept=true;
				else if ((region[l][c].getChar())==caracteresAutorises.get(7))
					huit=true;
				else if ((region[l][c].getChar())==caracteresAutorises.get(8))
					neuf=true;
				
				
			}
		
		if(un && deux && trois && quatre && cinq && six && sept && huit && neuf )
			return true;
		else return false;
		
	}
	
	// Verifie si la region est valide 
	public boolean regionValide(int l,int c)
	{
		if(this.getCaseChar(l, c)==0)
			return false;
		int k=0;
		int p=0;
		int temp;
		while(k<3 && p<3)
		{
			temp=(region[k][p]).getChar();
			if(temp != 0)
			{
				for(int i=0;i<3;i++)
					for(int j=0;j<3;j++)
					{
						if((region[i][j]).getChar() == temp)
							return false;
					}
			}
			k++ ;
			p++;
		}
		return true;
	}
	
	// Verifie si la valeur ajoutee a la region ne la rend pas invalide
	public boolean regionValide(int i,int j,char val)
	{
		if(val=='0')
			return false;
		for(int l=0;l<3;l++)
			for(int c=0;c<3;c++)
			{
				if(this.getCaseChar(l, c)==val)
					return false;
			}
		return true;
	}
	
	// Afficher une region
	public String toString()
	{
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
				sb.append(region[i][j]+" ");
			sb.append("\n");
		}
		sb.append("\n");
		return sb.toString();
		
	}
	
	
}
