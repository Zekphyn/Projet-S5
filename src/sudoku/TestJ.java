package sudoku;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class TestJ  {

	public static void main(String[] args) {
		/**Grille gr = new Grille();
		gr.lectureGrille();

	    int GAP = 1;
		JFrame f=new JFrame("Sudoku");
		
	    
	    Case[][] tfCells = new Case[9][9];
        JPanel sudokuPanel = new JPanel(new GridLayout(9, 9, GAP, GAP));
	    for (int row = 0; row < 9; ++row) {
	         for (int col = 0; col < 9; ++col) {
	            tfCells[row][col] = new Case(); // Allocate element of array
	                       // ContentPane adds JTextField
	            if (gr.grille[row][col]==0) {
	               tfCells[row][col].contenu.setText("");     // set to empty string
	               tfCells[row][col].contenu.setEditable(true);
	               tfCells[row][col].ligne=row;
	               tfCells[row][col].colonne=col;
	              // tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR);
	 
	               // Add ActionEvent listener to process the input
	              
	            } else {
	               tfCells[row][col].contenu.setText(Integer.toString(gr.grille[row][col]));
	               tfCells[row][col].contenu.setEditable(false);
	               tfCells[row][col].ligne=row;
	               tfCells[row][col].colonne=col;
	              // tfCells[row][col].setBackground(CLOSED_CELL_BGCOLOR);
	               //tfCells[row][col].setForeground(CLOSED_CELL_TEXT);
	            }
	            tfCells[row][col].contenu.setHorizontalAlignment(JTextField.CENTER);
	           // tfCells[row][col].setFont(FONT_NUMBERS);
	            sudokuPanel.add(tfCells[row][col].contenu); 
	            int h=row; 
	            int c=col;
	            tfCells[row][col].contenu.addActionListener(new ActionListener()
	            		{
	            			public void actionPerformed(ActionEvent e)
	            			{   
	            				int x = Integer.parseInt(tfCells[h][c].contenu.getText());
	            				if(x==5);
	            					tfCells[h][c].contenu.setBackground(Color.RED);
	            			}
	            		});
	         }
	      }
        f.add(sudokuPanel);

        f.pack();
	    f.setSize(400,400);

        f.setVisible(true);


	    // Partie concernant le traitement apres la saisie
        
        DocumentListener documentListener=new DocumentListener() 
        {
        	
        		public void changedUpdate(DocumentEvent documentEvent) {
        	        printIt(documentEvent);
        	      }
        	      public void insertUpdate(DocumentEvent documentEvent) {
        	        printIt(documentEvent);
        	      }
        	      public void removeUpdate(DocumentEvent documentEvent) {
        	        printIt(documentEvent);
        	      }
        	      /**public void valeurCorrecte(JTextField text)
        	      {
        	    	  String valeur=null;
        	    	  valeur=text.getText();
        	      }
        	      private void printIt(DocumentEvent documentEvent) 
        	      	{
        	          DocumentEvent.EventType type = documentEvent.getType();
        	          String typeString = null;
        	          if (type.equals(DocumentEvent.EventType.CHANGE)) {
        	            typeString = "Change"; 
        	            
        	          }  else if (type.equals(DocumentEvent.EventType.INSERT)) {
        	            typeString = "Insert";
        	            
        	          }  else if (type.equals(DocumentEvent.EventType.REMOVE)) {
        	            typeString = "Remove";
        	          }
        	          System.out.print("Type : " + typeString);
        	          
        	          Document source = documentEvent.getDocument();
        	          
        	         
        	        // Recuperer la valeur saisie dans la JTextField
        	        try {
        	        	String chaine=source.getText(0, source.getLength());
        	        	int valeur = Integer.parseInt(chaine);
        	        	System.out.println("Valeur = "+valeur+" inseree a la ligne ");
        	        	}
        	        catch (BadLocationException e) {}
        	        
        	          
        	        }
        	
        	};
        	// Ajout du Listener aux 9*9 JTextField de la sudoku
        	for(int i=0;i<9;i++)
        		for(int j=0;j<9;j++)
        			tfCells[i][j].contenu.getDocument().addDocumentListener(documentListener);
        	**/
		
		Grille g = new Grille();
		g.lectureGrille();
		g.creation();
		
		
	}
}
	    
	


