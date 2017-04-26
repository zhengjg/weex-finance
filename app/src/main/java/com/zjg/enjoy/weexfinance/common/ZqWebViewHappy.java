package com.zjg.enjoy.weexfinance.common;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by ZHENGJINGUANG829 on 2017-02-20.
 */
public class ZqWebViewHappy {

    private Context context;

    public ZqWebViewHappy() {

    }

    public ZqWebViewHappy(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void showToast(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
