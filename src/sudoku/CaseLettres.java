package sudoku;
import javax.swing.JTextField;
public class CaseLettres

	{
		// valeur de la case
		public char caractere;
		
		// etat de la case
		public boolean fixe;
		
		//Constructeur par defaut, cree une case vide, et non fixe
		public CaseLettres()
		{	
			caractere = 0;
			fixe = false;
		}
		
		//Constructeur, cree une case dont la valeur est n, et non fixe
		public CaseLettres(char n)
		{
			caractere = n;
			fixe = false;
		}
		
		//Constructeur, cree une case dont la valeur est n, dont l'etat est f
		public CaseLettres(char n, boolean f)
		{
			caractere = n;
			fixe = f;
		}
		
		//Constructeur, cree une case vide, dont l'etat est f
		public CaseLettres(boolean f)
		{
			caractere = 0;
			fixe = f;
		}
		
		//Accesseur return La valeur de la case
		public char getChar()
		{
			return caractere;
		}
		
		//Modifieur de num
		public void setChar(char n)
		{caractere = n;}
		
		//Accesseur de fixe return L'etat de la case
		public boolean getFixe()
		{return fixe;}
		
		//Modifieur de fixe
		public void setFixe(boolean f)
		{fixe = f;}
		
		//Affiche une case
		public String toString()
		{
			StringBuffer sb = new StringBuffer();
			sb.append(caractere);return sb.toString();}}
