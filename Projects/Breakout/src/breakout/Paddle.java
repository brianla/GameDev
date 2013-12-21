package breakout;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class Paddle extends Sprite implements Commons{

    int dx;
    private boolean inverted;


    public void move() {
    	if(inverted) 
    		x-= dx;
    	else
    		x += dx;
        if (x <= 2) 
          x = 2;
        if (x >= Commons.WIDTH - this.width - 10)
          x = Commons.WIDTH - this.width - 10; 
    }

	public void setInverted(boolean inverted) {
		this.inverted = inverted;
	}

	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;

        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    public void resetState() {
        x = (Commons.WIDTH - this.width)/2;
        y = 360;
    }
}