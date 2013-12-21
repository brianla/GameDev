package breakout;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.lang.Math;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Board extends JPanel implements Commons {

    Image ii;
    Timer timer, gameTimer;
    String message = "Game Over";
    Ball ball;
    Paddle paddle;
    PaddleSmall paddleSmall;
    PaddleBig paddleBig;
    Brick bricks[];
    Item items[];
    Background bg;
    
    boolean ingame = true, blinking = false, fast = false, slow = false;
    boolean skip = false;
    int lives, numBlinks;
    int gameTime, bigTime, invertTime, blinkTime, fastTime, slowTime;
    int timerId;


    public Board() {

        addKeyListener(new TAdapter());
       
        setFocusable(true);

        bricks = new Brick[30];
        items = new Item[30];

        setDoubleBuffered(true);
        timer = new Timer();
        gameTimer = new Timer();
        
        gameTimer.scheduleAtFixedRate(new ScheduleTask1(), 2000, 1000);
        timer.scheduleAtFixedRate(new ScheduleTask(), 1000, 10);
    }

        public void addNotify() {
            super.addNotify();
            gameInit();
        }

    public void gameInit() {

        ball = new Ball();
        paddleSmall = new PaddleSmall();
        paddleBig = new PaddleBig();
        paddle = paddleSmall;
        bg = new Background();
        lives = 3;
        numBlinks = 0;

        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                bricks[k] = new Brick(j * 40 + 30, i * 10 + 50);
                if (Math.random() <= 0.5){
                	items[k] = new Item(
                		bricks[k].getX()+(bricks[k].getWidth()/2)-5,
                		bricks[k].getY());
                	
                }
                else{
                	items[k] = null;
                }
             
                k++;
      
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        if (ingame) {
        	g.drawImage(bg.getImage(), bg.getX(), bg.getY(),
        				bg.getWidth(), bg.getHeight(), this);
        	
        	if(!blinking){
	            g.drawImage(ball.getImage(), ball.getX(), ball.getY(),
	                        ball.getWidth(), ball.getHeight(), this);
        	}
            
            g.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
                        paddle.getWidth(), paddle.getHeight(), this);

            for (int i = 0; i < 30; i++) {
                if (!bricks[i].isDestroyed()){
                    g.drawImage(bricks[i].getImage(), bricks[i].getX(),
                                bricks[i].getY(), bricks[i].getWidth(),
                                bricks[i].getHeight(), this);
                }
                else{
                	if (!(items[i] == null)){
    	                if (!items[i].isDestroyed()){
    	                    g.drawImage(items[i].getImage(), items[i].getX(),
    	                                items[i].getY(), items[i].getWidth(),
    	                                items[i].getHeight(), this);
    	            	}
                	}
                }
            }

            
            g.setColor(Color.black);
            g.drawString("Lives left: " + lives, 5, 15);
            g.drawString("Time: " + gameTime, 5, 30);
        } 
        
        
        else {

            Font font = new Font("Verdana", Font.BOLD, 18);
            FontMetrics metr = this.getFontMetrics(font);

            g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawString(message,
                         (Commons.WIDTH - metr.stringWidth(message)) / 2,
                         Commons.WIDTH / 2);
        }


        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
        }
    }
    
    class ScheduleTask1 extends TimerTask {

        public void run() {
        	gameTime++;
        	if(paddle instanceof PaddleBig){
        		if(gameTime >= bigTime + 10){
            		paddleSmall.setX((paddleBig.getX()+paddleBig.getWidth()/2)-
            	    (paddleSmall.getWidth()/2));
            	    paddle = paddleSmall;
        		}
        	}
        	if(gameTime >= invertTime + 10){
        		paddle.setInverted(false);
        	}
        	if(numBlinks > 0){
        		if((gameTime - blinkTime) % 2 == 0){
        			blinking = true;
        		}
        		else{
        			blinking = false;
        		}
        		numBlinks--;
        	}
        	if(gameTime >= fastTime + 10){
        		fast = false;
        	}
        	if(gameTime >= slowTime + 10){
        		slow = false;
        	}
        }
    }


    class ScheduleTask extends TimerTask {

        public void run() {
        	if (fast == slow){
        		ball.move();
        	}
        	else{
	        	if (slow){
	        		if(skip){
	        			skip = false;
	        		}
	        		else{
	        			ball.move();
	        			skip = true;
	        		}
	        	}
	            if (fast){
	            	ball.move();
	            	ball.move();
	            }
        	}
            paddle.move();
            checkCollision();
            repaint();
            
            for(int i = 0; i < 30; i++){
            	if(bricks[i].isDestroyed()){
	            	if (!(items[i] == null)){
		                if (!items[i].isDestroyed()){
		                	items[i].move();
		                }
	            	}
            	}
            }
            
            if(lives <= 0){
            	stopGame();
            }
        }
    }

    public void stopGame() {
        ingame = false;
        timer.cancel();
    }


    public void checkCollision() {

        if (ball.getRect().getMaxY() > Commons.BOTTOM) {
        	lives--;
        	if(lives <= 0){
        		stopGame();
        	}
        	else{
        		
        		ball.setXDir(0);
        		ball.setYDir(1);
        		ball.resetState();
        		
        	}
        }

        for (int i = 0, j = 0; i < 30; i++) {
            if (bricks[i].isDestroyed()) {
                j++;
            }
            if (j == 30) {
                message = "Victory";
                stopGame();
            }
        }

        if ((ball.getRect()).intersects(paddle.getRect())) {

            int paddleLPos = (int)paddle.getRect().getMinX();
            int ballLPos = (int)ball.getRect().getMinX();
            int section = (paddle.getWidth()/5);
            
            
            int first = paddleLPos + (section*1);
            int second = paddleLPos + (section*2);
            int third = paddleLPos + (section*3);
            int fourth = paddleLPos + (section*4);

            if (ballLPos < first) {
                ball.setXDir(-1);
                ball.setYDir(-1);
            }

            if (ballLPos >= first && ballLPos < second) {
                ball.setXDir(-1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos >= second && ballLPos < third) {
                ball.setXDir(0);
                ball.setYDir(-1);
            }

            if (ballLPos >= third && ballLPos < fourth) {
                ball.setXDir(1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos >= fourth) {
                ball.setXDir(1);
                ball.setYDir(-1);
            }


        }


        for (int i = 0; i < 30; i++) {
            if ((ball.getRect()).intersects(bricks[i].getRect())) {

                int ballLeft = (int)ball.getRect().getMinX();
                int ballHeight = (int)ball.getRect().getHeight();
                int ballWidth = (int)ball.getRect().getWidth();
                int ballTop = (int)ball.getRect().getMinY();

                Point pointRight =
                    new Point(ballLeft + ballWidth + 1, ballTop);
                Point pointLeft = new Point(ballLeft - 1, ballTop);
                Point pointTop = new Point(ballLeft, ballTop - 1);
                Point pointBottom =
                    new Point(ballLeft, ballTop + ballHeight + 1);

                if (!bricks[i].isDestroyed()) {
                    if (bricks[i].getRect().contains(pointRight)) {
                        ball.setXDir(-1);
                    }

                    else if (bricks[i].getRect().contains(pointLeft)) {
                        ball.setXDir(1);
                    }

                    if (bricks[i].getRect().contains(pointTop)) {
                        ball.setYDir(1);
                    }

                    else if (bricks[i].getRect().contains(pointBottom)) {
                        ball.setYDir(-1);
                    }

                    bricks[i].setDestroyed(true);
                }
            }
        }
        
        
        for (int i = 0; i < 30; i++) {
        	if (items[i] != null){
        		if(!(items[i].isDestroyed())){
		            if ((items[i].getRect()).intersects(paddle.getRect())) {
		                    items[i].setDestroyed(true);
		                    getEffect();
		            }
        		}
        	}
        }
    }
    
    private void getEffect(){
    	
    	double rand = Math.random();
    	
    	if(rand < 0.2)
    		speedUp();
    	else if(rand < 0.4)
    		speedDown();
    	else if(rand < 0.5)
    		addLife();
    	else if(rand < 0.6)
    		loseLife();
    	else if(rand < 0.8)
    		blink();
    	else if(rand < 0.9)
    		invert();
    	else
    		bigPaddle();
    }
   
    private void addLife(){
    	this.lives++;
    }
    
    private void loseLife(){
    	if (lives > 1){
    		this.lives--;
    	}
    }
    
    private void speedUp(){
    	fast = true;
    	fastTime = gameTime;
    }
    
    private void speedDown(){
    	skip = true;
    	slow = true;
    	slowTime = gameTime;
    }
    
    private void bigPaddle(){
    	if (paddle instanceof PaddleSmall){
    		paddleBig.setX((paddleSmall.getX()+paddleSmall.getWidth()/2)-
    		(paddleBig.getWidth()/2));
    		paddle = paddleBig;
    		
    		bigTime = gameTime;
    	}
    }
    
    private void invert(){
    	paddle.setInverted(true);
    	
    	invertTime = gameTime;
    }
    
    private void blink(){
    	numBlinks = 9;
    	blinking = true;
    	blinkTime = gameTime;
    }
    
}