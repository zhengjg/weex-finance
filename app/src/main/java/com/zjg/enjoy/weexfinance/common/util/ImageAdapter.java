package com.zjg.enjoy.weexfinance.common.util;

import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;
import com.zjg.enjoy.weexfinance.application.EleAppliction;

/**
 * Created by zhengjinguang on 2017/4/26.
 */
public class ImageAdapter implements IWXImgLoaderAdapter {
    BitmapUtils imageUtils = new BitmapUtils(EleAppliction.getContext());

    @Override
    public void setImage(String url, ImageView view, WXImageQuality quality, WXImageStrategy strategy) {
        imageUtils.display(view, url);
    }
}
