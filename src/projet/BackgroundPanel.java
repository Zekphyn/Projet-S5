package projet;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
	 
		private Image img;
	 
		public BackgroundPanel(String img) {
			this(new ImageIcon(img).getImage());
		}
	 
		public BackgroundPanel(Image img) {
			this.img = img;
		}
	 
		public void paintComponent(Graphics g) {
			g.drawImage(img, 0, 0, this);
		}
	}

