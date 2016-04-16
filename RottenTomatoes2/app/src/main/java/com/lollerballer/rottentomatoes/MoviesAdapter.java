package com.lollerballer.rottentomatoes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MoviesAdapter extends ArrayAdapter<Movie> {

    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
        }
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvScore = (TextView) convertView.findViewById(R.id.tvScore);
        tvTitle.setText(movie.title);
        tvScore.setText(movie.getScoreLabel());
        return convertView;
    }
}
