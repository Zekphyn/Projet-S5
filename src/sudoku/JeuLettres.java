package sudoku;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class JeuLettres {
	// Tableau de 3x3 Regions
	public RegionLettres[][] jeu;
	
	// Tableau des caracteres autorisés
	//public char[] caracteresAutorises  ;
	List<Character> caracteresAutorises = new ArrayList<Character>();

	
	// Constructeur par défaut , construit une grille vide
	
	public JeuLettres()
	{
		jeu=new RegionLettres[3][3];
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				jeu[i][j]=new RegionLettres();
	}
	
	public JeuLettres(File fichier)
	{
		jeu=new RegionLettres[3][3];
		boolean b=false;
		//int fixe ;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				jeu[i][j]=new RegionLettres();
		BufferedReader reader ;
		try {
			reader = new BufferedReader(new FileReader(fichier));
			
			String line = reader.readLine();
			int i=-1;
			// On parcourt le fichier avec un scanner
			while (line != null) {
				i++;
				
					for(int j=0;j<18;j=j+2)
					{	
						char c = line.charAt(j);
						int fixe = Integer.parseInt(Character.toString(line.charAt(j+1)));
						this.setCaseChar(i, j/2, c);
						if (fixe==0)
							b=false;
						else b = true;
						this.setCaseFixe(i, j/2, b);
						if(! existe(c) && c!='0')
							this.caracteresAutorises.add(c);
					}
					// Lire la ligne suivante
					line = reader.readLine();
					
						
			}
			reader.close();
			//Passage de la liste des possibilité de jeux
			for (int l=0;l<3;l++)
				for(int c=0;c<3;c++)
					this.jeu[l][c].caracteresAutorises=this.caracteresAutorises;			
						
		}
		
		catch(Exception exc) {
            System.err.println("Oups.. fichier incompatible !");
        }	
	                
			
			
		
		
			
		
	}
	
	// Verifie si le caractere existe deja dans le tableau de possibilites de jeu
	public boolean existe(char c)
	{
		for (int i=0;i<this.caracteresAutorises.size();i++)
		{
			if (caracteresAutorises.get(i)==c)
				return true;
			
		}
		return false;
	}
	
	// Retourne la position du caractere passé en parametre , dans le tableau de possibilites de jeu
	public int position (char c)
	{
		for(int i=0;i<9;i++)
		{
			if (caracteresAutorises.get(i)==c)
				return i;
		}
		return -1;
	}
	
	
	// Retourne la region de coordonnee i , j 
	public RegionLettres getRegion(int i,int j)
	{
		return jeu[i][j];
	}
	
	// Retourne la region dans laquelle se trouve la case i,j dans la grille
	public RegionLettres getRegionDeCase(int i, int j)
	{
		return (jeu[(int)(i/3)][(int)(j/3)]);
	}
	
	// Modifier region
	public void setRegion(RegionLettres[][] r)
	{
		jeu=r;
	}
	
	// Retourne la valeur de la case de coordonnees i,j dans la grille
	public char getCaseChar(int i,int j)
	{
		return (jeu[(int)(i/3)][(int)(j/3)]).getCaseChar(i%3, j%3);
	}
	
	
	// Modifier le num de la case de coordonnees i,j dans la grille
	public void setCaseChar(int i,int j,char c)
	{
		(jeu[(int)(i/3)][(int)(j/3)]).setCaseChar(i%3,j%3,c);
	}
	
	// Retourne l'etat de la case de coordonnees i,j dans la grille
	public boolean getCaseFixe(int i,int j)
	{
		return (jeu[(int)(i/3)][(int)(j/3)]).getCaseFixe(i%3, j%3);
	}
	
	// Modifier Fixe de la case de coordonnees i,j dans la grille
	public void setCaseFixe(int i,int j,boolean decision)
	{
		(jeu[(int)(i/3)][(int)(j/3)]).setCaseFixe(i%3, j%3,decision);
	}
	
	// Retourne l'etat (1 ou 0) Fixe ou non de la case de coordonnees i,j dans la grille
	public int getCaseFixeInt(int i,int j)
	{
		if ( (jeu[(int)(i/3)][(int)(j/3)]).getCaseFixe(i%3, j%3) )
			return 1;
		else return 0;
		
			
	}
	
	// Verifie si la ligne contient tous les chiffres de 1 a 9 
		public boolean ligneComplete(int l)
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
			
			for(int c=0;c<9;c++)
				{   
					
					
					if( (this.getCaseChar(l, c)) ==caracteresAutorises.get(0))
						un=true;
					else if ((this.getCaseChar(l, c))==caracteresAutorises.get(1))
						deux=true;
					else if ((this.getCaseChar(l, c))==caracteresAutorises.get(2))
						trois=true;
					else if ((this.getCaseChar(l, c))==caracteresAutorises.get(3))
						quatre=true;
					else if ((this.getCaseChar(l, c))==caracteresAutorises.get(4))
						cinq=true;
					else if ((this.getCaseChar(l, c))==caracteresAutorises.get(5))
						six=true;
					else if ((this.getCaseChar(l, c))==caracteresAutorises.get(6))
						sept=true;
					else if ((this.getCaseChar(l, c))==caracteresAutorises.get(7))
						huit=true;
					else if ((this.getCaseChar(l, c))==caracteresAutorises.get(8))
						neuf=true;
				}
			
			if(un && deux && trois && quatre && cinq && six && sept && huit && neuf )
				return true;
			else return false;
			
		}
		
		// Verifie si la ligne est valide 
		
		public boolean ligneValide(int l,int c)
		{
			if(this.getCaseChar(l, c)=='0')
				return false;
			
			int j=0;
			int temp;
			while(j<3)
			{
				temp=this.getCaseChar(l,j);
				if(temp != 0)
				{
					for(int k=0;k<3;k++)
						{
							if(k!=j)
								if(temp == this.getCaseChar(l, k))
									return false;
						}
				}
				j++;
			}
			return true;
		}
		
		// Verifie si la ligne est valide avec la nouvelle valeur à inserer
		
		public boolean ligneValide(int l ,int c, char val)
		{
			if(val=='0')
				return false;
			
			for(int i=0;i<9;i++)
			{
				if(val == this.getCaseChar(l, i))
					return false;
			}
			return true;
		}
		
		// Verfie si la colonne contient tous les chiffres de 1 a 9
		
		public boolean colonneComplete(int c)
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
			
			for(int l=0;l<9;l++)
				{
					
					if( (this.getCaseChar(l, c)) == caracteresAutorises.get(0))
						un=true;
					else if ((this.getCaseChar(l, c))==caracteresAutorises.get(1))
						deux=true;
					else if ((this.getCaseChar(l, c))==caracteresAutorises.get(2))
						trois=true;
					else if ((this.getCaseChar(l, c))==caracteresAutorises.get(3))
						quatre=true;
					else if ((this.getCaseChar(l, c))==caracteresAutorises.get(4))
						cinq=true;
					else if ((this.getCaseChar(l, c))==caracteresAutorises.get(5))
						six=true;
					else if ((this.getCaseChar(l, c))==caracteresAutorises.get(6))
						sept=true;
					else if ((this.getCaseChar(l, c))==caracteresAutorises.get(7))
						huit=true;
					else if ((this.getCaseChar(l, c))==caracteresAutorises.get(8))
						neuf=true;
				}
			
			if(un && deux && trois && quatre && cinq && six && sept && huit && neuf )
				return true;
			else return false;
			
		}
		
		// Verifie si la colonne est valide 
		public boolean colonneValide(int l ,int c, char val)
		{
			if(val=='0')
				return false;
			
			for(int i=0;i<9;i++)
			{
				if(val == this.getCaseChar(i, c))
					return false;
			}
			return true;
		}
	
		// Verifie si la colonne en cours de construction est valide
		
		public boolean colonneValide(int l,int c)
		{
			if(this.getCaseChar(l, c)=='0')
				return false;
			
			int j=0;
			int temp;
			while(j<9)
			{
				temp=this.getCaseChar(j,c);
				if(temp != 0)
				{
					for(int k=0;k<3;k++)
						{
							if(k!=j)
								if(temp == this.getCaseChar(k, c))
									return false;
						}
				}
				j++;
			}
			return true;
		}
		
		//Verifie si le joueur a gagne
		public boolean gagne()
		{
			for(int i=0;i<3;i++)
				for(int j=0;j<3;j++)
					if( !(jeu[i][j]).regionComplete())
						return false ;
			for(int i=0;i<9;i++)
				if(!colonneComplete(i) || !ligneComplete(i))
					return false;
			
			return true;
		}
		
		// Resoudre une grille
		
		public boolean resoudre(int i,int j)
		{
			// quand on finit la ligne on passe a la suivante
			if(j==9)
			{
				j=0;
				if(++i == 9)
					return true;
			}
			
			// Si la case est fixe on regarde la suivante
			if(this.getCaseFixe(i, j))
				return resoudre(i,j+1);
			
			// On regarde si on peut placer une valeur , si oui on passe a la suivante , sinon on recule
			for (int val=0;val<9;++val)
			{
				if ( (this.getRegionDeCase(i, j).regionValide(i, j, this.caracteresAutorises.get(val))) && (this.ligneValide(i, j, caracteresAutorises.get(val)) && (this.colonneValide(i, j, this.caracteresAutorises.get(val)))))
				{
					this.setCaseChar(i, j, caracteresAutorises.get(val));
					if(resoudre(i,j+1))
						return true;
				}
			}
			this.setCaseChar(i, j, '0');
			return false;
		}
		
		
		
		// Afficher une grille en console
		
		public String toString()
		{
			StringBuffer sb=new StringBuffer();
			sb.append("-----------------------\n");
			for(int i=0;i<9;i++)
			{
				for(int j=0;j<9;j++)
				{
					if(j==0)
						sb.append("| ");
					sb.append(this.getCaseChar(i, j)+" ");
					if( (j==2) || (j==5) || (j==8))
						sb.append("| ");
				}
			if( (i==2) || (i==5))
				sb.append("\n|--------------------|");
			if(i==8)
				sb.append("\n|--------------------|");
			sb.append("\n");
			}
			return sb.toString();
				
		}
		
		
		

}