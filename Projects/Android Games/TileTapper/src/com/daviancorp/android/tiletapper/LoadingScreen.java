package com.daviancorp.android.tiletapper;

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

        Assets.background = g.newImage("background.jpg", ImageFormat.RGB565);

        Assets.board = g.newImage("board.png", ImageFormat.ARGB4444);
        Assets.board_paused = g.newImage("board_paused.png", ImageFormat.ARGB4444);
        Assets.black_tile = g.newImage("black_tile.png", ImageFormat.ARGB4444);
        Assets.white_tile = g.newImage("white_tile.png", ImageFormat.ARGB4444);
        Assets.red_tile = g.newImage("red_tile.png", ImageFormat.ARGB4444);
        Assets.yellow_tile = g.newImage("yellow_tile.png", ImageFormat.ARGB4444);
        Assets.orange_tile = g.newImage("orange_tile.png", ImageFormat.ARGB4444);
        
        Assets.black_tile_1 = g.newImage("animations/black_tile_1.png", ImageFormat.ARGB4444);
        Assets.black_tile_2 = g.newImage("animations/black_tile_2.png", ImageFormat.ARGB4444);
        Assets.black_tile_3 = g.newImage("animations/black_tile_3.png", ImageFormat.ARGB4444);
        Assets.black_tile_4 = g.newImage("animations/black_tile_4.png", ImageFormat.ARGB4444);
        Assets.black_tile_5 = g.newImage("animations/black_tile_5.png", ImageFormat.ARGB4444);
        Assets.black_tile_6 = g.newImage("animations/black_tile_6.png", ImageFormat.ARGB4444);
        Assets.black_tile_7 = g.newImage("animations/black_tile_7.png", ImageFormat.ARGB4444);
        Assets.black_tile_8 = g.newImage("animations/black_tile_8.png", ImageFormat.ARGB4444);
        Assets.black_tile_9 = g.newImage("animations/black_tile_9.png", ImageFormat.ARGB4444);
        Assets.black_tile_10 = g.newImage("animations/black_tile_10.png", ImageFormat.ARGB4444);
        Assets.black_tile_11 = g.newImage("animations/black_tile_11.png", ImageFormat.ARGB4444);
        Assets.black_tile_12 = g.newImage("animations/black_tile_12.png", ImageFormat.ARGB4444);
        Assets.black_tile_13 = g.newImage("animations/black_tile_13.png", ImageFormat.ARGB4444);
        Assets.black_tile_14 = g.newImage("animations/black_tile_14.png", ImageFormat.ARGB4444);
        Assets.black_tile_15 = g.newImage("animations/black_tile_15.png", ImageFormat.ARGB4444);
        Assets.black_tile_16 = g.newImage("animations/black_tile_16.png", ImageFormat.ARGB4444);
        Assets.black_tile_17 = g.newImage("animations/black_tile_17.png", ImageFormat.ARGB4444);

        Assets.pause = g.newImage("pause.jpg", ImageFormat.RGB565);

        /* TODO */
        Assets.tap = game.getAudio().createSound("audio/tap.wav");
        Assets.lose = game.getAudio().createSound("audio/lose.wav");
        
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
