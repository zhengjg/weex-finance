package com.zjg.enjoy.weexfinance.data.cache;



import com.zjg.enjoy.weexfinance.common.util.SpUtils;

import java.util.Set;


public class PreferenceCacheManager extends AbstractCacheManager {

    private final String CACHE_FILE_NAME = "pazqcache";
    private SpUtils spUtils;

    public PreferenceCacheManager() {
        super();
        spUtils = new SpUtils(CACHE_FILE_NAME);
    }

    @Override
    public String getCache(String key) {
        return spUtils.getString(key, null);
    }

    @Override
    public void setCache(String key, Object value) {
        spUtils.setString(key, (String) value);
    }

    public void removeCache(String key) {
        if(getCache(key) != null) {
            spUtils.remove(key);
        }
    }

    public void clearCache() {
        spUtils.clear();
    }

    public void clearUserCache() {
        Set<String> keys = spUtils.getAllKeys();
        for (String key : keys) {
            if (key != null && key.startsWith(CacheKeys.USER_PREFIX)) {
                spUtils.remove(key);
            }
        }
    }
}
