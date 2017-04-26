package com.zjg.enjoy.weexfinance.common.util;

import android.net.Uri;

import com.alibaba.fastjson.JSON;
import com.zjg.enjoy.weexfinance.common.consts.PinganConsts;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZHENGJINGUANG829 on 2017-02-20.
 */
public class UrlUtils {
    public static String getScheme(String urlStr) {
        try {
            Uri uri = Uri.parse(urlStr);
            if (uri == null) {
                return null;
            }
            return uri.getScheme();
        } catch (Throwable throwable) {
            return null;
        }
    }

    public static String getHost(String urlStr) {
        String newUrl = decode(urlStr);
        Uri uri = Uri.parse(newUrl);
        if (uri == null) {
            return null;
        }
        return uri.getHost();
    }

    public static boolean isHappyUrl(String url) {
        String scheme = getScheme(url);
        if (PinganConsts.HPAAY_SCHEME.equals(scheme)) {
            return true;
        }
        return false;
    }

    public static boolean isWebUrl(String url) {
        if (url == null) return false;
        if (url.startsWith("http://") || url.startsWith("https://"))
            return true;
        return false;
    }

    public static boolean isModuleUrl(String url) {
        if (url == null) return false;
        if (url.startsWith("http://") || url.startsWith("https://") || url.startsWith("file://"))
            return true;
        return false;
    }

    public static String getAction(String url) {
        if (url == null) return null;
        String[] parts = url.split("[?]");
        int start = parts[0].lastIndexOf("/");
        if (start < 0) return null;
        if (start >= parts[0].length() - 1)
            return null;
        return parts[0].substring(start + 1);
    }

    public static String getParam(String urlStr, String key) {
        String params = getParams(UrlUtils.decode(urlStr));
        if(params == null) {
            return null;
        }
        JSONObject json;
        String param = null;
        try {
            json = new JSONObject(params);
            param = json.getString(key);
            //param = URLDecoder.decode(param);
        } catch (JSONException e1) {
            // TODO Auto-generated catch block
            //e1.printStackTrace();
        }
        return param;
    }

    public static Map<String, String> getParamsMap(String url) {
        String params = getParams(url);
        if(params == null) {
            return null;
        }
        try {
            params = URLDecoder.decode(params, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String,String> paramMap = (Map<String,String>) JSON.parse(params);
        return paramMap;
    }


    public static String getParams(String urlStr) {
        if (urlStr == null) {
            return null;
        }
        //urlStr = decode(urlStr);
        int index = urlStr.indexOf("?");
        if (index >= 0 && index != urlStr.length() - 1) {
            return urlStr.substring(index + 1);
        }
        return null;
    }

    public static Map<String, String> getParamMap(String url) {
        String params = getParams(url);
        if (params == null) {
            return null;
        }
        Map<String, String> paramMap = new HashMap<String, String>();
        String[] keyValues = params.split("&");
        for (String keyValue : keyValues) {
            int index = keyValue.indexOf("=");
            String key = keyValue.substring(0, index);
            String value = "";
            if (index >= 0 && index < keyValue.length() - 1) {
                value = keyValue.substring(index + 1);
            }
            paramMap.put(key, decode(value));
//            String[] kv = keyValue.split("=");
//            if(kv.length == 1) {
//                paramMap.put(kv[0], "");
//            } else {
//                paramMap.put(kv[0], URLDecoder.decode(kv[1]));
//            }
        }
        return paramMap;
    }


    public static String encode(String url) {
        String encoded = url;
        try {
            encoded = URLEncoder.encode(url, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encoded;
    }

    public static String decode(String url) {
        String decodeUrl = url;
        try {
            decodeUrl = URLDecoder.decode(url, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodeUrl;
    }
}
