package com.zjg.enjoy.weexfinance.application;

import android.app.Application;
import android.content.Context;

import com.zjg.enjoy.weexfinance.common.activitycontrol.ActivityManager;
import com.zjg.enjoy.weexfinance.data.cache.CacheManager;


/**
 * Created by ZHENGJINGUANG829 on 2017-02-21.
 */
public class EleAppliction extends Application {

    private static Context context;
    private static EleAppliction application;
    private ActivityManager activityManager;
    private CacheManager cacheManager;

    @Override
    public void onCreate() {
        application = this;
        super.onCreate();
        setContext(getApplicationContext());
        activityManager = new ActivityManager();
        cacheManager = new CacheManager();
    }

    public static EleAppliction getInstance() {
        return application;
    }

    public ActivityManager getActivityManager() {
        return activityManager;
    }
    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        EleAppliction.context = context;
    }


}
