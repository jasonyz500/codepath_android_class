package com.lollerballer.rottentomatoes;

import java.util.ArrayList;

public class Movie {
    public String title;
    public String posterUrl;
    public float score;

    public Movie(String title, String posterUrl, float score) {
        this.title = title;
        this.posterUrl = posterUrl;
        this.score = score;
    }

    public String getScoreLabel() {
        return score + "%";
    }

    public static ArrayList<Movie> getFakeMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Interstellar", "", 32.2f));
        movies.add(new Movie("Guptata", "", 12.3f));
        movies.add(new Movie("Banana", "", 43.2f));
        return movies;
    }
}
