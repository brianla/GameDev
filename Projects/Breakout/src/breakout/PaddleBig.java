package breakout;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class PaddleBig extends Paddle{

    String paddle = "paddle2.png";

    public PaddleBig() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(paddle));
        image = ii.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);
        resetState();
    }
}