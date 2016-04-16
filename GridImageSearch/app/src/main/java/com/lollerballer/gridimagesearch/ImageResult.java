package com.lollerballer.gridimagesearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jzhou5 on 10/17/15.
 */
public class ImageResult implements Serializable {
    public String fullURL;
    public String thumbURL;
    public String title;
    public int height;
    public int width;

    public ImageResult(JSONObject json) {
        try {
            this.fullURL = json.getString("url");
            this.thumbURL = json.getString("tbUrl");
            this.title = json.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ImageResult> fromJSONArray(JSONArray array) {
        ArrayList<ImageResult> results = new ArrayList<ImageResult>();
        for (int i = 0; i < array.length(); i++) {
            try {
                results.add(new ImageResult(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
