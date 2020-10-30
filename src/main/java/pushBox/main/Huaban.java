package pushBox.main;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Huaban extends JPanel {

	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		ImageIcon  img = new ImageIcon("src/main/java/pushBox/imgs/backgroundImg.png");
		g.drawImage(img.getImage(), 0, 0, this);
		
	}
}
