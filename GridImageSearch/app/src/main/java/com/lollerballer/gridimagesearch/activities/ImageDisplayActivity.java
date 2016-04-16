package com.lollerballer.gridimagesearch.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.lollerballer.gridimagesearch.ImageResult;
import com.lollerballer.gridimagesearch.R;
import com.squareup.picasso.Picasso;

public class ImageDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);

        ImageResult imageResult = (ImageResult) getIntent().getSerializableExtra("result");
        String url = imageResult.fullURL;
        ImageView imageView = (ImageView) findViewById(R.id.ivImageResult);
        Picasso.with(this).load(url).into(imageView);
        getSupportActionBar().hide();
    }
}
