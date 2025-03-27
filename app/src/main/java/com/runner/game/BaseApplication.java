package com.runner.game;

import android.app.Application;
import android.content.Context;

import com.runner.game.ui.ActivityManager;

public class BaseApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }

    public static ActivityManager getActivityManager() {
        return ActivityManager.getInstance();
    }
}
