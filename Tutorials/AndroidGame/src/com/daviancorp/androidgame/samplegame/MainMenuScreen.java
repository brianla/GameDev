package com.daviancorp.androidgame.samplegame;

import java.util.List;

import com.daviancorp.framework.Game;
import com.daviancorp.framework.Graphics;
import com.daviancorp.framework.Screen;
import com.daviancorp.framework.Input.TouchEvent;


public class MainMenuScreen extends Screen {
    public MainMenuScreen(Game game) {
        super(game);
    }


    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();


        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {


                if (inBounds(event, 0, 0, 250, 250)) {
                    //START GAME
                                game.setScreen(new GameScreen(game));               
      }


            }
        }
    }


    private boolean inBounds(TouchEvent event, int x, int y, int width,
            int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }


    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.menu, 0, 0);
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
        //Display "Exit Game?" Box


    }

}
