package com.daviancorp.android.tilegame;

import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.graphics.Paint;

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

	private static final int NUM_ROWS = 4;
	private static final int NUM_COLUMNS = 4;
	
	private static final int WIDTH_OFFSET = 40;
	private static final int HEIGHT_OFFSET = 420;
	private static final int WIDTH_GAP = 20;
	private static final int HEIGHT_GAP = 20;
	
	private static final int SPAWN_SPEED_VAR = 20;
	private static final int MIN_SPAWN_SPEED = 20;
	private static final float TILE_DURATION = 50;
	private static final int RED_TO_BLACK = 20;
	private static final int SPAWN_TWO_CHANCE = 20;
	private static final int ANIM_SPEED = 7;

	private static final int TEXT_COLOR = 0xff6B6564;
	private static final int ORANGE_COLOR = 0xffff8a00;
	
	// Variable Setup
	private Image black_tile, white_tile, red_tile, yellow_tile;
	private Image black_tile_1, black_tile_2, black_tile_3, black_tile_4,
			black_tile_5, black_tile_6, black_tile_7, black_tile_8, black_tile_9,
			black_tile_10, black_tile_11, black_tile_12, black_tile_13, black_tile_14,
			black_tile_15, black_tile_16, black_tile_17;
	private Image board, board_paused;
		
	private Tile[][] tiles;  
	private Animation[][] animations;
	private int num_black;		// TODO: Change?
	private int width, height;
	private int score, highScore;
	private boolean isHighScore;
	
	private Random rand = new Random();
	private float timer;

	private Shared shared;		// Shared variables/methods
	private Paint paint, paint2, paint3, paint4;

	public GameScreen(Game game) {
		super(game);

		// Initialize game objects here
		black_tile = Assets.black_tile;
		white_tile = Assets.white_tile;
		red_tile = Assets.red_tile;
		yellow_tile = Assets.yellow_tile;
		board = Assets.board;
		board_paused = Assets.board_paused;
		
		black_tile_1 = Assets.black_tile_1;
		black_tile_2 = Assets.black_tile_2;
		black_tile_3 = Assets.black_tile_3;
		black_tile_4 = Assets.black_tile_4;
		black_tile_5 = Assets.black_tile_5;
		black_tile_6 = Assets.black_tile_6;
		black_tile_7 = Assets.black_tile_7;
		black_tile_8 = Assets.black_tile_8;
		black_tile_9 = Assets.black_tile_9;
		black_tile_10 = Assets.black_tile_10;
		black_tile_11 = Assets.black_tile_11;
		black_tile_12 = Assets.black_tile_12;
		black_tile_13 = Assets.black_tile_13;
		black_tile_14 = Assets.black_tile_14;
		black_tile_15 = Assets.black_tile_15;
		black_tile_16 = Assets.black_tile_16;
		black_tile_17 = Assets.black_tile_17;
		
		tiles = new Tile[NUM_ROWS][NUM_COLUMNS];
		animations = new Animation[NUM_ROWS][NUM_COLUMNS];
		
		width = black_tile.getWidth();
		height = black_tile.getHeight();
		
		shared = Shared.getInstance();
		
		// Defining a paint object
		paint = new Paint();
		paint.setTextSize(70);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(TEXT_COLOR);

		paint2 = new Paint();
		paint2.setTextSize(150);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(TEXT_COLOR);

		paint3 = new Paint();
		paint3.setTextSize(50);
		paint3.setTextAlign(Paint.Align.CENTER);
		paint3.setAntiAlias(true);
		paint3.setColor(Color.BLACK);
		
		paint4 = new Paint();
		paint4.setTextSize(50);
		paint4.setTextAlign(Paint.Align.CENTER);
		paint4.setAntiAlias(true);
		paint4.setColor(Color.RED);
		
		newGame();
	}

	public void newGame() {
		score = 0;
		highScore = shared.getHighscore();
//		shared.setHighscore(0);
		isHighScore = false;
		num_black = 0;
		
		paint2.setColor(TEXT_COLOR);
		
		for (int r = 0; r < NUM_ROWS; r++) {
			for (int c = 0; c < NUM_COLUMNS; c++) {
				tiles[r][c] = new Tile(Tile.WHITE, -9999);
				animations[r][c] = null;
			}
		}
		
		timer = MIN_SPAWN_SPEED + SPAWN_SPEED_VAR * rand.nextFloat();
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
				if (inBounds(event, 
						800-shared.getMediaOption().getWidth(), 0, 
						shared.getMediaOption().getWidth(), 
						shared.getMediaOption().getHeight())) {
					shared.toggleMedia();
					shared.saveGame();
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

		// TODO - Touching media option will also start
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				state = GameState.Running;
			}
		}
	}

	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {

		// This is identical to the update() method from our Unit 2/3 game.

		// 1. All touch input is handled here:
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				
				// Pressed a tile
				for(int r = 0; r < NUM_ROWS; r++) {
					for (int c = 0; c < NUM_COLUMNS; c++) {

						if (inBounds(event, 
								WIDTH_OFFSET + WIDTH_GAP + (width + WIDTH_GAP)*r, 
								HEIGHT_OFFSET + HEIGHT_GAP + (height + HEIGHT_GAP)*c, 
								width, height)) {
							
							if (tiles[r][c].getType() == Tile.BLACK) {
								changeTile(r, c, Tile.WHITE);
								score++;
							}
							else if (tiles[r][c].getType() == Tile.RED) {
								changeTile(r, c, Tile.YELLOW);

								paint2.setColor(Color.RED);
								state = GameState.GameOver;
								
								if(isHighScore) shared.checkScore(score);
							}
						}
					}
				}
				

				// Pressed Pause button
				if (inBounds(event, 0, 0, 
						Assets.pause.getWidth(), Assets.pause.getHeight())) {
					state = GameState.Paused;
				}
				
				/* TODO */	
//				// Pressed a game object
//				for (int j = gameObjects.size()-1; j >= 0; j--) {
//					GameObject g = gameObjects.get(j);
//					
//					if (inBounds(event, g.getX(), g.getY(), g.getWidth(), g.getHeight())) {
//
//						if (g instanceof GoodObject) {
//							score += g.getPoints() + ((840 - g.getY()) / 10);
//							gameObjects.remove(j);
//							objectSound(g.getX());
//						}
//						else if (g instanceof GreatObject) {
//							score += g.getPoints() + 2 * ((840 - g.getY()) / 10);
//							gameObjects.remove(j);
//							objectSound(g.getX());
//						}
//						else if (g instanceof BadObject) {
//							state = GameState.GameOver;
//							checkScore();
//						}
//					}		
//				}
			}

			if (event.type == TouchEvent.TOUCH_UP) {
				
			}
		}

		// 2. Check miscellaneous events like death:


		// 3. Call individual update() methods here.
		// This is where all the game updates happen.

		// Check if any tile ran out of time
		for (int r = 0; r < NUM_ROWS; r++) {
			for (int c = 0; c < NUM_COLUMNS; c++) {
				Tile tile = tiles[r][c];
				
				if (tile.getDuration() < 0) {
					if (tile.getType() == Tile.BLACK) {
						changeTile(r, c, Tile.YELLOW);
						paint2.setColor(Color.RED);
						state = GameState.GameOver;
						if(isHighScore) shared.checkScore(score);
					}
					else if (tile.getType() == Tile.RED) {
						changeTile(r, c, Tile.WHITE);
					}
				}
				else {
					float prevDuration = tile.getDuration();
					tile.setDuration(prevDuration - deltaTime);
					animations[r][c].update((long) (prevDuration - tile.getDuration()) * 3);
				}
			}
		}
		
		/* TODO */
//		// Check if any good object reached the bottom
//		for (int j = 0; j < gameObjects.size(); j++) {
//			GameObject g = gameObjects.get(j);
//			g.update();
//			
//			if (((g instanceof GoodObject) || (g instanceof GreatObject)) && (g.getY() > 800)) {
//				state = GameState.GameOver;
//				checkScore();
//			}
//		}		
		
		if(score > highScore) {
			isHighScore = true;
			highScore = score;
		}
		
		// Check when to change a tile
		if (timer <= deltaTime) {
			timer = MIN_SPAWN_SPEED + SPAWN_SPEED_VAR * rand.nextFloat();
			
			newRandomTile();
//			if(rand.nextInt(100) < SPAWN_TWO_CHANCE) newRandomTile();
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
				
				// TODO
				// 'Resume'
				if (inBounds(event, 150, 475, 500, 200)) {
					resume();
				}
				
				// 'Restart'
				else if (inBounds(event, 150, 675, 500, 200)) {
 					newGame();
 					state = GameState.Ready;
				}
				
				// 'Menu'
				else if (inBounds(event, 150, 875, 500, 200)) {
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
				// 'Return'
				if (inBounds(event, 20, 1175, 200, 80)) {
					nullify();
					game.setScreen(new MainMenuScreen(game));
					return;
				}
				// 'Retry'
				else if (inBounds(event, 610, 1175, 165, 80)) {
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
		
		g.drawImage(board, WIDTH_OFFSET, HEIGHT_OFFSET, 0, 0, 
				board.getWidth(), board.getHeight());
		
		for(int r = 0; r < NUM_ROWS; r++) {
			for (int c = 0; c < NUM_COLUMNS; c++) {
				Image tileImage;
				
				switch (tiles[r][c].getType()) {
					case Tile.BLACK:
						tileImage = animations[r][c].getImage();
						break;
					case Tile.WHITE:
						tileImage = white_tile;
						break;
					case Tile.RED:
						tileImage = red_tile;
						break;
					case Tile.YELLOW:
						tileImage = yellow_tile;
						break;
					default:
						tileImage = white_tile;
						break;
				}
				
				g.drawImage(tileImage, 
						WIDTH_OFFSET + WIDTH_GAP + (width + WIDTH_GAP)*r, 
						HEIGHT_OFFSET + HEIGHT_GAP + (height + HEIGHT_GAP)*c, 
						0, 0, width, height);
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

		/* TODO */

		if(isHighScore) {
			paint.setColor(ORANGE_COLOR);
		}
		
		g.drawString("Score: " + score, 400, 75, paint);
		g.drawString("High Score: " + highScore, 400, 140, paint);
		
		if(isHighScore) {
			paint.setColor(TEXT_COLOR);
		}
		
//		g.drawString("Num_Black: " + num_black, 240, 150, paint);
		g.drawImage(shared.getMediaOption(), 
				800-shared.getMediaOption().getWidth(), 0, 0, 0,
        		shared.getMediaOption().getWidth(), 
        		shared.getMediaOption().getHeight());
	}

	private void nullify() {

		// Set all variables to null. You will be recreating them in the
		// constructor.

		paint = null;
		paint2 = null;
		paint3 = null;
		paint4 = null;
		
		black_tile = null;
		white_tile = null; 
		red_tile = null; 
		yellow_tile = null;
		
		black_tile_1 = null; 
		black_tile_2 = null; 
		black_tile_3 = null; 
		black_tile_4 = null;
		black_tile_5 = null;
		black_tile_6 = null;
		black_tile_7 = null;
		black_tile_8 = null;
		black_tile_9 = null;
		black_tile_10 = null;
		black_tile_11 = null; 
		black_tile_12 = null; 
		black_tile_13 = null; 
		black_tile_14 = null;
		black_tile_15 = null;
		black_tile_16 = null;
		black_tile_17 = null;
		
		board = null;
		board_paused = null;
		tiles = null;
		animations = null;

		// Call garbage collector to clean up memory.
		System.gc();
	}

	private void drawReadyUI() {
		Graphics g = game.getGraphics();

		// TODO
//		g.drawARGB(155, 0, 0, 0);
		g.drawString("Tap the black tiles before their ", 400, 250, paint3);
		g.drawString("time runs out", 400, 300, paint3);
		g.drawString("Avoid the red tiles!", 400, 370, paint4);
		g.drawString("Tap anywhere to start", 400, 1230, paint);
	}

	private void drawRunningUI() {
		Graphics g = game.getGraphics();
		
		/* TODO */
		g.drawImage(Assets.pause, 0, 0, 0, 0, 
				Assets.pause.getWidth(), Assets.pause.getHeight());	
	}

	private void drawPausedUI() {
		Graphics g = game.getGraphics();
		
		// Darken the entire screen so you can display the Paused screen.
		// TODO
//		g.drawARGB(155, 0, 0, 0);
		g.drawImage(board_paused, WIDTH_OFFSET, HEIGHT_OFFSET, 0, 0, 
				board_paused.getWidth(), board_paused.getHeight());
		g.drawString("Resume", 400, 625, paint2);
		g.drawString("Restart", 400, 825, paint2);
		g.drawString("Home", 400, 1025, paint2);
	
		// TODO
//		g.drawRect(150, 450, 500, 200, Color.argb(100, 50, 0, 0));
//		g.drawRect(150, 650, 500, 200, Color.argb(100, 50, 0, 0));
//		g.drawRect(150, 850, 500, 200, Color.argb(100, 50, 0, 0));
	}

	private void drawGameOverUI() {
		Graphics g = game.getGraphics();

		// TODO
//		g.drawARGB(155, 0, 0, 0);
		g.drawString("GAME OVER!", 400, 350, paint2);
		g.drawString("Home", 120, 1230, paint);
		g.drawString("Retry", 690, 1230, paint);
		
//		g.drawRect(20, 1175, 200, 80, Color.argb(100, 50, 0, 0));
//		g.drawRect(610, 1175, 165, 80, Color.argb(100, 50, 0, 0));
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
	
	private Animation newAnimation() {
		Animation anim_tile = new Animation();
		anim_tile.addFrame(black_tile_1, ANIM_SPEED);
		anim_tile.addFrame(black_tile_2, ANIM_SPEED);
		anim_tile.addFrame(black_tile_3, ANIM_SPEED);
		anim_tile.addFrame(black_tile_4, ANIM_SPEED);
		anim_tile.addFrame(black_tile_5, ANIM_SPEED);
		anim_tile.addFrame(black_tile_6, ANIM_SPEED);
		anim_tile.addFrame(black_tile_7, ANIM_SPEED);
		anim_tile.addFrame(black_tile_8, ANIM_SPEED);
		anim_tile.addFrame(black_tile_9, ANIM_SPEED);
		anim_tile.addFrame(black_tile_10, ANIM_SPEED);
		anim_tile.addFrame(black_tile_11, ANIM_SPEED);
		anim_tile.addFrame(black_tile_12, ANIM_SPEED);
		anim_tile.addFrame(black_tile_13, ANIM_SPEED);
		anim_tile.addFrame(black_tile_14, ANIM_SPEED);
		anim_tile.addFrame(black_tile_15, ANIM_SPEED);
		anim_tile.addFrame(black_tile_16, ANIM_SPEED);
		anim_tile.addFrame(black_tile_17, ANIM_SPEED);
		anim_tile.addFrame(black_tile, ANIM_SPEED*4);
		
		return anim_tile;
	}
	
	private void newRandomTile() {
		int rand_tile;
		
		if (rand.nextInt(100) < RED_TO_BLACK) {
			rand_tile = Tile.RED;
		}
		else {
			rand_tile = Tile.BLACK;
		}
		
		/* TODO - CHECK IF ALL TILES ARE BLACK */
		while(num_black < NUM_ROWS * NUM_COLUMNS) {
			int row = rand.nextInt(NUM_ROWS);
			int column = rand.nextInt(NUM_COLUMNS);
			
			if (tiles[row][column].getType() == Tile.WHITE) {
				animations[row][column] = newAnimation();
				changeTile(row, column, rand_tile);
				tiles[row][column].setDuration(TILE_DURATION);
				return;
			} 
		}
	}
	
	private void changeTile(int row, int column, int type) {
		Tile tile = tiles[row][column];
		if(tile.getType() != type) {
			
			if(tile.getType() == Tile.BLACK) {
				tile.setDuration(-999);
				animations[row][column] = null;
				num_black--;
			}
			if (type == Tile.BLACK) {
				num_black++;
			}
			
			tile.setType(type);
		}
	}
	
	/* TODO */
//	/* Play the sound when a game object is clicked
//	 */
//	private void objectSound(int i) {
//		if (mediaOn) {
//			switch (i) {
//				case (30 + 115 * 0):
//					Assets.item1.play(0.5f);
//					break;
//				case (30 + 115 * 1):
//					Assets.item2.play(0.5f);
//					break;
//				case (30 + 115 * 2):
//					Assets.item3.play(0.5f);
//					break;
//				case (30 + 115 * 3):
//					Assets.item4.play(0.85f);
//					break;		
//			}
//		}
//	}

}
