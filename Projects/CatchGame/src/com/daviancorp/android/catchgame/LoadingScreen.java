package com.daviancorp.android.catchgame;

import com.daviancorp.framework.Game;
import com.daviancorp.framework.Graphics;
import com.daviancorp.framework.Graphics.ImageFormat;
import com.daviancorp.framework.Screen;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
        Assets.background = g.newImage("background.png", ImageFormat.RGB565);
      
        Assets.good = g.newImage("good.png", ImageFormat.ARGB4444);
        Assets.great = g.newImage("great.png", ImageFormat.ARGB4444);
        Assets.bad = g.newImage("bad.png", ImageFormat.ARGB4444);

        Assets.pause = g.newImage("pause.jpg", ImageFormat.RGB565);
        
        Assets.mediaPlay = g.newImage("media_play.png", ImageFormat.RGB565);
        Assets.mediaMute = g.newImage("media_mute.png", ImageFormat.RGB565);

        Assets.coin = game.getAudio().createSound("coin1.wav");
        
        game.setScreen(new MainMenuScreen(game));

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.splash, 0, 0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {

    }

}
