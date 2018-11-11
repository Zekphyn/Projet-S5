package sudoku;
import javax.swing.JFileChooser;

import java.util.ArrayList;
import java.io.*;
public class Grille {
	int grille[][]=new int[9][9];
	ArrayList<Position> tourJoues=new ArrayList<Position>(100);
	public boolean estDansLigne (int chiffre ,int ligne )
	{
		
		for(int i=0;i<9;i++)
			if(this.grille[ligne][i]==chiffre)
				return true;
		return false;
	}
	
	public boolean estDansColonne (int chiffre ,int colonne )
	{
		for(int i=0;i<9;i++)
			if(this.grille[i][colonne]==chiffre)
			return true;
		return false;
	}
	
	public boolean estDansRegion (int chiffre , Position pos  )
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
				if(this.grille[region.ligne+i][region.colonne+j]==chiffre)
					return true;
			}
		}
		return false ;
	}
	
	
	
	public void lectureGrille()
	{    BufferedReader reader ;
		try {
			reader = new BufferedReader(new FileReader(
					"src/sudoku/g.txt"));
			
			String line = reader.readLine();
			int i=-1;
			
			while (line != null) {
				i++;
				
					for(int j=0;j<9;j++)
					{	
						char c=line.charAt(j);
						String str=Character.toString(c);
						grille[i][j] =Integer.parseInt(str)  ;
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
				if(this.grille[i][j]!=0)
					System.out.print(this.grille[i][j]);
				else System.out.print(".");
			}
			System.out.println();
		}
	}
	
	/** renvoie la  ligne de la case à droite de  (i,j) 
    private int ligneSuivante(int i,int j){
	if (j==7) return i+1;
	else return i;
    }

    /* renvoie la  colonne de la case à droite de  (i,j) 
    private int colonneSuivante(int i,int j){
	if (j==8) return 0;
	else return j+1;
    }
    **/
	
/**
 boolean placer(int i, int j) {
        // on a dépassé la dernière ligne
	if (i==8) return true;
        //  la grille a une valeur initiale, on continue sur la prochaine case
	if (grille[i][j]!=0) return placer(ligneSuivante(i,j),colonneSuivante(i,j));
	int k=1;
	boolean possible = false;
	Position pos=new Position();

        // on essaie toutes les valeurs possibles 
	while (!possible && k<=8) {
		pos.ligne=i;
		pos.colonne=j;
		
	    if (!estDansRegion(k,pos,grille)&& (!estDansLigne(k,i,grille)) && (!estDansColonne(k,j,grille))){
                // on place la valeur k 
		grille[i][j]=k;
                // en choisissant k on peut placer les autres lignes et colonnes
		if (placer(ligneSuivante(i,j),colonneSuivante(i,j)))
		   possible = true;
                // en choisissant k on n'a pas pu finir de remplir la grille, on passe au k suivant
		else {
		    grille[i][j]=0;
		    k++;
		}
            // la valeur k n'est pas possible pour (i,j), on passe au k suivant
	    } else k++;
	}
	return possible;
    }
	
	**/
    
    boolean jouerTour(int x , int y , int valeur)
    {
    	Position pos = new Position();
    	pos.ligne=x-1;
    	pos.colonne=y-1;
    	if ( !(this.estDansLigne(valeur, x-1)) && !(this.estDansColonne(valeur, y-1)) && !(this.estDansRegion(valeur, pos)) )
    	{
    		this.grille[x-1][y-1]=valeur;
    		this.tourJoues.add(pos);
    		return true;
    	}
    	else if (this.estDansLigne(valeur, x-1))
    		System.out.println("Verifier ligne");
    	else if (this.estDansColonne(valeur, y-1))
    		System.out.println("Verifier colonne");
    	else if (this.estDansRegion(valeur, pos) )System.out.println("Verifier region");
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
    				this.grille[x-1][y-1]=0;
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
    					if(jouerTour(i+1,j+1,k))
    						{	
    							System.out.println("Joué ligne "+i+" colonne "+j+" la valeur "+k);
    							return true;	
    						}
    				}
    				
    			}
    		}
    	System.out.println("Pas de possibilites , veuillez enlever une valeur pour debloquer le jeu");
    	return false;
    }
    
	
	
	
	
}
