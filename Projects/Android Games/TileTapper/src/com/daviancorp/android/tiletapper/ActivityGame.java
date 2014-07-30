package com.daviancorp.android.tiletapper;

import android.util.Log;

import com.daviancorp.framework.Screen;
import com.daviancorp.framework.implementation.AndroidGame;

public class ActivityGame extends AndroidGame {
	private static final String TAG = "ActivityGame";
	
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

		if(!Assets.theme.isPlaying() && Shared.getInstance().isMusicOn()) {
			Assets.theme.play();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		
		if(Assets.theme.isPlaying()) {
			Assets.theme.pause();
		}
	}

}
