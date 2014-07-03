package com.daviancorp.android.catchgame;

import com.daviancorp.framework.Image;
import com.daviancorp.framework.Music;
import com.daviancorp.framework.Sound;

public class Assets {
    
    public static Image menu, splash, background;	// Main images
    public static Image good, bad;			// Game objects
    public static Image pause;				// Game buttons
    public static Sound coin;
    public static Music theme;
    
    public static void load(ActivityGame activityGame) {
        theme = activityGame.getAudio().createMusic("menutheme.mp3");
        theme.setLooping(true);
        theme.setVolume(0.85f);
        theme.play();
    }

}
