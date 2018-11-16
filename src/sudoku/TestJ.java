package sudoku;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class TestJ {

	public static void main(String[] args) {
		Grille gr = new Grille();
		gr.lectureGrille();

	    int GAP = 1;
		JFrame f=new JFrame("Sudoku");
		
	    
	    JTextField[][] tfCells = new JTextField[9][9];
        JPanel sudokuPanel = new JPanel(new GridLayout(9, 9, GAP, GAP));
	    for (int row = 0; row < 9; ++row) {
	         for (int col = 0; col < 9; ++col) {
	            tfCells[row][col] = new JTextField(1); // Allocate element of array
	                       // ContentPane adds JTextField
	            if (gr.grille[row][col]==0) {
	               tfCells[row][col].setText("");     // set to empty string
	               tfCells[row][col].setEditable(true);
	              // tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR);
	 
	               // Add ActionEvent listener to process the input
	              
	            } else {
	               tfCells[row][col].setText(gr.grille[row][col] + "");
	               tfCells[row][col].setEditable(false);
	              // tfCells[row][col].setBackground(CLOSED_CELL_BGCOLOR);
	               //tfCells[row][col].setForeground(CLOSED_CELL_TEXT);
	            }
	            tfCells[row][col].setHorizontalAlignment(JTextField.CENTER);
	           // tfCells[row][col].setFont(FONT_NUMBERS);
	            sudokuPanel.add(tfCells[row][col]); 
	            int h=row; 
	            int c=col;
	            tfCells[row][col].addActionListener(new ActionListener()
	            		{
	            			public void actionPerformed(ActionEvent e)
	            			{   
	            				int x = Integer.parseInt(tfCells[h][c].getText());
	            				if(x==5);
	            					tfCells[h][c].setBackground(Color.RED);
	            			}
	            		});
	         }
	      }
        f.add(sudokuPanel);

        f.pack();
	    f.setSize(400,400);

        f.setVisible(true);


	    // Partie concernant le traitement apres l'entre 
	    
	}

}
