package breakout;

import javax.swing.ImageIcon;

public class Ball extends Sprite implements Commons {

	private int xdir;
	private int ydir;
	
	protected String ball = "../images/ball.png";
	
	public Ball() {
		xdir = 1;
		ydir = -1;
		
		ImageIcon ii = new ImageIcon(this.getClass().getResource(ball));
		image = ii.getImage();
		
		width = image.getWidth(null);
		height = image.getHeight(null);
		
		resetState();
	}
	
	public void move() {
		x += xdir;
		y += ydir;
		
		if (x == 0) {
			setXDir(1);
		}
		
		if (x == BALL_RIGHT) {
			setXDir(-1);
		}
		
		if (y == 0) {
			setYDir(1);
		}
	}
	
	public void resetState() {
		x = 230;
		y = 355;
	}
	
	public void setXDir(int x) {
		xdir = x;
	}
	
	public void setYDir(int y) {
		ydir = y;
	}
	
	public int getYDir() {
		return ydir;
	}	
}