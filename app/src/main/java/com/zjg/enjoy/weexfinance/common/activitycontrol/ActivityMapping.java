package com.zjg.enjoy.weexfinance.common.activitycontrol;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZHENGJINGUANG829 on 2017-02-21.
 */
public class ActivityMapping {
    private static volatile ActivityMapping instance;

    private Map<String, ActivityStruct> activityMap;

    private ActivityMapping() {
        activityMap = new HashMap<String, ActivityStruct>();
        init();
    }

    public static ActivityMapping getInstance() {
        if (instance == null) {
            synchronized (ActivityMapping.class) {
                if (instance == null) {
                    instance = new ActivityMapping();
                }
            }
        }
        return instance;
    }

    private void init() {
        //activityMap.put(ActivityId.MAINACTIVITY, new ActivityStruct(ActivityId.MAINACTIVITY, "首页", MainActivity.class));
    }

    public ActivityStruct getActivityStruct(String activityId) {
        if (activityId == null) {
            return null;
        }
        return activityMap.get(activityId);
    }

    public Class<? extends Activity> getActivity(String activityId) {
        ActivityStruct struct = getActivityStruct(activityId);
        if (struct != null) {
            return struct.getClassName();
        }
        return null;
    }
}
