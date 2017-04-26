package com.zjg.enjoy.weexfinance.data.cache;

/**
 * Created by yanghongbing on 16/4/7.
 */
public interface OnCacheStatusChangedListener {
    void onCacheStatusChanged(int cacheMode, String cacheKey, String value);
}
