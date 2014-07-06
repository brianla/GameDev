package com.daviancorp.android.catchgame;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.daviancorp.framework.JSONSerializer;

public class GameSave extends JSONSerializer {

	private static final String JSON_HIGHSCORE = "score";
	
	public GameSave(Context c, String f) {
		super(c, f);
	}
	
	public void saveHighScore(int score)
		throws JSONException, IOException {
		
		// Build an array in JSON
		JSONObject json = new JSONObject();
		json.put(JSON_HIGHSCORE, score);
		
		JSONArray array = new JSONArray();
		array.put(json);
		
		super.save(array);
	}

}
