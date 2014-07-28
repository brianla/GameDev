package com.daviancorp.android.tilegame;

import com.daviancorp.framework.Image;

/* Singleton Class */
public final class Shared {
	private static Shared instance = null;
	
	private GameSave gameSave;
	private int highscore;
	private Image mediaOption;
	private boolean mediaOn;
	
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

	public Image getMediaOption() {
		return mediaOption;
	}

	public void setMediaOption(Image mediaOption) {
		this.mediaOption = mediaOption;
	}

	public boolean isMediaOn() {
		return mediaOn;
	}

	public void setMediaOn(boolean mediaOn) {
		this.mediaOn = mediaOn;
	}

	/* Get the info for muted/unmuted media
	 */
	public void getMedia() {
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
	public void toggleMedia() {
		mediaOn = !mediaOn;
		getMedia();
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
			gameSave.saveGame(highscore, mediaOn);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
