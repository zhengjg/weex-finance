package com.zjg.enjoy.weexfinance.common.activitycontrol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.zjg.enjoy.weexfinance.application.EleAppliction;
import com.zjg.enjoy.weexfinance.common.consts.IntentKeys;


/**
 * Created by yanghongbing on 16/3/7.
 */
public class ActivityForward {

    public static void forward(Context context, String activityId, Intent intent) {
        if (context == null) {
            context = EleAppliction.getContext();
        }
        if (activityId == null) {
            return;
        }
        if (intent == null) {
            intent = new Intent();
        }
        intent.putExtra(IntentKeys.ACTIVITY_ID, activityId);
        Class<? extends Activity> activityClass = ActivityMapping.getInstance().getActivity(activityId);
        if (activityClass == null) {
            return;
        }
        intent.setClass(context, activityClass);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public static void forward(Activity fromActivity, String activityId, Intent intent, int requestCode) {
        if (fromActivity == null) {
            return;
        }
        if (activityId == null) {
            return;
        }
        if (intent == null) {
            intent = new Intent();
        }
        ActivityStruct struct = ActivityMapping.getInstance().getActivityStruct(activityId);
        if (struct == null) {
            return;
        }
        String[] parts = activityId.split(":");
        activityId = parts[0];
        if (parts.length > 1) {
            intent.putExtra(IntentKeys.PAGE_INDEX, parts[1]);
        }
        intent.putExtra(IntentKeys.ACTIVITY_ID, activityId);
        Class<? extends Activity> activityClass = ActivityMapping.getInstance().getActivity(activityId);
        if (activityClass == null) {
            return;
        }
        intent.setClass(fromActivity, activityClass);
        fromActivity.startActivityForResult(intent, requestCode);
    }
}
