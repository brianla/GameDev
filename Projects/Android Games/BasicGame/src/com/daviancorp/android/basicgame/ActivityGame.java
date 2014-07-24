package com.daviancorp.android.basicgame;

import com.daviancorp.framework.Screen;
import com.daviancorp.framework.implementation.AndroidGame;

public class ActivityGame extends AndroidGame {

	boolean firstTimeCreate = true;

	@Override
	public Screen getInitScreen() {

		if (firstTimeCreate) {
			Assets.load(this);
			firstTimeCreate = false;
		}

		return new SplashLoadingScreen(this);

	}

	@Override
	public void onBackPressed() {
		getCurrentScreen().backButton();
	}

	@Override
	public void onResume() {
		super.onResume();

		/* TODO */
//		Assets.theme.play();

	}

	@Override
	public void onPause() {
		super.onPause();
		
		/* TODO */
//		Assets.theme.pause();

	}

}
