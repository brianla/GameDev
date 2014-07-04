package com.daviancorp.android.catchgame;

import android.graphics.Rect;

public class GameObject {
		protected final static int MIN_SPEED = 10;

		private int x, y, width, height, speedY;
		private int points;
		private Rect r = new Rect(0, 0, 0, 0);
		
		public void update() {
			y += speedY;
	        r.set(x, y, x+width, y+height);
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}
		
		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public int getSpeedY() {
			return speedY;
		}

		public void setSpeedY(int speedY) {
			this.speedY = speedY;
		}

		public int getPoints() {
			return points;
		}

		public void setPoints(int points) {
			this.points = points;
		}

}
