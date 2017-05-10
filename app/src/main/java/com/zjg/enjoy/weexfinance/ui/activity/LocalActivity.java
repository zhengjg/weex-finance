package com.zjg.enjoy.weexfinance.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;
import com.zjg.enjoy.weexfinance.R;
import com.zjg.enjoy.weexfinance.common.base.PABaseActivity;
import com.zjg.enjoy.weexfinance.ui.view.PATitleView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengjinguang on 2017/4/26.
 */
public class LocalActivity extends Activity implements IWXRenderListener {
    private WXSDKInstance wxsdkInstance;
    private final static String WEEX_TAG = "weexLocal";
    private Intent intent;
    private String path;
    private String title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_weex);
        intent = getIntent();
        if (intent == null) {
            return;
        }
        handleIntent();
        WXEnvironment.sDebugMode = true;
        WXEnvironment.sDebugWsUrl = "ws://10.243.242.99:8088/debugProxy/native";
        wxsdkInstance = new WXSDKInstance(this);
        wxsdkInstance.registerRenderListener(this);
        /**information
         * weexLocal 可以替换成自定义的字符串，针对埋点有效。
         * template 是.we transform 后的 js文件。
         * option 可以为空，或者通过option传入 js需要的参数。例如bundle js的地址等。
         * jsonInitData 可以为空。
         */
        Map<String, Object> options = new HashMap<>();
        wxsdkInstance.render(WEEX_TAG, WXFileUtils.loadAsset(path, LocalActivity.this), null, null, WXRenderStrategy.APPEND_ASYNC);


    }

    protected void handleIntent() {
        path = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        setContentView(view);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wxsdkInstance != null) {
            wxsdkInstance.onActivityResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (wxsdkInstance != null) {
            wxsdkInstance.onActivityPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (wxsdkInstance != null) {
            wxsdkInstance.onActivityStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (wxsdkInstance != null) {
            wxsdkInstance.onActivityDestroy();
        }
    }
}
