package com.lollerballer.instagramclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.text.format.DateUtils;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jzhou5 on 10/10/15.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {
    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InstagramPhoto photo = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
        ImageView ivProfile = (ImageView) convertView.findViewById(R.id.ivProfile);
        TextView tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
        TextView tvLikes = (TextView) convertView.findViewById(R.id.tvLikes);
        TextView tvTimestamp = (TextView) convertView.findViewById(R.id.tvTimestamp);
        tvCaption.setText(photo.caption);
        ivPhoto.setImageResource(0);
        Picasso.with(getContext()).load(photo.imageURL).into(ivPhoto);
        ivProfile.setImageResource(0);
        Picasso.with(getContext()).load(photo.profileURL).into(ivProfile);
        tvUsername.setText(photo.username);
        tvLikes.setText(Integer.toString(photo.likesCount) + " likes");
        long now = System.currentTimeMillis();
        CharSequence timeStamp = DateUtils.getRelativeTimeSpanString(Long.valueOf(photo.timeStamp)*1000, now, DateUtils.DAY_IN_MILLIS);

        tvTimestamp.setText(timeStamp);

        return convertView;
    }
}
