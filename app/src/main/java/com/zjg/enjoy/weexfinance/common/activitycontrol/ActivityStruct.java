package com.zjg.enjoy.weexfinance.common.activitycontrol;

import android.app.Activity;

/**
 * Created by ZHENGJINGUANG829 on 2017-02-21.
 */
public class ActivityStruct {
    private String activityId;
    private String title;
    private Class<? extends Activity> className;
    private int loginLevel;
    private String alias; //别名

    public ActivityStruct(String activityId, String title, Class<? extends Activity> className) {
        this.activityId = activityId;
        this.title = title;
        this.className = className;
    }

    public ActivityStruct(String activityId, String title, Class<? extends Activity> className, int loginLevel) {
        this.activityId = activityId;
        this.title = title;
        this.className = className;
        this.loginLevel = loginLevel;
    }

    public ActivityStruct(String activityId, String title, Class<? extends Activity> className, String alias) {
        this.activityId = activityId;
        this.title = title;
        this.className = className;
        this.alias = alias;
    }

    public ActivityStruct(String activityId, String title, Class<? extends Activity> className, int loginLevel, String alias) {
        this.activityId = activityId;
        this.title = title;
        this.className = className;
        this.loginLevel = loginLevel;
        this.alias = alias;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }


    public String getActivityId() {
        return activityId;
    }

    public String getTitle() {
        return title;
    }

    public Class<? extends Activity> getClassName() {
        return className;
    }

    public int getLoginLevel() {
        return loginLevel;
    }

}
