package com.runner.game.ui;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityManager {

    private static volatile ActivityManager mInstance;
    private final List<Activity>  activityList = new ArrayList<>();

    private ActivityManager() {
    }

    public static ActivityManager getInstance() {
        if (mInstance == null) {
            synchronized (ActivityManager.class) {
                if (mInstance == null) {
                    mInstance = new ActivityManager();
                }
            }
        }
        return mInstance;
    }

    public void addActivity(Activity activity) {
        if (activity != null) {
            activityList.add(activity);
        }
    }

    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityList.remove(activity);
        }
    }

    public void finishAllActivity() {
        for (Activity activity : activityList) {
            activity.finish();
        }
    }
}
