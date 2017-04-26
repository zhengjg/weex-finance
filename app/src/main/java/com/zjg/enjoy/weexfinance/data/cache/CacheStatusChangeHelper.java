package com.zjg.enjoy.weexfinance.data.cache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yanghongbing on 16/4/7.
 */
public class CacheStatusChangeHelper {

    protected List<Subscriber> subscribeList;

    public CacheStatusChangeHelper() {
        subscribeList = new ArrayList<Subscriber>();
    }

    public void addListener(String key, OnCacheStatusChangedListener listener) {
        if (key == null || listener == null) {
            return;
        }
        Subscriber subscriber = new Subscriber();
        subscriber.cacheKey = key;
        subscriber.listener = listener;
        subscribeList.add(subscriber);
    }

    public void removeListener(String key, OnCacheStatusChangedListener listener) {
        if (subscribeList == null || subscribeList.isEmpty()) {
            return;
        }
        Iterator<Subscriber> iterator = subscribeList.iterator();
        while (iterator.hasNext()) {
            Subscriber subscriber = iterator.next();
            if (key.equals(subscriber.cacheKey) && listener == subscriber.listener) {
                iterator.remove();
            }
        }
    }

    public void notifySubscriber(int cacheMode, String key, String value) {
        if (subscribeList == null || subscribeList.isEmpty()) {
            return;
        }
        Iterator<Subscriber> iterator = subscribeList.iterator();
        while (iterator.hasNext()) {
            Subscriber subscriber = iterator.next();
            if (key.equals(subscriber.cacheKey)) {
                subscriber.listener.onCacheStatusChanged(cacheMode, key, value);
            }
        }
    }

    public class Subscriber {
        public String cacheKey;
        public OnCacheStatusChangedListener listener;
    }
}
