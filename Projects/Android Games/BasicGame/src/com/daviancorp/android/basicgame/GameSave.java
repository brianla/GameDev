package com.daviancorp.android.basicgame;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.daviancorp.framework.JSONSerializer;

public class GameSave extends JSONSerializer {

	/* TODO */
//	private static final String JSON_HIGHSCORE = "score";
//	private static final String JSON_MEDIA_OPTION = "media_option";
	
	public GameSave(Context c, String f) {
		super(c, f);
	}
	
	/* TODO */
	public void saveGame(int score, boolean media)
		throws JSONException, IOException {
		
		// Build an array in JSON
		JSONObject json = new JSONObject();
		
		/* TODO */
//		json.put(JSON_HIGHSCORE, score);
//		json.put(JSON_MEDIA_OPTION, media);
		
		JSONArray array = new JSONArray();
		array.put(json);
		
		super.save(array);
	}

	/* TODO */
//	public int loadHighScore() {
//		try {
//			JSONArray array = super.load();
//			int highscore = array.getJSONObject(0).getInt(JSON_HIGHSCORE);
//			return highscore;
//		}
//		catch (Exception e) {
//			return 0;
//		}
//	}
//	
//	/* Check if media was played or muted in the past
//	 */
//	public boolean loadMediaOption() {
//		try {
//			JSONArray array = super.load();
//			boolean media_option = array.getJSONObject(0).getBoolean(JSON_MEDIA_OPTION);
//			return media_option;
//		}
//		catch (Exception e) {
//			return true;
//		}
//	}
}
