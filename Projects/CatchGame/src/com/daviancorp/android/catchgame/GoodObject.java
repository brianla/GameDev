package com.daviancorp.android.catchgame;

public class GoodObject extends GameObject {

	public GoodObject(int x, int width, int height, int r) {
		setX(x);
		setY(0);
		setWidth(width);
		setHeight(height);
		setPoints(1);
		
		setSpeedY(MIN_SPEED + r);
	}

}
