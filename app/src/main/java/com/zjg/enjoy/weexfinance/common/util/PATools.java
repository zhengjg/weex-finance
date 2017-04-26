package com.zjg.enjoy.weexfinance.common.util;

import android.graphics.Bitmap;
import android.os.Environment;


import com.zjg.enjoy.weexfinance.R;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by ZHENGJINGUANG829 on 2017-02-23.
 */
public class PATools {

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 获取缓存图片目录
     */
    public static String getImageDirectory() {
        final String dirName = "/pazqHappy";
        String path = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_UNMOUNTED)) {
            path = Environment.getDownloadCacheDirectory().getPath();
        } else {
            path = Environment.getExternalStorageDirectory().getPath();
        }

        String filePath = path + dirName;
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdir();
        }

        return path + dirName;
    }

    /**
     * web设置native的title信息
     * **/
    public static int getImageByType(int type) {
        int[] imgList = {0, R.mipmap.btn_back, R.mipmap.btn_home, R.mipmap.btn_new_share,R.mipmap.trade_preset_entrust_refresh,R.mipmap.btn_list,R.mipmap.btn_right};
        if(type < 0 || type >= imgList.length) {
            return 0;
        }
        return imgList[type];
    }


    /**
     * 获取截图路径
     *
     * @return
     */
    public static String getScreenShotsPath() {
        return getImageDirectory() + "/" + "screenShots.png";
    }
}
