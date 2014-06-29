package com.daviancorp.androidgame.samplegame;

import com.daviancorp.framework.Screen;
import com.daviancorp.framework.implementation.AndroidGame;

public class SampleGame extends AndroidGame {
    @Override
    public Screen getInitScreen() {
        return new LoadingScreen(this); 
    }

    @Override
    public void onBackPressed() {
    	getCurrentScreen().backButton();
    }
}
