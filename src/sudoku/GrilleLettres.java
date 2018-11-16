package sudoku;


import java.io.*;
import java.util.ArrayList;

public class GrilleLettres {
	char grille[][]=new char[9][9];
	ArrayList<Position> tourJoues=new ArrayList<Position>(100);

	String utilisable="";
	
	
	public boolean estDansLigne (char lettre ,int ligne )
	{
		
		for(int i=0;i<9;i++)
			if(this.grille[ligne][i]==lettre)
				return true;
		return false;
	}
	
	public boolean estDansColonne (char lettre ,int colonne )
	{
		for(int i=0;i<9;i++)
			if(this.grille[i][colonne]==lettre)
			return true;
		return false;
	}
	
	public boolean estDansRegion (char lettre , Position pos  )
	{
		Position region=new Position();
		Position trans = new Position();
		trans.ligne=(pos.ligne/3)+1;
		trans.colonne=(pos.colonne/3)+1;
		region.ligne=(trans.ligne*3)-3;
		region.colonne=(trans.colonne*3)-3;
		
		
		
		
		for(int i=0; i<3 ; i++)
		{
			for(int j=0 ; j<3 ; j++)
			{
				if(this.grille[region.ligne+i][region.colonne+j]==lettre)
					return true;
			}
		}
		return false ;
	}
	
	public void lectureGrille()
	{    BufferedReader reader ;
		try {
			reader = new BufferedReader(new FileReader(
					"C:/Users/N&R/git/Projet-S5/src/sudoku/gl.txt"));

					
			
			String line = reader.readLine();
			int i=-1;
			
			while (line != null) {
				i++;
				
					for(int j=0;j<9;j++)
					{	
						char c=line.charAt(j);
						grille[i][j] =c ;
						if(  (!(this.utilisable.contains(Character.toString(c)))) && ( c!='0') ) 
							this.utilisable+=Character.toString(c);
					}
				// read next line
				line = reader.readLine();
	                
			}
			reader.close();
	                 
	                
	                
	          } catch(Exception exc) {
	              System.err.println("Ouverture impossible : fichier inexistant ou mal complété.");
	          }
	         
	       
	    
	}
	
	public void afficheGrille()
	{
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				if(this.grille[i][j]!='0')
					System.out.print(this.grille[i][j]);
				else System.out.print(".");
			}
			System.out.println();
		}
	}
	
	boolean jouerTour(int x , int y , char lettres)
    {
    	Position pos = new Position();
    	pos.ligne=x-1;
    	pos.colonne=y-1;
    	if ( !(this.estDansLigne(lettres, x-1)) && !(this.estDansColonne(lettres, y-1)) && !(this.estDansRegion(lettres, pos)) && (this.utilisable.contains(Character.toString(lettres))) )
    	{
    		this.grille[x-1][y-1]=Character.toString(lettres).toUpperCase().toCharArray()[0];
    		this.tourJoues.add(pos);
    		return true;
    	}
    	else if (this.estDansLigne(lettres, x-1))
    		System.out.println("Verifier ligne");
    	else if (this.estDansColonne(lettres, y-1))
    		System.out.println("Verifier colonne");
    	else if (this.estDansRegion(lettres, pos) )
    		System.out.println("Verifier region");
    	else if (!(this.utilisable.contains(Character.toString(lettres))))
    		System.out.println("Vous ne pouvez pas utiliser "+lettres);
    	return false ;
    }
	
	
	public void enleverValeur(int x , int y)
    {
    	Position pos=new Position();
    	pos.ligne=x-1;
    	pos.colonne=y-1;
    	boolean trouve=false;
    	for(Position posEnregistre : tourJoues)
    	{	  
    		if( (posEnregistre.ligne==pos.ligne) && ((posEnregistre.colonne==pos.colonne)) )
    	    	{
    				this.grille[x-1][y-1]='0';
    				trouve=true;
    				System.out.println("Found ! ");
    			}

    	}
    	if (!trouve)
    		System.out.println("Vous pouvez pas changer cette case ");
    	
    }
	
	boolean aideTour()
    {
    	for(int i=0;i<10;i++)
    		for(int j=0;j<10;j++)
    		{
    			if (grille[i][j]==0)
    			{
    				for(int k =1;k<10;k++)
    				{
    					if(jouerTour(i+1,j+1,this.utilisable.charAt(k)))
    						{	
    							System.out.println("Joué ligne "+i+" colonne "+j+" la valeur "+this.utilisable.charAt(k));
    							return true;	
    						}
    				}
    				
    			}
    		}
    	System.out.println("Pas de possibilites , veuillez enlever une valeur pour debloquer le jeu");
    	return false;
    }
	
	
	public String getUtilisable()
	{
		return this.utilisable;
	}
	
	

}
