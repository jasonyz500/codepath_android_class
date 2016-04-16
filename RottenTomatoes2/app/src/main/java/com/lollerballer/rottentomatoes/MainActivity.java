package com.lollerballer.rottentomatoes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Movie> movies = Movie.getFakeMovies();

        ListView lvMovies = (ListView) findViewById(R.id.lvMovies);
        MoviesAdapter adapter = new MoviesAdapter(this, movies);

        lvMovies.setAdapter(adapter);
    }
}
