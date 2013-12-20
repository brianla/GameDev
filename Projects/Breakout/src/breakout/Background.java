package breakout;

import javax.swing.ImageIcon;

public class Background extends Sprite{

    String bg = "Background breakout1.png";
    
    public Background() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(bg));
        image = ii.getImage();

        width = image.getWidth(null);
        heigth = image.getHeight(null);

      }
}
