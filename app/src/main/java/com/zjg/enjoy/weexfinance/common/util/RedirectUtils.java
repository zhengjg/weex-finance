package com.zjg.enjoy.weexfinance.common.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;


import com.zjg.enjoy.weexfinance.common.consts.IntentKeys;
import com.zjg.enjoy.weexfinance.data.model.Module;

import java.util.Map;

/**
 * Created by ZHENGJINGUANG829 on 2017-02-20.
 */
public class RedirectUtils {

    public static void redirect(Context context, String url) {
        if (UrlUtils.isHappyUrl(url)) {
            String action = UrlUtils.getAction(url);
            Map<String, String> params = UrlUtils.getParamMap(url);
            go(context, action, params);
        } else if (UrlUtils.isWebUrl(url)) {
            ForwardUtils.forwardWeb(context, url, null);
        }
    }

    public static void go(Context context, String action, Map<String, String> params) {
        final Intent intent = new Intent();
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                intent.putExtra(key, params.get(key));
            }
        }

        //捕获schema
        if (Module.HAPPY_WEBV.equals(action)) {
            String url = params.get("url");
            String title = params.get("title");
            String hidden = params.get(IntentKeys.HIDDEN);
            if (!TextUtils.isEmpty(hidden)) {
                intent.putExtra(IntentKeys.SHOW_WEB_VIEW_TITLE, !Boolean.valueOf(hidden));
            }
            //intent.putExtra(IntentKeys.HIDDEN, hidden);
            ForwardUtils.forwardWeb(context, url, title, intent);
        } else if (Module.OUT_BROWSER.equals(action)) {
            String url = params.get("url");
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            context.startActivity(intent);
        }

    }
}
