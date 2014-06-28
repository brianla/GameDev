package kiloboltgame;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Robot {

	// Constants are Here
	final int JUMPSPEED = -15;
	final int MOVESPEED = 5;

	private int centerX = 100;
	private int centerY = 377;
	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducked = false;
	private boolean readyToFire = true;

	private static Background bg1 = StartingClass.getBg1();
	private static Background bg2 = StartingClass.getBg2();

	private int speedX = 0;
	private int speedY = 0;
	
	public static Rectangle rect = new Rectangle(0, 0, 0, 0);
	public static Rectangle rect2 = new Rectangle(0, 0, 0, 0);
	public static Rectangle rect3 = new Rectangle(0, 0, 0, 0);
	public static Rectangle rect4 = new Rectangle(0, 0, 0, 0);
	public static Rectangle yellowRed = new Rectangle(0, 0, 0, 0);
	public static Rectangle footleft = new Rectangle(0, 0, 0, 0);
	public static Rectangle footright = new Rectangle(0, 0, 0, 0);

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	public void update() {

		// Moves Character or Scrolls Background accordingly.
		if (speedX < 0) {
			centerX += speedX;
		}
		if (speedX == 0 || speedX < 0) {
			bg1.setSpeedX(0);
			bg2.setSpeedX(0);
		}
		if (centerX <= 200 && speedX > 0) {
			centerX += speedX;
		}
		if (speedX > 0 && centerX > 200) {
			bg1.setSpeedX(-MOVESPEED / 5);
			bg2.setSpeedX(-MOVESPEED / 5);
		}

		// Updates Y Position
		centerY += speedY;

		// Handles Jumping
		speedY += 1;
		
		if (speedY > 3) {
			jumped = true;
		}

		// Prevents going beyond X coordinate of 0
		if (centerX + speedX <= 60) {
			centerX = 61;
		}
		
		rect.setRect(centerX - 34, centerY - 63, 68, 63);
		rect2.setRect(rect.getX(), rect.getY() + 63, 68, 64);
		rect3.setRect(rect.getX() - 26, rect.getY() + 32, 26, 20);
		rect4.setRect(rect.getX() + 68, rect.getY() + 32, 26, 20);
		yellowRed.setRect(centerX - 110, centerY - 110, 180, 180);
		footleft.setRect(centerX - 50, centerY + 20, 50, 15);
		footright.setRect(centerX, centerY + 20, 50, 15);
	}

	public void moveRight() {
		if (ducked == false) {
			speedX = 6;
		}
	}

	public void moveLeft() {
		if (ducked == false) {
			speedX = -6;
		}
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}

	public void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 0;
		}
		
		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}
		
		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}
	}

	public void jump() {
		if (jumped == false) {
			speedY = -15;
			jumped = true;
		}

	}
	
	public void shoot() {
		if(readyToFire) {
			Projectile p = new Projectile(centerX + 50, centerY - 25);
			projectiles.add(p);
		}
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public boolean isJumped() {
		return jumped;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isDucked() {
		return ducked;
	}

	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}

	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public boolean isReadyToFire() {
		return readyToFire;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}

}