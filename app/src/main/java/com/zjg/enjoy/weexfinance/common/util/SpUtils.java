package com.zjg.enjoy.weexfinance.common.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.zjg.enjoy.weexfinance.application.EleAppliction;

import java.util.Map;
import java.util.Set;

/**
 * Created by YANGHONGBING615 on 2015/7/22.
 */
public class SpUtils {

    private SharedPreferences sharedPreferences;

    public SpUtils(String fileName) {
        sharedPreferences = EleAppliction.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public String getString(String key, String defaultValue) {
        String value = sharedPreferences.getString(key, defaultValue);
        return value;
    }

    public Set<String> getAllKeys() {
        Map<String, String> all = (Map<String, String>) sharedPreferences.getAll();
        return all.keySet();
    }

    public void setString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void remove(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
