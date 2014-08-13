package com.daviancorp.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.daviancorp.gameworld.GameRenderer;
import com.daviancorp.gameworld.GameWorld;
import com.daviancorp.zbHelpers.InputHandler;

public class GameScreen implements Screen {
	private static final String TAG = "GameScreen";
	
	private GameWorld world;
	private GameRenderer renderer;
	private float runTime = 0;

	public GameScreen() {
		
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameWidth = 136;
		float gameHeight = screenHeight / (screenWidth / gameWidth);
		
		int midPointY = (int) (gameHeight / 2);

		world = new GameWorld(midPointY);
		renderer = new GameRenderer(world, (int) gameHeight, midPointY);
		
		Gdx.input.setInputProcessor(new InputHandler(world));
	}
	
	@Override
	public void render(float delta) {
		runTime += delta;
		world.update(delta);
		renderer.render(runTime);
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		Gdx.app.log(TAG, "show called");
	}

	@Override
	public void hide() {
		Gdx.app.log(TAG, "hide called");
	}

	@Override
	public void pause() {
		Gdx.app.log(TAG, "pause called");
	}

	@Override
	public void resume() {
		Gdx.app.log(TAG, "resume called");
	}

	@Override
	public void dispose() {
		
	}

}
