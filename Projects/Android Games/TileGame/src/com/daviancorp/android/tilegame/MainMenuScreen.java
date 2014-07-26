package com.daviancorp.android.tilegame;

import java.util.List;

import android.content.Context;
import android.util.Log;

import com.daviancorp.framework.Game;
import com.daviancorp.framework.Graphics;
import com.daviancorp.framework.Image;
import com.daviancorp.framework.Input.TouchEvent;
import com.daviancorp.framework.Screen;

public class MainMenuScreen extends Screen {

	/* TODO */
	private static final String TAG = "MainMenuScreen";
	private Shared shared;
	
    public MainMenuScreen(Game game) {
        super(game);
     
        shared = Shared.getInstance();
        
		shared.setGameSave(new GameSave((Context) game, Assets.FILENAME));
		shared.setHighscore(shared.getGameSave().loadHighScore());
		shared.setMediaOn(shared.getGameSave().loadMediaOption());
		shared.getMedia();
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
        	if(i < touchEvents.size()) {
	            TouchEvent event = touchEvents.get(i);
	            
	            /* TODO */
	            if (event.type == TouchEvent.TOUCH_UP) {
	            	// Pressed Play button
	                if (inBounds(event, 160, 480, 130, 70)) {
	                    game.setScreen(new GameScreen(game));
	                }
	                
//	                // Pressed Instructions button
//	                else if (inBounds(event, 80, 555, 310, 60)) {
//	                
//	                }
//	                
					// Pressed mute/unmute button
	                else if (inBounds(event, 415, 0, 65, 65)) {
	                	shared.toggleMedia();
						shared.saveGame();
					}
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
		g.drawImage(shared.getMediaOption(), 415, 0, 0, 0, 65, 65);
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
        android.os.Process.killProcess(android.os.Process.myPid());

    }
}
