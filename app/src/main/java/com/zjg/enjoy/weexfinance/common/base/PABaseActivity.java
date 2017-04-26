package com.zjg.enjoy.weexfinance.common.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import com.zjg.enjoy.weexfinance.R;
import com.zjg.enjoy.weexfinance.application.EleAppliction;
import com.zjg.enjoy.weexfinance.common.activitycontrol.ActivityManager;
import com.zjg.enjoy.weexfinance.common.activitycontrol.ActivityMapping;
import com.zjg.enjoy.weexfinance.common.activitycontrol.ActivityStruct;
import com.zjg.enjoy.weexfinance.common.consts.IntentKeys;
import com.zjg.enjoy.weexfinance.ui.view.PATitleView;


/**
 * Created by ZHENGJINGUANG829 on 2017-02-22.
 */
public abstract class PABaseActivity extends FragmentActivity implements
        View.OnClickListener, PATitleView.OnTitleClickListener {

    private String activityId;
    private PATitleView titleView;
    private FrameLayout mBodyLayout;
    private ActivityManager activityManager;
    protected String defaultTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        activityId = getIntent().getStringExtra(IntentKeys.ACTIVITY_ID);
        activityManager = EleAppliction.getInstance().getActivityManager();
        if (getParent() == null) {
            activityManager.addToStack(this);
        }
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.pa_base_activity);
        initView();
        setDefaultTitleView();
        setTitleView(titleView);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (getParent() == null) {
            activityManager.addToStack(this);
        }
        activityId = intent.getStringExtra(IntentKeys.ACTIVITY_ID);
        setTitleView(titleView);
    }

    @Override
    public void finish() {
        super.finish();
        activityManager.removeFromStack(this);
    }

    public void finishActivity() {
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityManager.removeFromStack(this);
    }

    private void initView() {
        titleView = (PATitleView) findViewById(R.id.pa_base_title_view);
        mBodyLayout = (FrameLayout) findViewById(R.id.pa_base_body_layout);
        titleView.setOnTitleClickListener(this);
    }

    public void setDefaultTitleView() {
        titleView.setBackgroundColor(getResources().getColor(R.color.title_bg));
        titleView.setLeft(null, R.mipmap.btn_back);
        ActivityStruct activityStruct = ActivityMapping.getInstance().getActivityStruct(activityId);
        String title = getResources().getString(R.string.app_name);
        if (activityStruct != null && activityStruct.getTitle() != null) {
            title = activityStruct.getTitle();
        }
        defaultTitle = title;
        titleView.setCenter(title, 0);
        titleView.setRight(null, 0);
        titleView.show(true);
    }

    public ActivityStruct getActivityStruct() {
        return ActivityMapping.getInstance().getActivityStruct(activityId);
    }

    public abstract void setTitleView(PATitleView titleView);

    public String getActivityId() {
        return activityId;
    }


    @Override
    public void setContentView(int layoutResID) {
        mBodyLayout.removeAllViews();
        getLayoutInflater().inflate(layoutResID, mBodyLayout);
    }

    @Override
    public void setContentView(View view) {
        mBodyLayout.removeAllViews();
        mBodyLayout.addView(view);
    }

    public void setContentView(Class<? extends Fragment> fragmentClass) {
        setContentView(fragmentClass, null);
    }

    public void setContentView(Class<? extends Fragment> fragmentClass, Bundle bundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = Fragment.instantiate(this, fragmentClass.getName());
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.pa_base_body_layout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onTitleClick(int which, View view) {
        switch (which) {
            case PATitleView.TITLE_LEFT:
                finish();
                break;
        }
    }

    public PATitleView getTitleView() {
        return titleView;
    }

    public void setTitle(String title) {
        titleView.setCenter(title, 0);
    }


}
