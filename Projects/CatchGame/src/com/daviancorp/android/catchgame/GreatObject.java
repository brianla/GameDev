package com.daviancorp.android.catchgame;


public class GreatObject extends GameObject {

	public GreatObject(int x, int width, int height, int r) {
		setX(x);
		setY(0);
		setWidth(width);
		setHeight(height);
		setPoints(100);
		
		setSpeedY(MIN_SPEED + r);
	}

}
