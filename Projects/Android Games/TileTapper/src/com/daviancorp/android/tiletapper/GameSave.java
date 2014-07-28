package com.daviancorp.android.tiletapper;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.daviancorp.framework.JSONSerializer;

public class GameSave extends JSONSerializer {

	private static final String JSON_HIGHSCORE = "score";
	private static final String JSON_MUSIC_OPTION = "music_option";
	private static final String JSON_SOUND_OPTION = "sound_option";
	
	public GameSave(Context c, String f) {
		super(c, f);
	}
	
	public void saveGame(int score, boolean music, boolean sound)
		throws JSONException, IOException {
		
		// Build an array in JSON
		JSONObject json = new JSONObject();
		
		json.put(JSON_HIGHSCORE, score);
		json.put(JSON_MUSIC_OPTION, music);
		json.put(JSON_SOUND_OPTION, sound);
		
		JSONArray array = new JSONArray();
		array.put(json);
		
		super.save(array);
	}

	public int loadHighScore() {
		try {
			JSONArray array = super.load();
			int highscore = array.getJSONObject(0).getInt(JSON_HIGHSCORE);
			return highscore;
		}
		catch (Exception e) {
			return 0;
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
