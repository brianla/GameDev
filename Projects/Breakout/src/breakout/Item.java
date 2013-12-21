package breakout;

import javax.swing.ImageIcon;

public class Item extends Sprite implements Commons {
	   private int ydir;

	   protected String item = "block1.jpg";
	   boolean destroyed;

	   public Item(int x, int y) {
		 this.x = x;
		 this.y = y;
	     ydir = 2;

	     ImageIcon ii = new ImageIcon(this.getClass().getResource(item));
	     image = ii.getImage();

	     width = image.getWidth(null);
	     height = image.getHeight(null);

	    }

	    public void move()
	    {
	      y += ydir;

	      if (y == HEIGHT) {
	        setYDir(0);
	        setDestroyed(true);
	      }
	      
	    }

	    public void setYDir(int y)
	    {
	      ydir = y;
	    }

	    public int getYDir()
	    {
	      return ydir;
	    }
	    
	    public boolean isDestroyed()
	    {
	      return destroyed;
	    }

	    public void setDestroyed(boolean destroyed)
	    {
	      this.destroyed = destroyed;
	    }

}
