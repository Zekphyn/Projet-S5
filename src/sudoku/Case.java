package sudoku;
import javax.swing.JTextField;
public class Case

	{
		/***valeur de la case*/
		public int num;
		
		/***etat de la case*/
		public boolean fixe;
		
		/***Constructeur par defaut, cree une case vide, et non fixe*/
		public Case()
		{	
			num = 0;
			fixe = false;
		}
		
		/***Constructeur, cree une case dont la valeur est n, et non fixe*/
		public Case(int n)
		{
			num = n;
			fixe = false;
		}
		
		/***Constructeur, cree une case dont la valeur est n, dont l'etat est f*/
		public Case(int n, boolean f)
		{
			num = n;
			fixe = f;
		}
		
		/***Constructeur, cree une case vide, dont l'etat est f*/
		public Case(boolean f)
		{
			num = 0;
			fixe = f;
		}
		
		/***Accesseur de num@return La valeur de la case*/
		public int getNum()
		{
			return num;
		}
		
		/***Modifieur de num*/
		public void setNum(int n)
		{num = n;}
		
		/***Accesseur de fixe@return L'etat de la case*/
		public boolean getFixe()
		{return fixe;}
		
		/***Modifieur de fixe*/
		public void setFixe(boolean f)
		{fixe = f;}
		
		/***Affiche une case*/
		public String toString()
		{
			StringBuffer sb = new StringBuffer();
			sb.append(num);return sb.toString();}}
