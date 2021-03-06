package com.daviancorp.android.catchgame;

import com.daviancorp.framework.Image;
import com.daviancorp.framework.Music;
import com.daviancorp.framework.Sound;

public class Assets {
    
    public static Image menu, splash, background;	// Main images
    public static Image good, great, bad;			// Game objects
    public static Image pause;				// Game buttons
    public static Image mediaPlay, mediaMute; 		// Media options
    public static Sound item1, item2, item3, item4, lose;
    public static Music theme;
    
    public static void load(ActivityGame activityGame) {
        theme = activityGame.getAudio().createMusic("bgmusic.mp3");
        theme.setLooping(true);
        theme.setVolume(0.5f);
        theme.play();
    }

}
