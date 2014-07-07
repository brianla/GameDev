package com.daviancorp.android.catchgame;

import java.util.List;

import android.content.Context;

import com.daviancorp.framework.Game;
import com.daviancorp.framework.Graphics;
import com.daviancorp.framework.Image;
import com.daviancorp.framework.Screen;
import com.daviancorp.framework.Input.TouchEvent;

public class MainMenuScreen extends Screen {

	private static final String FILENAME = "catchgame.json";
	
	private GameSave gameSave;
	private int highscore;
	private Image mediaOption;
	private boolean mediaOn;
	
    public MainMenuScreen(Game game) {
        super(game);
        
		gameSave = new GameSave((Context) game, FILENAME);
		highscore = gameSave.loadHighScore();
        mediaOn = gameSave.loadMediaOption();
		getMedia();
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
            	// Pressed Play button
                if (inBounds(event, 175, 530, 130, 70)) {
                    game.setScreen(new GameScreen(game));
                }
                
				// Pressed mute/unmute button
                else if (inBounds(event, 415, 0, 65, 65)) {
                	toggleMedia();
					saveGame();
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
		g.drawImage(mediaOption, 415, 0, 0, 0, 65, 65);
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
    
	/* Get the info for muted/unmuted media
	 */
	private void getMedia() {
		if (mediaOn == true) {
			mediaOption = Assets.mediaPlay;
			
			if (!Assets.theme.isPlaying()) {
				Assets.theme.play();
			}
		} 
		else {
			mediaOption = Assets.mediaMute;
			
			if (Assets.theme.isPlaying()) {
				Assets.theme.stop();
			}
		}
	}
	
	/* Toggle the media option
	 */
	private void toggleMedia() {
		mediaOn = !mediaOn;
		getMedia();
	}
	
	/* Save game info to system
	 */
	private boolean saveGame() {
		try {
			gameSave.saveCatchGame(highscore, mediaOn);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
