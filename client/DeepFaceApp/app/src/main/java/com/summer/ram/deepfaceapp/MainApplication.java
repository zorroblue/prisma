package com.summer.ram.deepfaceapp;

import android.app.Application;

import com.facebook.soloader.SoLoader;

/**
 * Created by rameshwar on 15/12/17.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SoLoader.init(this, false);
    }
}