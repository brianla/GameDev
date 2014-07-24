package com.daviancorp.android.basicgame;

import com.daviancorp.framework.Game;
import com.daviancorp.framework.Graphics;
import com.daviancorp.framework.Screen;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();

        /* TODO */
//        Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
//        Assets.background = g.newImage("background.png", ImageFormat.RGB565);
//      
//        Assets.good = g.newImage("block_yellow.png", ImageFormat.ARGB4444);
//        Assets.great = g.newImage("block_green.png", ImageFormat.ARGB4444);
//        Assets.bad = g.newImage("block_red.png", ImageFormat.ARGB4444);
//
//        Assets.pause = g.newImage("pause.jpg", ImageFormat.RGB565);
//        
//        Assets.mediaPlay = g.newImage("media_play.png", ImageFormat.RGB565);
//        Assets.mediaMute = g.newImage("media_mute.png", ImageFormat.RGB565);
//
//        Assets.item1 = game.getAudio().createSound("coin1.wav");
//        Assets.item2 = game.getAudio().createSound("coin2.wav");
//        Assets.item3 = game.getAudio().createSound("coin3.wav");
//        Assets.item4 = game.getAudio().createSound("coin4.wav");
        
        game.setScreen(new MainMenuScreen(game));

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        
        /* TODO */
//        g.drawImage(Assets.splash, 0, 0);
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
