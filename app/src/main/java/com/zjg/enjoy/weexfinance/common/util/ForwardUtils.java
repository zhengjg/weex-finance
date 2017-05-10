package com.zjg.enjoy.weexfinance.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.zjg.enjoy.weexfinance.application.EleAppliction;
import com.zjg.enjoy.weexfinance.common.activitycontrol.ActivityForward;
import com.zjg.enjoy.weexfinance.common.activitycontrol.ActivityId;
import com.zjg.enjoy.weexfinance.common.activitycontrol.ActivityMapping;
import com.zjg.enjoy.weexfinance.common.activitycontrol.ActivityStruct;
import com.zjg.enjoy.weexfinance.common.consts.IntentKeys;


/**
 * Created by ZHENGJINGUANG829 on 2017-02-21.
 */
public class ForwardUtils {

    //返回值，是否关闭背景activity
    public static boolean forwardActivity(Context context, String activityId, Intent intent) {
        if (intent == null) {
            intent = new Intent();
        }
        intent.putExtra(IntentKeys.NEXT_ACTIVITY_ID, activityId);
        ActivityStruct activityStruct = ActivityMapping.getInstance().getActivityStruct(activityId);
        if (activityStruct == null && !intent.getBooleanExtra(IntentKeys.NEED_STRONG, false)) {
            String[] parts = activityId.split(":");
            activityStruct = ActivityMapping.getInstance().getActivityStruct(parts[0]);
            if (activityStruct == null) {
                return true;
            }
        }
        ActivityForward.forward(context, activityId, intent);
        return true;
    }


    public static void forwardWeb(Context context, String url, String title) {
        forwardWeb(context, url, title, null);
    }

    public static boolean forwardWeb(Context context, String url, String title, Intent intent) {

        if (intent == null) {
            intent = new Intent();
        }
        if (title != null) {
            intent.putExtra(IntentKeys.ACTIVITY_TITLE_KEY, title);
        }
        if (url != null) {
            intent.putExtra(IntentKeys.KEYURL, url);
        } else {
            url = intent.getStringExtra(IntentKeys.KEYURL);
        }
        if (UrlUtils.isHappyUrl(url)) {
            RedirectUtils.redirect(context, url);
            return false;
        }
        String toActivityId = ActivityId.PAPPYWEBVIEW;
        intent.putExtra(IntentKeys.NEXT_ACTIVITY_ID, toActivityId);
        ActivityForward.forward(context, toActivityId, intent);
        return true;
    }

    public static boolean forwardWeex(Context context, String url, String title, Intent intent) {
        if (intent == null) {
            intent = new Intent();
        }
        if (title != null) {
            intent.putExtra(IntentKeys.ACTIVITY_TITLE_KEY, title);
        }
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString("title", title);
        intent.putExtras(bundle);
        String toActivityId = ActivityId.WEEX_LOCAL;
        intent.putExtra(IntentKeys.NEXT_ACTIVITY_ID, toActivityId);
        ActivityForward.forward(context, toActivityId, intent);
        return true;
    }

}
