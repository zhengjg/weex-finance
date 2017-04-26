package com.zjg.enjoy.weexfinance.data.cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by YANGHONGBING615 on 2015/7/21.
 */
public class MemoryCacheManager extends AbstractCacheManager {

    private Map<String, Object> mCacheMap;

    public MemoryCacheManager() {
        super();
        mCacheMap = new HashMap<String, Object>();
    }

    @Override
    public Object getCache(String key) {
        return mCacheMap.get(key);
    }

    @Override
    public void removeCache(String key) {
        if(mCacheMap.containsKey(key)) {
            mCacheMap.remove(key);
        }
    }

    @Override
    public void setCache(String key, Object object) {
        mCacheMap.put(key, object);
    }

    public void clearCache() {
        mCacheMap.clear();
    }

    public void clearUserCache() {
        Iterator<String> iterator = mCacheMap.keySet().iterator();
        while(iterator.hasNext()) {
            String key = iterator.next();
            if (key != null && key.startsWith(CacheKeys.USER_PREFIX)) {
                iterator.remove();
            }
        }
    }
}
