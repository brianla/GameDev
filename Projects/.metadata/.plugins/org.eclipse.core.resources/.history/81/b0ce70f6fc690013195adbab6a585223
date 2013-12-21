package breakout;

import javax.swing.ImageIcon;


public class Ball extends Sprite implements Commons {

   private int xdir;
   private int ydir;

   protected String ball = "ball.png";

   public Ball() {

     xdir = 0;
     ydir = 1;

     ImageIcon ii = new ImageIcon(this.getClass().getResource(ball));
     image = ii.getImage();

     width = image.getWidth(null);
     heigth = image.getHeight(null);

     resetState();
    }


    public void move()
    {
      x += xdir;
      y += ydir;

      if (x == 0) {
        setXDir(1);
      }

      if (x == BALL_RIGHT) {
        setXDir(-1);
      }

      if (y == 0) {
        setYDir(-ydir);
      }
    }

    public void resetState() 
    {
      x = 150;
      y = 200;
    }

    public void setXDir(int x)
    {
      xdir = x;
    }

    public void setYDir(int y)
    {
      ydir = y;
    }

    public int getYDir()
    {
      return ydir;
    }
}