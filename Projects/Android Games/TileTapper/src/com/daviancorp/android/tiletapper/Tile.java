package com.daviancorp.android.tiletapper;

public class Tile {
	public static final int WHITE = 0;
	public static final int BLACK = 1;
	public static final int RED = 2;
	public static final int YELLOW = 3;
	
	private int type;
	private float duration;
	
	public Tile(int type, float duration) {
		this.type = type;
		this.duration = duration;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}
}
