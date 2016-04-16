package com.lollerballer.simplechat;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ChatApplication extends Application {
    public static final String YOUR_APPLICATION_ID = "OOFslVW51PmIgLDRwGBARspl1WNePgP3Q23NLv5i";
    public static final String YOUR_CLIENT_KEY = "bhMS2aXDRHGiIyG9rXSvvf0vPVK4CARB66HYxePW";
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Message.class);
        Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);
    }
}