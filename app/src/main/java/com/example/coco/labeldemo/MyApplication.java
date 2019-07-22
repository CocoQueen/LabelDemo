package com.example.coco.labeldemo;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    public static Context sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = getApplicationContext();
    }
}
