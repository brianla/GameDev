package com.daviancorp.android.tiletapper;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.daviancorp.android.tiletapper.GameScreen.GameState;
import com.daviancorp.framework.Game;
import com.daviancorp.framework.Graphics;
import com.daviancorp.framework.Image;
import com.daviancorp.framework.Input.TouchEvent;
import com.daviancorp.framework.Screen;

public class MainMenuScreen extends Screen {
	enum HomeState {
		Home, Option
	}

	HomeState state = HomeState.Home;
	
	/* TODO */
	private static final String TAG = "MainMenuScreen";

	private static final int TEXT_COLOR = 0xff6B6564;
	private static final int ORANGE_COLOR = 0xffff8a00;

	private boolean switchUpdate;
	private Shared shared;
	private Paint paint;

	public MainMenuScreen(Game game) {
		super(game);
		
		switchUpdate = true;
		shared = Shared.getInstance();

		shared.setGameSave(new GameSave((Context) game, Assets.FILENAME));
		shared.setHighscore(shared.getGameSave().loadHighScore());
		shared.setMusicOn(shared.getGameSave().loadMusicOption());
		shared.setSoundOn(shared.getGameSave().loadSoundOption());
		shared.getMusic();

		paint = new Paint();
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(TEXT_COLOR);
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		
		if (state == HomeState.Home)
			updateHome(touchEvents, deltaTime);
		if (state == HomeState.Option)
			updateOption(touchEvents, deltaTime);
	}

	public void updateHome(List<TouchEvent> touchEvents, float deltaTime) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			if (i < touchEvents.size()) {
				TouchEvent event = touchEvents.get(i);

				/* TODO */
				if (switchUpdate) {
					if (event.type == TouchEvent.TOUCH_UP) {
						// Pressed Play button
						if (inBounds(event, 250, 700, 300, 150)) {
							game.setScreen(new GameScreen(game));
						}
	
						// Pressed Options button
						else if (inBounds(event, 200, 850, 400, 150)) {
							state = HomeState.Option;
							switchUpdate = false;
						}
					}
				} 
				else {
					if (event.type == TouchEvent.TOUCH_DOWN) {
						switchUpdate = true;
					}
				}
			}
		}
	}

	public void updateOption(List<TouchEvent> touchEvents, float deltaTime) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			if (i < touchEvents.size()) {
				TouchEvent event = touchEvents.get(i);

				if (switchUpdate) {
					/* TODO */
					if (event.type == TouchEvent.TOUCH_UP) {
						// Toggle Music
						if (inBounds(event, 50, 600, 700, 150)) {
							shared.toggleMusic();
						}
	
						// Toggle Sound
						else if (inBounds(event, 50, 750, 700, 150)) {
							shared.toggleSound();
						}
						
						// Pressed Back button
						else if (inBounds(event, 250, 900, 300, 150)) {
							state = HomeState.Home;
							switchUpdate = false;
						}
					}
				}
				else {
					if (event.type == TouchEvent.TOUCH_DOWN) {
						switchUpdate = true;
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
		paint.setTextSize(200);
		paint.setColor(Color.WHITE);
		
		g.drawImage(Assets.background, 0, 0);
		g.drawRect(50, 100, 700, 450, Color.argb(255, 33, 33, 33));
		g.drawString("TILE", 400, 300, paint);
		g.drawString("TAPPER", 400, 500, paint);
		
		if (state == HomeState.Home)
			drawHomeUI();
		if (state == HomeState.Option)
			drawOptionUI();
	}

	private void drawHomeUI() {
		Graphics g = game.getGraphics();
		paint.setTextSize(100);
		paint.setColor(TEXT_COLOR);
		
		g.drawString("Play", 400, 800, paint);
		g.drawString("Options", 400, 950, paint);

		// TODO
//		 g.drawRect(250, 700, 300, 150, Color.argb(100, 50, 0, 0));
//		 g.drawRect(200, 850, 400, 150, Color.argb(100, 0, 50, 0));
	}

	private void drawOptionUI() {
		Graphics g = game.getGraphics();
		paint.setTextSize(100);
		paint.setColor(TEXT_COLOR);

		// Drawing Music option
		g.drawString("Music:", 200, 700, paint);

		paint.setColor(ORANGE_COLOR);
		if(shared.isMusicOn()) {
			g.drawString("On", 450, 700, paint);

			paint.setColor(TEXT_COLOR);
			g.drawString("Off", 650, 700, paint);
		}
		else {
			g.drawString("Off", 650, 700, paint);
			
			paint.setColor(TEXT_COLOR);
			g.drawString("On", 450, 700, paint);
		}
		g.drawString("/", 550, 700, paint);
		
		// Drawing Sound option
		g.drawString("Sound:", 200, 850, paint);
		
		paint.setColor(ORANGE_COLOR);
		if(shared.isSoundOn()) {
			g.drawString("On", 450, 850, paint);

			paint.setColor(TEXT_COLOR);
			g.drawString("Off", 650, 850, paint);
		}
		else {
			g.drawString("Off", 650, 850, paint);
			
			paint.setColor(TEXT_COLOR);
			g.drawString("On", 450, 850, paint);
		}
		g.drawString("/", 550, 850, paint);
		
		// Drawing Back option
		paint.setTextSize(100);
		g.drawString("Back", 400, 1000, paint);

		// TODO
//		 g.drawRect(50, 600, 700, 150, Color.argb(100, 50, 0, 0));
//		 g.drawRect(50, 750, 700, 150, Color.argb(100, 0, 50, 0));
//		 g.drawRect(250, 900, 300, 150, Color.argb(100, 0, 00, 50));
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		paint = null;
	}

	@Override
	public void backButton() {
		android.os.Process.killProcess(android.os.Process.myPid());

	}
}
