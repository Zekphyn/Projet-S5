package projet;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

// classe permettant d'avoir un background de fond prenant toute la place de l'écran dans un layout
public class BackgroundPanel extends JPanel {
	 
		private Image img;
	 
		public BackgroundPanel(String img) {
			this(new ImageIcon(img).getImage());
		}
	 
		public BackgroundPanel(Image img) {
			this.img = img;
		}
		// redessine le panel avec img afin d'avoir img en fond d'écran 
		public void paintComponent(Graphics g) {
			g.drawImage(img, 0, 0, this);
		}
	}

