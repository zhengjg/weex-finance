package com.zjg.enjoy.weexfinance.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.zjg.enjoy.weexfinance.common.activitycontrol.ActivityForward;
import com.zjg.enjoy.weexfinance.common.activitycontrol.ActivityId;
import com.zjg.enjoy.weexfinance.common.consts.IntentKeys;


/**
 * Created by ZHENGJINGUANG829 on 2017-02-21.
 */
public class ForwardUtils {

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

}
