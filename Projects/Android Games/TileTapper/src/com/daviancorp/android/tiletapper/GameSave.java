package com.daviancorp.android.tiletapper;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.daviancorp.framework.JSONSerializer;

public class GameSave extends JSONSerializer {

	private static final String JSON_HIGHSCORE_EASY = "easy_highscore";
	private static final String JSON_HIGHSCORE_MEDIUM = "medium_highscore";
	private static final String JSON_HIGHSCORE_HARD = "hard_highscore";
	private static final String JSON_MODE = "mode";
	private static final String JSON_MUSIC_OPTION = "music_option";
	private static final String JSON_SOUND_OPTION = "sound_option";
	
	public GameSave(Context c, String f) {
		super(c, f);
	}
	
	public void saveGame(int easyHS, int mediumHS, int hardHS, int mode, 
			boolean music, boolean sound)
		throws JSONException, IOException {
		
		// Build an array in JSON
		JSONObject json = new JSONObject();
		
		json.put(JSON_HIGHSCORE_EASY, easyHS);
		json.put(JSON_HIGHSCORE_MEDIUM, mediumHS);
		json.put(JSON_HIGHSCORE_HARD, hardHS);
		json.put(JSON_MODE, mode);
		json.put(JSON_MUSIC_OPTION, music);
		json.put(JSON_SOUND_OPTION, sound);
		
		JSONArray array = new JSONArray();
		array.put(json);
		
		super.save(array);
	}

	public int loadEasyHighScore() {
		try {
			JSONArray array = super.load();
			int easyHS = array.getJSONObject(0).getInt(JSON_HIGHSCORE_EASY);
			return easyHS;
		}
		catch (Exception e) {
			return 0;
		}
	}
	
	public int loadMediumHighScore() {
		try {
			JSONArray array = super.load();
			int mediumHS = array.getJSONObject(0).getInt(JSON_HIGHSCORE_MEDIUM);
			return mediumHS;
		}
		catch (Exception e) {
			return 0;
		}
	}
	
	public int loadHardHighScore() {
		try {
			JSONArray array = super.load();
			int hardHS = array.getJSONObject(0).getInt(JSON_HIGHSCORE_HARD);
			return hardHS;
		}
		catch (Exception e) {
			return 0;
		}
	}
	
	public int loadMode() {
		try {
			JSONArray array = super.load();
			int mode = array.getJSONObject(0).getInt(JSON_MODE);
			return mode;
		}
		catch (Exception e) {
			return Shared.EASY;
		}
	}
	
	/* Check if music was played or muted in the past
	 */
	public boolean loadMusicOption() {
		try {
			JSONArray array = super.load();
			boolean music_option = array.getJSONObject(0).getBoolean(JSON_MUSIC_OPTION);
			return music_option;
		}
		catch (Exception e) {
			return true;
		}
	}
	
	/* Check if sound was played or muted in the past
	 */
	public boolean loadSoundOption() {
		try {
			JSONArray array = super.load();
			boolean sound_option = array.getJSONObject(0).getBoolean(JSON_SOUND_OPTION);
			return sound_option;
		}
		catch (Exception e) {
			return true;
		}
	}
}
