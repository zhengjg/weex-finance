package com.zjg.enjoy.weexfinance.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zjg.enjoy.weexfinance.R;
import com.zjg.enjoy.weexfinance.common.util.ForwardUtils;

public class MainActivity extends AppCompatActivity {

    public static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.self_stock_wrap:
                Log.i(TAG, "self_stock_wrap");
                //ForwardUtils.forwardActivity(MainActivity.this, ActivityId.TEST_ACTIVITY, null);
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
                break;
            case R.id.information_wrap:
                //String infoUrl = "information/infos.js";
                String infoUrl= "information/app.js";
                ForwardUtils.forwardWeex(MainActivity.this, infoUrl, "资讯", null);
                break;
        }

    }
}
