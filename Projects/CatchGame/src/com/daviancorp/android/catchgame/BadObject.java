package com.daviancorp.android.catchgame;

import java.util.Random;

public class BadObject extends GameObject {

	public BadObject(int x, int width, int height, int r) {
		setX(x);
		setY(0);
		setWidth(width);
		setHeight(height);
		
		Random rand = new Random();
		setSpeedY(MIN_SPEED + rand.nextInt(r));
	}

}
