package com.daviancorp.android.catchgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.daviancorp.framework.Game;
import com.daviancorp.framework.Graphics;
import com.daviancorp.framework.Image;
import com.daviancorp.framework.Input.TouchEvent;
import com.daviancorp.framework.Screen;

public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver
	}
	
	private static final String TAG = "GameScreen";

	GameState state = GameState.Ready;

	private static final int NUM_COLUMNS = 4;
	private static final int SPAWN_SPEED_GAP = 50;
	private static final int MIN_SPAWN_SPEED = 50;
	private static final int MAX_SPAWN_SPEED = 100;

	private static final int OBJECT_SPEED_GAP = 5;
	private static final int MIN_OBJECT_SPEED = 5;
	
	private static final int GOOD_TO_BAD_RATIO = 80;
	private static final int GOOD_TO_GREAT_RATIO = 80;
	
	private static final String FILENAME = "catchgame.json";
	
	// Variable Setup
	private Image good, great, bad;
	private Image mediaOption;

	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private GameSave gameSave;
	
	private Random rand = new Random();
	private float timer;
	private int score, highscore;
	private boolean mediaOn;
	
	private Paint paint, paint2;

	public GameScreen(Game game) {
		super(game);

		// Initialize game objects here
		good = Assets.good;
		great = Assets.great;
		bad = Assets.bad;

		// Defining a paint object
		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);

		paint2 = new Paint();
		paint2.setTextSize(70);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);

		timer = MIN_SPAWN_SPEED + SPAWN_SPEED_GAP * rand.nextFloat();

		gameSave = new GameSave((Context) game, FILENAME);
		
		mediaOn = gameSave.loadMediaOption();
		getMedia();
		
		newGame();
	}
	
	public void newGame() {
		score = 0;
		highscore = gameSave.loadHighScore();
		gameObjects.clear();
		
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		
		// Check if player pressed mute/unmute button
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				
				// Pressed mute/unmute button
				if (inBounds(event, 415, 0, 65, 65)) {
					toggleMedia();
					saveGame();
				}
			}
		}

		// We have four separate update methods in this example.
		// Depending on the state of the game, we call different update methods.
		// Refer to Unit 3's code. We did a similar thing without separating the
		// update methods.

		if (state == GameState.Ready)
			updateReady(touchEvents);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.Paused)
			updatePaused(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
	}

	private void updateReady(List<TouchEvent> touchEvents) {

		// This example starts with a "Ready" screen.
		// When the user touches the screen, the game begins.
		// state now becomes GameState.Running.
		// Now the updateRunning() method will be called!

		if (touchEvents.size() > 0)
			state = GameState.Running;
	}

	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {

		// This is identical to the update() method from our Unit 2/3 game.

		// 1. All touch input is handled here:
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				
				// Pressed Pause button
				if (inBounds(event, 0, 0, 65, 65)) {
					state = GameState.Paused;
				}
				
				// Pressed a game object
				for (int j = gameObjects.size()-1; j >= 0; j--) {
					GameObject g = gameObjects.get(j);
					
					if (inBounds(event, g.getX(), g.getY(), g.getWidth(), g.getHeight())) {

						if (g instanceof GoodObject) {
							score += g.getPoints() + ((840 - g.getY()) / 10);
							gameObjects.remove(j);
							objectSound(g.getX());
						}
						else if (g instanceof GreatObject) {
							score += g.getPoints() + 2 * ((840 - g.getY()) / 10);
							gameObjects.remove(j);
							objectSound(g.getX());
						}
						else if (g instanceof BadObject) {
							state = GameState.GameOver;
							checkScore();
						}
					}		
				}
			}

			if (event.type == TouchEvent.TOUCH_UP) {
				
			}
		}

		// 2. Check miscellaneous events like death:


		// 3. Call individual update() methods here.
		// This is where all the game updates happen.

		// Check if any good object reached the bottom
		for (int j = 0; j < gameObjects.size(); j++) {
			GameObject g = gameObjects.get(j);
			g.update();
			
			if (((g instanceof GoodObject) || (g instanceof GreatObject)) && (g.getY() > 800)) {
				state = GameState.GameOver;
				checkScore();
			}
		}
		
		// Check if need to spawn objects
		if (timer <= deltaTime) {
			timer = MIN_SPAWN_SPEED + SPAWN_SPEED_GAP * rand.nextFloat();
			spawnObjects();
		}
		else {
			timer -= deltaTime;
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

	private void updatePaused(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				
				if (inBounds(event, 50, 210, 380, 90)) {
					resume();
				}

				else if (inBounds(event, 110, 410, 250, 90)) {
					nullify();
 					goToMenu();
				}
			}
		}

	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 110, 390, 260, 50)) {
					nullify();
					game.setScreen(new MainMenuScreen(game));
					return;
				}
				else if (inBounds(event, 200, 460, 90, 50)) {
					Log.d(TAG, "in");
					newGame();
					state = GameState.Running;
				}
			}
		}

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		
		// First draw the game elements.
		// Example:
		// g.drawImage(Assets.background, 0, 0);
		// g.drawImage(Assets.character, characterX, characterY);

		g.drawImage(Assets.background, 0, 0);
		
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject o = gameObjects.get(i);
			
			if (o instanceof GoodObject) {
				g.drawImage(good, o.getX(), o.getY());
			}
			else if (o instanceof GreatObject){
				g.drawImage(great, o.getX(), o.getY());
			}
			else if (o instanceof BadObject) {
				g.drawImage(bad, o.getX(), o.getY());
			}
		}

		// Secondly, draw the UI above the game elements.
		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running)
			drawRunningUI();
		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawGameOverUI();

		g.drawString("Score: " + Integer.toString(score), 240, 50, paint);
		g.drawImage(mediaOption, 415, 0, 0, 0, 65, 65);
	}

	private void nullify() {

		// Set all variables to null. You will be recreating them in the
		// constructor.
	
		paint = null;
		paint2 = null;
		good = null;
		great = null;
		bad = null;
		gameObjects = null;

		// Call garbage collector to clean up memory.
		System.gc();

	}

	private void drawReadyUI() {
		Graphics g = game.getGraphics();

		g.drawARGB(155, 0, 0, 0);
		g.drawString("Tap to Start.", 240, 400, paint);
	}

	private void drawRunningUI() {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.pause, 0, 0, 0, 0, 65, 65);
	}

	private void drawPausedUI() {
		Graphics g = game.getGraphics();
		// Darken the entire screen so you can display the Paused screen.
		g.drawARGB(155, 0, 0, 0);
		g.drawString("Resume", 240, 300, paint2);
		g.drawString("Menu", 240, 500, paint2);
	}

	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
		g.drawARGB(155, 0, 0, 0);
		//g.drawRect(0, 0, 801, 1201, Color.BLACK);
		g.drawString("High Score: " + Integer.toString(highscore), 240, 95, paint);
		g.drawString("GAME OVER.", 240, 350, paint2);
		g.drawString("Tap here to return", 240, 430, paint);
		g.drawString("Retry", 240, 500, paint);
	}

	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;

	}

	@Override
	public void resume() {
		if (state == GameState.Paused)
			state = GameState.Running;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		pause();
	}

	private void goToMenu() {
		game.setScreen(new MainMenuScreen(game));

	}
	
	/* Used to spawn game objects during the game
	 */
	private void spawnObjects() {
		
		// Get the column position to spawn
		int column = rand.nextInt(NUM_COLUMNS);
		int posX = 30 + 115 * column;
		
		// Get the object's speed
		int oSpeed = MIN_OBJECT_SPEED + rand.nextInt(OBJECT_SPEED_GAP);
		
		// Get the type of object
		GameObject o;
		if (rand.nextInt(100) < GOOD_TO_BAD_RATIO ) {
			if (rand.nextInt(100) < GOOD_TO_GREAT_RATIO) {
				o = new GoodObject(posX, 75, 75, oSpeed);
			}
			else {
				o = new GreatObject(posX, 75, 75, oSpeed);
			}
		} 
		else {

			o = new BadObject(posX, 75, 75, oSpeed);
		}
		
		gameObjects.add(o);
	}
	
	/* Play the sound when a game object is clicked
	 */
	private void objectSound(int i) {
		if (mediaOn) {
			switch (i) {
				case (30 + 115 * 0):
					Assets.item1.play(0.5f);
					break;
				case (30 + 115 * 1):
					Assets.item2.play(0.5f);
					break;
				case (30 + 115 * 2):
					Assets.item3.play(0.5f);
					break;
				case (30 + 115 * 3):
					Assets.item4.play(0.85f);
					break;		
			}
		}
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
	
	/* Check if score beats high score
	 */
	private void checkScore() {
		if (score > highscore) {
			highscore = score;
			saveGame();
		}
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
