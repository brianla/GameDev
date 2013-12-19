package breakout;

import javax.swing.ImageIcon;

public class Brick extends Sprite {

	String brickie = "../images/brickie.png";
	boolean destroyed;
	
	public Brick(int x, int y) {
		this.x = x;
		this.y = y;
		
		ImageIcon ii = new ImageIcon(this.getClass().getResource(brickie));
		image = ii.getImage();
		
		width = image.getWidth(null);
		height = image.getHeight(null);
		
		destroyed = false;
	}
	
	public boolean isDestroyed() {
		return destroyed;
	}
	
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}
 }