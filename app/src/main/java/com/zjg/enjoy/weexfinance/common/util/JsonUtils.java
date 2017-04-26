package com.zjg.enjoy.weexfinance.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by yanghongbing on 16/3/17.
 */
public class JsonUtils {

    public static <T> T parseObject(String text, Class<T> cls) {
        T t = null;
        try {
            t = JSON.parseObject(text, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static String toJSONString(Object obj) {
        String objString = null;
        try {
            objString = JSON.toJSONString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objString;
    }

    public static JSONArray parseJson(String json) {
        JSONArray jsonArray = null;
        try {
            jsonArray = JSON.parseArray(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static <T> List<T> parseArray(String json, Class<T> tClass) {
        List<T> list = null;
        try {
            list = JSON.parseArray(json, tClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static JSONObject parseJsonObject(String json) {
        JSONObject object = null;
        try {
            object = JSON.parseObject(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

}
