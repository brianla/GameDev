package com.daviancorp.android.catchgame;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.daviancorp.framework.JSONSerializer;

public class GameSave extends JSONSerializer {

	private static final String JSON_HIGHSCORE = "score";
	private static final String JSON_MEDIA_OPTION = "media_option";
	
	public GameSave(Context c, String f) {
		super(c, f);
	}
	
	public void saveCatchGame(int score, boolean media)
		throws JSONException, IOException {
		
		// Build an array in JSON
		JSONObject json = new JSONObject();
		json.put(JSON_HIGHSCORE, score);
		json.put(JSON_MEDIA_OPTION, media);
		
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
	
	/* Check if media was played or muted in the past
	 */
	public boolean loadMediaOption() {
		try {
			JSONArray array = super.load();
			boolean media_option = array.getJSONObject(0).getBoolean(JSON_MEDIA_OPTION);
			return media_option;
		}
		catch (Exception e) {
			return true;
		}
	}
}
