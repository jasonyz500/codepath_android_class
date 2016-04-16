package com.codepath.apps.mysimpletweets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.models.Tweet;
import com.codepath.apps.mysimpletweets.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONObject;

public class ComposeActivity extends AppCompatActivity {

    private TwitterClient client;
    private EditText etTweet;
    private ImageView ivProfileImage;
    private TextView tvName;
    private TextView tvScreenName;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        etTweet = (EditText) findViewById(R.id.etTweet);
        ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        tvName = (TextView) findViewById(R.id.tvName);
        tvScreenName = (TextView) findViewById(R.id.tvScreenName);
        user = (User) getIntent().getSerializableExtra("user");
        Picasso.with(getApplicationContext()).load(user.getProfileImageUrl()).into(ivProfileImage);
        tvName.setText(user.getName());
        tvScreenName.setText("@" + user.getScreenName());
        client = TwitterApplication.getRestClient();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.timeline_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // publish and dismiss tweet
        Log.d("DEBUG", etTweet.getText().toString());
        client.publishTweet(etTweet.getText().toString(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Tweet newTweet = Tweet.fromJSON(response);
                end(newTweet);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println("Failed to publish!");
            }
        });
        return true;
    }

    public void end(Tweet newtweet) {
        Intent data = new Intent();
        data.putExtra("Tweet", newtweet);
        setResult(RESULT_OK, data);
        this.finish();
    }
}
