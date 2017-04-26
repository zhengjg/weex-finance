package com.zjg.enjoy.weexfinance.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjg.enjoy.weexfinance.R;


/**
 * Created by YANGHONGBING615 on 2015/7/8.
 */
public class PATitleView extends FrameLayout implements View.OnClickListener {

    private Context context;
    private LinearLayout mLeft;
    private LinearLayout mRight;
    private TextView mLeftText;
    private ImageView mLeftImage;
    private TextView mCenterView;
    private TextView mRightText;
    private ImageView mRightImage;
    private FrameLayout leftLayout;
    private FrameLayout centerLayout;
    private FrameLayout rightLayout;

    public static final int TITLE_LEFT = 1;
    public static final int TITLE_CENTER = 2;
    public static final int TITLE_RIGHT = 3;


    private OnTitleClickListener onTitleClickListener;

    public PATitleView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public PATitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public PATitleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    private void init() {
        inflate(context, R.layout.pa_title_layout, this);
        mLeftText = (TextView) findViewById(R.id.pa_title_left_text);
        mLeftImage = (ImageView) findViewById(R.id.pa_title_left_img);
        mLeft = (LinearLayout) findViewById(R.id.pa_title_left);
        mCenterView = (TextView) findViewById(R.id.pa_title_center);
        mRightText = (TextView) findViewById(R.id.pa_title_right_text);
        mRightImage = (ImageView) findViewById(R.id.pa_title_right_img);
        mRight = (LinearLayout) findViewById(R.id.pa_title_right);
        leftLayout = (FrameLayout) findViewById(R.id.pa_title_left_layout);
        centerLayout = (FrameLayout) findViewById(R.id.pa_title_center_layout);
        rightLayout = (FrameLayout) findViewById(R.id.pa_title_right_layout);
        mLeft.setOnClickListener(this);
        mCenterView.setOnClickListener(this);
        mRightImage.setOnClickListener(this);
        mRight.setOnClickListener(this);
        rightLayout.setOnClickListener(this);
    }

    public void show(boolean show) {
        if (show) {
            setVisibility(VISIBLE);
        } else {
            setVisibility(GONE);
        }
    }

    public void setDefault() {
        setLeft(null, R.mipmap.btn_back);
        //setCenter(context.getString(R.string.app_name), 0);
        //mRight.setVisibility(GONE);
        mLeft.setOnClickListener(this);
        //mCenterView.setOnClickListener(this);
        //mRight.setOnClickListener(this);

    }

    public void setLeftVisible(boolean isVisible) {
        if (isVisible) {
            leftLayout.setVisibility(View.VISIBLE);
        } else {
            leftLayout.setVisibility(View.GONE);
            mLeftText.setVisibility(GONE);
        }
    }

    public void setLeftVisibleWithText() {
        leftLayout.setVisibility(View.VISIBLE);
        mLeftText.setVisibility(VISIBLE);

    }

    public void setCenterVisible(boolean isVisible) {
        if (isVisible) {
            centerLayout.setVisibility(View.VISIBLE);
        } else {
            centerLayout.setVisibility(View.GONE);
            mCenterView.setVisibility(GONE);
        }
    }

    public void setRightVisible(boolean isVisible) {
        if (isVisible) {
            rightLayout.setVisibility(View.VISIBLE);
        } else {
            rightLayout.setVisibility(View.GONE);
            mRightText.setVisibility(GONE);
        }
    }

    public void setLeftView(View view) {
        leftLayout.removeAllViews();
        mLeft.setVisibility(GONE);
        if (view == null) return;
        leftLayout.addView(view);
        leftLayout.setVisibility(VISIBLE);
    }

    public void setCenterView(View view) {
        centerLayout.removeAllViews();
        mCenterView.setVisibility(GONE);
        if (view == null) return;
//        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//        lp.gravity = Gravity.CENTER;
//        view.setLayoutParams(lp);
        centerLayout.addView(view);
        centerLayout.setVisibility(VISIBLE);
    }

    public void setRightView(View view) {
        rightLayout.removeAllViews();
        mRight.setVisibility(GONE);
        if (view == null) return;
        rightLayout.addView(view);
        rightLayout.setVisibility(VISIBLE);
    }

    public void setLeft(String text, int resId) {
        leftLayout.setVisibility(GONE);
        mLeft.setVisibility(VISIBLE);
        mLeftText.setText("");
        mLeftImage.setImageResource(0);
        if (TextUtils.isEmpty(text)) {
            mLeftText.setText("");
            mLeftText.setVisibility(GONE);
        } else {
            mLeftText.setText(text);
            mLeftText.setVisibility(VISIBLE);
        }
        if (resId > 0) {
            mLeftImage.setImageResource(resId);
            mLeftImage.setVisibility(VISIBLE);
        } else {
            mLeftImage.setVisibility(GONE);
        }
    }

    public void setLeft(int textResId, int drawableResId) {
        setLeft(getResources().getString(textResId), drawableResId);
    }

    public void setCenter(String text, int resId) {
        if (!TextUtils.isEmpty(text) && resId > 0) {
            mCenterView.setText(text);
            mCenterView.setBackgroundColor(0);
            mCenterView.setCompoundDrawablesWithIntrinsicBounds(0, 0, resId, 0);
            mCenterView.setCompoundDrawablePadding(5);
            mCenterView.setVisibility(VISIBLE);
        } else {
            setResource(mCenterView, text, resId);
        }
        centerLayout.setVisibility(GONE);
    }

    public void setCenter(int textResId, int drawableResId) {
        setCenter(getResources().getString(textResId), drawableResId);
    }

    public void setRight(String text, int resId) {
        rightLayout.setVisibility(GONE);
        mRight.setVisibility(VISIBLE);
        mRightImage.setImageResource(0);
        if (TextUtils.isEmpty(text)) {
            mRightText.setText("");
            mRightText.setVisibility(GONE);
        } else {
            mRightText.setText(text);
            mRightText.setVisibility(VISIBLE);
        }
        if (resId > 0) {
            mRightImage.setImageResource(resId);
            mRightImage.setVisibility(VISIBLE);
        } else {
            mRightImage.setVisibility(GONE);
        }
    }

    public void setRight(int textResId, int drawableResId) {
        setRight(getResources().getString(textResId), drawableResId);
    }

    public void setCenterRightView(int resId) {
        mRightText.setCompoundDrawablesWithIntrinsicBounds(0, 0, resId, 0);
        mRightText.setCompoundDrawablePadding(3);
    }

    public void setResource(TextView tv, String text, int resId) {
        if (TextUtils.isEmpty(text) && resId <= 0) {
            tv.setText("");
            tv.setBackgroundResource(0);
            tv.setVisibility(GONE);
        } else {
            tv.setText(text);
            tv.setBackgroundResource(resId);
            tv.setVisibility(VISIBLE);
        }
    }

    public void setOnTitleClickListener(OnTitleClickListener onTitleClickListener) {
        this.onTitleClickListener = onTitleClickListener;
    }

    @Override
    public void onClick(View view) {
        int which = -1;
        switch (view.getId()) {
            case R.id.pa_title_left:
            case R.id.pa_title_left_layout:
                which = TITLE_LEFT;
                break;
            case R.id.pa_title_center:
            case R.id.pa_title_center_layout:
                which = TITLE_CENTER;
                break;
            case R.id.pa_title_right_img:
            case R.id.pa_title_right:
            case R.id.pa_title_right_layout:
                which = TITLE_RIGHT;
                break;
        }
        if (onTitleClickListener != null) {
            onTitleClickListener.onTitleClick(which, view);
        }
    }

    public View getView(int which) {
        if (which == TITLE_LEFT)
            return mLeftText.getVisibility() == VISIBLE ? mLeftText : leftLayout;
        if (which == TITLE_CENTER)
            return mCenterView.getVisibility() == VISIBLE ? mCenterView : centerLayout;
        if (which == TITLE_RIGHT)
            return mRightText.getVisibility() == VISIBLE ? mRightText : rightLayout;
        return null;
    }

    public interface OnTitleClickListener {
        public void onTitleClick(int which, View view);
    }
}
