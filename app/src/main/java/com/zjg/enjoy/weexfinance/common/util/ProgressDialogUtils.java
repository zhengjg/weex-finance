package com.zjg.enjoy.weexfinance.common.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import com.zjg.enjoy.weexfinance.application.EleAppliction;


/**
 * Created by YANGHONGBING615 on 2015/7/7.
 */
public class ProgressDialogUtils {
    private static ProgressDialog mDiolog;

    public static void show(Context context) {
        show(context, "加载中...");
    }

    public static void show(Context context, int resId) {
        String string = EleAppliction.getInstance().getString(resId);
        show(context, string);
    }

    public static void show(Context context, String msg) {
        show(context, msg, false);
    }

    public static void show(Context context, String msg, boolean cel) {
        if(mDiolog != null && mDiolog.isShowing()) {
            try {
                mDiolog.dismiss();
            } catch (Exception e) {

            }
        }

        if(context != null && !((Activity)context).isFinishing()){
            mDiolog = ProgressDialog.show(context, null, msg);
            mDiolog.setCancelable(true);
            mDiolog.setCanceledOnTouchOutside(cel);
        }
    }

    public static void dismiss() {
        if(mDiolog!= null && mDiolog.isShowing()) {
            try {
                mDiolog.dismiss();
                mDiolog = null;
            } catch (Exception e) {

            }
        }
    }


}
