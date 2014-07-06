package com.daviancorp.framework;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;

public class JSONSerializer {

	protected Context mContext;
	protected String mFilename;
	
	public JSONSerializer(Context c, String f) {
		mContext = c;
		mFilename = f;
	}
	
	public void save(JSONArray array)
		throws JSONException, IOException {
		
		// Write the file to disk
		Writer writer = null;
		try {
			OutputStream out = mContext
					.openFileOutput(mFilename, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(array.toString());
		} finally {
			if (writer != null)
				writer.close();
		}
	}

}
