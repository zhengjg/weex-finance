package com.zjg.enjoy.weexfinance.common.widget;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;


import com.zjg.enjoy.weexfinance.R;

import java.lang.ref.WeakReference;

/**
 * Created by YANGHONGBING615 on 2015-09-10.
 */
public class PAPopupWindow extends PopupWindow implements PopupWindow.OnDismissListener {

    private Activity activity;
    private boolean cancelable;
    private int[] mLocationInWindowPosition = new int[2];
    private WeakReference<View> mBelowPositionView;
    private int mBelow;
    private View mDarkView;
    private int mScreenWidth, mScreenHeight;
    private boolean mIsDarkInvoked = false;
    private WindowManager mWindowManager;

    public PAPopupWindow(Activity activity) {
        super(activity);
        this.activity = activity;
        this.cancelable = true;
        mWindowManager = (WindowManager) activity.getSystemService(activity.WINDOW_SERVICE);
        init();
    }

    private void init() {
        this.setFocusable(true);
        this.setOnDismissListener(this);
        this.setOutsideTouchable(true);
        Drawable d = activity.getResources().getDrawable(R.mipmap.ic_launcher);
        d.setAlpha(1);
        this.setBackgroundDrawable(d);
    }

    @Override
    public void showAsDropDown(View anchor) {
        super.showAsDropDown(anchor);
        backgroundAlpha(0.5f);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        super.showAsDropDown(anchor, xoff, yoff);
        backgroundAlpha(0.5f);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        backgroundAlpha(0.5f);
    }

    public void showshowAsDropDownWithoutAlpha(View anchor) {
        super.showAsDropDown(anchor);
        backgroundAlpha(1f);
    }

    /**
     * dark below target view
     *
     * @param view
     */
    public void darkBelow(View view) {
        if (null == view) {
            return;
        }
        mBelowPositionView = new WeakReference<>(view);
        view.getLocationInWindow(mLocationInWindowPosition);
        mBelow = mLocationInWindowPosition[1] + view.getHeight();

        DisplayMetrics dm = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;
        if (mIsDarkInvoked || getContentView() == null) {
            return;
        }
        WindowManager.LayoutParams darkLP = createDarkLayout(view.getWindowToken());
        darkLP.height = mScreenHeight - mBelow;
        darkLP.y = mBelow;
        mDarkView = new View(activity);
        mDarkView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        mDarkView.setBackgroundColor(Color.parseColor("#000000"));
        mDarkView.setAlpha(0.4f);
        mWindowManager.addView(mDarkView, darkLP);
        mIsDarkInvoked = true;
    }

    public void removeBelow() {
        if (mDarkView != null && mIsDarkInvoked) {
            mWindowManager.removeViewImmediate(mDarkView);
            mIsDarkInvoked = false;
        }
    }

    /**
     * create dark layout
     *
     * @param token
     * @return
     */
    private WindowManager.LayoutParams createDarkLayout(IBinder token) {
        WindowManager.LayoutParams p = new WindowManager.LayoutParams();
        p.gravity = Gravity.START | Gravity.TOP;
        p.width = WindowManager.LayoutParams.MATCH_PARENT;
        p.height = WindowManager.LayoutParams.MATCH_PARENT;
        p.format = PixelFormat.TRANSLUCENT;
        p.flags = computeFlags(p.flags);
        p.type = WindowManager.LayoutParams.LAST_SUB_WINDOW;
        p.token = token;
        p.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_UNCHANGED;
        return p;
    }

    private int computeFlags(int curFlags) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            curFlags &= ~(
                    WindowManager.LayoutParams.FLAG_IGNORE_CHEEK_PRESSES |
                            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE |
                            WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |
                            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |
                            WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM |
                            WindowManager.LayoutParams.FLAG_SPLIT_TOUCH);
        } else {
            curFlags &= ~(
                    WindowManager.LayoutParams.FLAG_IGNORE_CHEEK_PRESSES |
                            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE |
                            WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |
                            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |
                            WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        }
        curFlags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        curFlags |= WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        return curFlags;
    }


    @Override
    public void onDismiss() {
        backgroundAlpha(1f);
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        activity.getWindow().setAttributes(lp);
    }
}
