package com.lollerballer.gridimagesearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.lollerballer.gridimagesearch.EndlessScrollListener;
import com.lollerballer.gridimagesearch.ImageResult;
import com.lollerballer.gridimagesearch.ImageResultsAdapter;
import com.lollerballer.gridimagesearch.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class PhotosActivity extends AppCompatActivity {
    private EditText etQuery;
    private GridView gvResults;
    private ArrayList<ImageResult> imageResults;
    private ImageResultsAdapter aImageResults;

    private String color;
    private String type;
    private String size;
    private String site;

    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        setupViews();
        imageResults = new ArrayList<ImageResult>();
        aImageResults = new ImageResultsAdapter(this, imageResults);
        gvResults.setAdapter(aImageResults);
        getSupportActionBar().setTitle("Google Image Search");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.photos_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // only one menu option
        Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
        i.putExtra("type", type);
        i.putExtra("color", color);
        startActivityForResult(i, REQUEST_CODE);
        return true;
    }

    private void setupViews() {
        etQuery = (EditText) findViewById(R.id.etQuery);
        gvResults = (GridView) findViewById(R.id.gvResults);
        gvResults.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                loadMoreDataFromApi(totalItemsCount);
                return true;
            }
        });
        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
                ImageResult imageResult = imageResults.get(position);
                i.putExtra("result", imageResult);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            color = data.getExtras().getString("color");
            type = data.getExtras().getString("type");
            size = data.getExtras().getString("size");
            site = data.getExtras().getString("site");
            searchWithParams(0, true);
            aImageResults.notifyDataSetChanged();
        }
    }

    public void loadMoreDataFromApi(int offset) {
        searchWithParams(offset, false);
    }

    public void searchWithParams(int offset, final boolean isNew) {
        String query = etQuery.getText().toString();
        AsyncHttpClient client = new AsyncHttpClient();
        String extras = "&start=" + offset;
        if (color != null && !color.equals("any")) {
            extras += "&imgcolor=" + color;
        }
        if (type != null && !type.equals("any")) {
            extras += "&imgtype=" + type;
        }
        if (size != null && !size.equals("any")) {
            extras += "&imgsz=" + size;
        }
        if (site != null && !site.equals("")) {
            extras += "&as_sitesearch=" + site;
        }
        String searchURL = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=" + query + "&rsz=8" + extras;
        System.out.println(searchURL);
        client.get(searchURL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("DEBUG", response.toString());
                JSONArray imageResultsJSON = null;
                try {
                    imageResultsJSON = response.getJSONObject("responseData").getJSONArray("results");
                    if (isNew) {
                        imageResults.clear(); // clear only on initial search for pagination
                    }
                    aImageResults.addAll(ImageResult.fromJSONArray(imageResultsJSON));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onImageSearch(View view) {
        searchWithParams(0, true);
    }
}
