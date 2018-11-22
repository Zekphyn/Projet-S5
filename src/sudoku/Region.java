package sudoku;

public class Region {
	// Tableau de 3x3 Cases
	public Case[][] region;
	
	// Constructeur par defaut avec des cases vides :
	public Region()
	{
		region=new Case[3][3];
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				region[i][j]= new Case();
			}
		}
	}
	
	// Accesseur de region qui retourne la case de coordonnees i,j dans la region
	public Case getCase(int i , int j)
	{
		return region[i][j];
	}
	
	// Modifier region
	public void setCase(int i, int j, Case c)
	{
		(region[i][j]).setNum(c.getNum());
	}
	
	// Accesseur de region qui retourne la case de coordonnees i,j dans la region
	public int getCaseNum(int i,int j)
	{
		return (region[i][j]).getNum();
	}
	
	// Modifier region
	public void setCaseNum(int i ,int j,int c)
	{
		(region[i][j]).setNum(c);
	}
	
	// Accesseur de fixe qui retourne l'etat de la case de coordonnees i,j dans la region
	public boolean getCaseFice(int i, int j)
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
				switch ((region[l][c].getNum()))
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
	
	// Verifie si la region est valide 
	public boolean regionValide(int l,int c)
	{
		if(this.getCaseNum(l, c)==0)
			return false;
		int k=0;
		int p=0;
		int temp;
		while(k<3 && p<3)
		{
			temp=(region[k][p]).getNum();
			if(temp != 0)
			{
				for(int i=0;i<3;i++)
					for(int j=0;j<3;j++)
					{
						if((region[i][j]).getNum() == temp)
							return false;
					}
			}
			k++ ;
			p++;
		}
		return true;
	}
	
	
}
