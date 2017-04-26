package com.zjg.enjoy.weexfinance.data.cache;

/**
 * Created by yanghongbing on 16/4/7.
 */
public abstract class AbstractCacheManager {

    private CacheStatusChangeHelper cacheStatusHelper;
    
    public AbstractCacheManager() {
        cacheStatusHelper = new CacheStatusChangeHelper();
    }

    public abstract Object getCache(String key);

    public abstract void setCache(String key, Object value);

    public abstract void removeCache(String key);

    public abstract void clearCache();

    public abstract void clearUserCache();

    public CacheStatusChangeHelper getCacheStatusHelper() {
        return cacheStatusHelper;
    }
}
