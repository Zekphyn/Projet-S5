package sudoku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Jeu {
	// Tableau de 3x3 Regions
	public Region[][] jeu;
	
	// Constructeur par défaut , construit une grille vide
	
	public Jeu()
	{
		jeu=new Region[3][3];
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				jeu[i][j]=new Region();
	}
	
	public Jeu(File fichier)
	{
		jeu=new Region[3][3];
		boolean b=false;
		//int fixe ;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				jeu[i][j]=new Region();
		BufferedReader reader ;
		try {
			reader = new BufferedReader(new FileReader("C:/Users/N&R/git/Projet-S5/src/sudoku/g.txt"));
			
			String line = reader.readLine();
			int i=-1;
			// On parcourt le fichier avec un scanner
			while (line != null) {
				i++;
				
					for(int j=0;j<18;j=j+2)
					{	
						Integer courant = Integer.parseInt(Character.toString(line.charAt(j)));
						System.out.println(courant);
						Integer fixe = Integer.parseInt(Character.toString(line.charAt(j+1)));
						this.setCaseNum(i, j/2, courant);
						if (fixe==0)
							b=false;
						else b = true;
						this.setCaseFixe(i, j/2, b);
					}
					// Lire la ligne suivante
					line = reader.readLine();
					
						
			}
			
						
		}
		
		catch(Exception exc) {
            System.err.println("Ouverture impossible : fichier inexistant ou mal complété.");
        }	
	                
			
			
		
		
			
		
	}
	
	// Retourne la region de coordonnee i , j 
	public Region getRegion(int i,int j)
	{
		return jeu[i][j];
	}
	
	// Retourne la region dans laquelle se trouve la case i,j dans la grille
	public Region getRegionDeCase(int i, int j)
	{
		return (jeu[(int)(i/3)][(int)(j/3)]);
	}
	
	// Modifier region
	public void setRegion(Region[][] r)
	{
		jeu=r;
	}
	
	// Retourne la valeur de la case de coordonnees i,j dans la grille
	public int getCaseNum(int i,int j)
	{
		return (jeu[(int)(i/3)][(int)(j/3)]).getCaseNum(i%3, j%3);
	}
	
	
	// Modifier le num de la case de coordonnees i,j dans la grille
	public void setCaseNum(int i,int j,int c)
	{
		(jeu[(int)(i/3)][(int)(j/3)]).setCaseNum(i%3,j%3,c);
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
				{   System.out.println(this.getCaseNum(l, c));
					switch (this.getCaseNum(l, c))
					{
						case 1: un=true;break;
						case 2: deux=true;break;
						case 3: trois=true;break;
						case 4: quatre=true;break;
						case 5: cinq=true;break;
						case 6: six=true;break;
						case 7: sept=true;break;
						case 8: huit=true;break;
						case 9: neuf=true;break;
						
					
					}
				}
			
			if(un && deux && trois && quatre && cinq && six && sept && huit && neuf )
				return true;
			else return false;
			
		}
		
		// Verifie si la ligne est valide 
		
		public boolean ligneValide(int l,int c)
		{
			if(this.getCaseNum(l, c)==0)
				return false;
			
			int j=0;
			int temp;
			while(j<3)
			{
				temp=this.getCaseNum(l,j);
				if(temp != 0)
				{
					for(int k=0;k<3;k++)
						{
							if(k!=j)
								if(temp == this.getCaseNum(l, k))
									return false;
						}
				}
				j++;
			}
			return true;
		}
		
		// Verifie si la ligne est valide avec la nouvelle valeur à inserer
		
		public boolean ligneValide(int l ,int c, int val)
		{
			if(val==0)
				return false;
			
			for(int i=0;i<9;i++)
			{
				if(val == this.getCaseNum(l, i))
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
					switch (this.getCaseNum(l, c))
					{
						case 1: un=true;break;
						case 2: deux=true;break;
						case 3: trois=true;break;
						case 4: quatre=true;break;
						case 5: cinq=true;break;
						case 6: six=true;break;
						case 7: sept=true;break;
						case 8: huit=true;break;
						case 9: neuf=true;break;
						
					
					}
				}
			
			if(un && deux && trois && quatre && cinq && six && sept && huit && neuf )
				return true;
			else return false;
			
		}
		
		// Verifie si la colonne est valide 
		public boolean colonneValide(int l ,int c, int val)
		{
			if(val==0)
				return false;
			
			for(int i=0;i<9;i++)
			{
				if(val == this.getCaseNum(i, c))
					return false;
			}
			return true;
		}
	
		// Verifie si la colonne en cours de construction est valide
		
		public boolean colonneValide(int l,int c)
		{
			if(this.getCaseNum(l, c)==0)
				return false;
			
			int j=0;
			int temp;
			while(j<9)
			{
				temp=this.getCaseNum(j,c);
				if(temp != 0)
				{
					for(int k=0;k<3;k++)
						{
							if(k!=j)
								if(temp == this.getCaseNum(k, c))
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
			for (int val=1;val<10;++val)
			{
				if ( (this.getRegionDeCase(i, j).regionValide(i, j, val)) && (this.ligneValide(i, j, val) && (this.colonneValide(i, j, val))))
				{
					this.setCaseNum(i, j, val);
					if(resoudre(i,j+1))
						return true;
				}
			}
			this.setCaseNum(i, j, 0);
			System.out.println(this);
			return false;
		}
		
		// Afficher une grille
		
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
					sb.append(this.getCaseNum(i, j)+" ");
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
