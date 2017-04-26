package com.zjg.enjoy.weexfinance.common.activitycontrol;

import android.app.Activity;


import com.zjg.enjoy.weexfinance.common.base.PABaseActivity;
import com.zjg.enjoy.weexfinance.common.util.Tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yanghongbing on 16/2/29.
 */
public class ActivityManager {

    private List<Activity> activityList;

    public ActivityManager() {
        activityList = new ArrayList<Activity>();
    }

    public void addToStack(Activity activity) {
        synchronized (activityList) {
            if (activity != null) {
                if (activityList.contains(activity)) {
                    activityList.remove(activity);
                }
                activityList.add(activity);
            }
        }
    }

    public void removeFromStack(Activity activity) {
        synchronized (activityList) {
            if (activity != null) {
                if (activityList.contains(activity)) {
                    activityList.remove(activity);
                }
            }
        }
    }

    public Activity getTopActivity() {
        int size = activityList.size();
        if (size > 0) {
            return activityList.get(size - 1);
        }
        return null;
    }

    public Activity getBottomActivity() {
        int size = activityList.size();
        if (size > 0) {
            return activityList.get(0);
        }
        return null;
    }

    public void finishAllActivities() {
        synchronized (activityList) {
            Iterator<Activity> iterator = activityList.iterator();
            while (iterator.hasNext()) {
                //iterator.remove();
                PABaseActivity activity = (PABaseActivity) iterator.next();
                activity.finishActivity();
            }
        }
    }

    public void backToActivity(String activityId) {
        if (Tools.isTrimEmpty(activityId)) {
            return;
        }
        synchronized (activityList) {
            Collections.reverse(activityList);
            Iterator<Activity> iterator = activityList.iterator();
            while (iterator.hasNext()) {
                PABaseActivity activity = (PABaseActivity) iterator.next();
                if (!activityId.equals(activity.getActivityId())
                        && !ActivityId.MAINWEBVIEW.equals(activity.getActivityId())) {
                    activity.finishActivity();
                } else {
                    break;
                }
            }
            Collections.reverse(activityList);
        }
    }

    public Activity getBelow(Activity activity) {
        int index = getIndex(activity);
        if (index > 0) {
            return activityList.get(index - 1);
        }
        return null;
    }

    public Activity getAbove(Activity activity) {
        int index = getIndex(activity);
        if (index >= 0 && index < activityList.size() - 1) {
            return activityList.get(index + 1);
        }
        return null;
    }

    public int getIndex(Activity activity) {
        for (int i = 0; i < activityList.size(); i++) {
            if (activityList.get(i).equals(activity)) {
                return i;
            }
        }
        return -1;
    }

    public void finishActivity(String activityId) {
        synchronized (activityList) {
            for (int i = activityList.size() - 1; i >= 0; i--) {
                PABaseActivity activity = (PABaseActivity) activityList.get(i);
                if (activity.getActivityId().equals(activityId)) {
                    activity.finishActivity();
                    return;
                }
            }
        }
    }


    public List<Activity> getActivityList() {
        return activityList;
    }

    public void finishActivityWhenTokenExpired() {
        //TODO:跳转到首页
        backToActivity(ActivityId.MAINWEBVIEW);
    }
}
