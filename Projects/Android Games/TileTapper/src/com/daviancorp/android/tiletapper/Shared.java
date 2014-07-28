package com.daviancorp.android.tiletapper;

import android.util.Log;

/* Singleton Class */
public final class Shared {
	private static final String TAG = "Shared";
	
	private static Shared instance = null;
	
	private GameSave gameSave;
	private int highscore;
	private boolean musicOn, soundOn;
	
	private Shared() {
		
	}
	
	public static Shared getInstance() {
		if (instance == null) {
			instance = new Shared();
		}
		return instance;
	}
	
	public GameSave getGameSave() {
		return gameSave;
	}

	public void setGameSave(GameSave gameSave) {
		this.gameSave = gameSave;
	}

	public int getHighscore() {
		return highscore;
	}

	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}

	public boolean isMusicOn() {
		return musicOn;
	}

	public void setMusicOn(boolean musicOn) {
		this.musicOn = musicOn;
	}

	public boolean isSoundOn() {
		return soundOn;
	}

	public void setSoundOn(boolean soundOn) {
		this.soundOn = soundOn;
	}

	/* Get the info for muted/unmuted music
	 */
	public void getMusic() {
		if (musicOn == true) {			
			if (!Assets.theme.isPlaying()) {
				Assets.theme.play();
			}
		} 
		else {
			if (Assets.theme.isPlaying()) {
				Assets.theme.stop();
			}
		}
	}
	
	/* Toggle the music option
	 */
	public void toggleMusic() {
		musicOn = !musicOn;
		getMusic();
		saveGame();
	}
	
	/* Toggle the sound option
	 */
	public void toggleSound() {
		soundOn = !soundOn;
		saveGame();
	}
	
	/* Check if score beats high score
	 */
	public boolean checkScore(int score) {
		if (score > highscore) {
			highscore = score;
			saveGame();
			return true;
		}
		else return false;
	}
	
	/* Save game info to system
	 */
	public boolean saveGame() {
		try {
			gameSave.saveGame(highscore, musicOn, soundOn);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
