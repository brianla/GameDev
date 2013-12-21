package breakout;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class PaddleSmall extends Paddle {

    String paddle = "paddle.png";


    public PaddleSmall() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(paddle));
        image = ii.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);

        resetState();
    }
}