package com.zjg.enjoy.weexfinance.data.cache;

/**
 * Created by YANGHONGBING615 on 2015/7/22.
 */
public class CacheManager {

    public static final int MODE_MEMORY = 1;
    public static final int MODE_PREFERENCE = 2;
    public static final int MODE_JSON_FILE = 4;
    public static final int MODE_DB = 8;
    public static final int MODE_ALL = MODE_MEMORY | MODE_PREFERENCE | MODE_DB | MODE_JSON_FILE;
    public static final int MODE_DEFAULT = MODE_MEMORY;

    private MemoryCacheManager memoryCacheManager;
    private PreferenceCacheManager preferenceCacheManager;
    private FileCacheManager jsonFileCache;

    public CacheManager(){
        memoryCacheManager = new MemoryCacheManager();
        preferenceCacheManager = new PreferenceCacheManager();
        jsonFileCache = new FileCacheManager();
    }

    public MemoryCacheManager getMemoryCacheManager() {
        return memoryCacheManager;
    }

    public void setCache(String key, String value) {
        setCache(MODE_DEFAULT, key, value);
    }

    public void setCache(int cacheMode, String key, String value) {
        if((cacheMode & MODE_MEMORY) == MODE_MEMORY) {
            memoryCacheManager.setCache(key, value);
        }
        if((cacheMode & MODE_PREFERENCE) == MODE_PREFERENCE) {
            preferenceCacheManager.setCache(key, value);
        }
        if((cacheMode & MODE_JSON_FILE) == MODE_JSON_FILE){
            jsonFileCache.setCache(key,value);
        }
    }

    public String getCache(String key) {
        return getCache(MODE_DEFAULT, key);
    }

    public String getCache(int cacheMode, String key) {
        String value = null;
        if((cacheMode & MODE_MEMORY) == MODE_MEMORY) {
            value = (String) memoryCacheManager.getCache(key);
        }
        if((cacheMode & MODE_PREFERENCE) == MODE_PREFERENCE) {
            value = preferenceCacheManager.getCache(key);
        }
        if((cacheMode & MODE_JSON_FILE) == MODE_JSON_FILE){
            value = jsonFileCache.getCache(key);
        }
        return value;
    }

    public void removeCache(String key) {
        removeCache(MODE_DEFAULT, key);
    }

    public void removeCache(int cacheMode, String key) {
        if((cacheMode & MODE_MEMORY) == MODE_MEMORY) {
            memoryCacheManager.removeCache(key);
        }
        if((cacheMode & MODE_PREFERENCE) == MODE_PREFERENCE) {
            preferenceCacheManager.removeCache(key);
        }
        if((cacheMode & MODE_JSON_FILE) == MODE_JSON_FILE) {
            jsonFileCache.removeCache(key);
        }
    }

    public void clear(int cacheMode) {
        if((cacheMode & MODE_MEMORY) == MODE_MEMORY) {
            memoryCacheManager.clearCache();
        }
        if((cacheMode & MODE_PREFERENCE) == MODE_PREFERENCE) {
            preferenceCacheManager.clearCache();
        }
        if((cacheMode & MODE_JSON_FILE) == MODE_JSON_FILE) {
            jsonFileCache.clearCache();
        }

    }

    public void clearUserCache() {
        memoryCacheManager.clearUserCache();
        preferenceCacheManager.clearUserCache();
        jsonFileCache.clearUserCache();
    }

    public void clearAll() {
        memoryCacheManager.clearCache();
        preferenceCacheManager.clearCache();
        jsonFileCache.clearCache();
    }

    public void setOnCacheStatusChangedListener(int cacheMode, String cacheKey, OnCacheStatusChangedListener listener) {
        if ((cacheMode & MODE_MEMORY) == MODE_MEMORY) {
            memoryCacheManager.getCacheStatusHelper().addListener(cacheKey, listener);
        }
        if ((cacheMode & MODE_PREFERENCE) == MODE_PREFERENCE) {
            preferenceCacheManager.getCacheStatusHelper().addListener(cacheKey, listener);
        }
        if ((cacheMode & MODE_JSON_FILE) == MODE_JSON_FILE) {
            jsonFileCache.getCacheStatusHelper().addListener(cacheKey, listener);
        }
    }

    public void setOnCacheStatusChangedListener(String cacheKey, OnCacheStatusChangedListener onCacheStatusChangedListener) {
        setOnCacheStatusChangedListener(CacheManager.MODE_ALL, cacheKey, onCacheStatusChangedListener);
    }
}
