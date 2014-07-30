package com.daviancorp.android.tiletapper;

import com.daviancorp.framework.Image;
import com.daviancorp.framework.Music;
import com.daviancorp.framework.Sound;


public class Assets {
    
	/* TODO */
	public static final String FILENAME = "tilegame.json";
	
    public static Image splash, background;	// Main images
	public static Image black_tile, white_tile, red_tile, yellow_tile, orange_tile;		// Game objects
	public static Image black_tile_1, black_tile_2, black_tile_3, black_tile_4,
	 			black_tile_5, black_tile_6, black_tile_7, black_tile_8, black_tile_9,
	 			black_tile_10, black_tile_11, black_tile_12, black_tile_13, black_tile_14,
	 			black_tile_15, black_tile_16, black_tile_17;
	public static Image board, board_paused;

    public static Image pause;						// Game buttons
    public static Sound tap, lose;
    public static Music theme;
    
    public static void load(ActivityGame activityGame) {
    	
        theme = activityGame.getAudio().createMusic("bgmusic.mp3");
        theme.setLooping(true);
        theme.setVolume(0.5f);
    }

}
