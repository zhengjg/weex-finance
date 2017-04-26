package com.zjg.enjoy.weexfinance.net.http;



import com.zjg.enjoy.weexfinance.common.util.JsonUtils;
import com.zjg.enjoy.weexfinance.data.bean.PABaseBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequest {

    private int httpId;
    private String url;
    private HttpUtils.HttpListener httpListener;
    private PABaseBean paramsBean;
    private Map<String, Object> paramsMap;

    //给某个请求设置单独的超时时间
    private int timeOutInMilliseconds = 0;

    //头信息
    public Map<String, List<String>> headMap;


    public HttpRequest(int httpId, String url) {
        this(httpId, url, (Map<String, Object>) null);
    }

    public HttpRequest(int httpId, String url, PABaseBean paramsBean) {
        this.url = url;
        this.httpId = httpId;
        this.paramsBean = paramsBean;
        parseParams(this.paramsBean);
        headMap = new HashMap<String, List<String>>();
    }

    public HttpRequest(int httpId, String url, Map<String, Object> params) {
        this.url = url;
        this.httpId = httpId;
        this.paramsMap = params;
        headMap = new HashMap<String, List<String>>();
    }

    //设置map格式的参数
    public void setParams(Map<String, Object> params) {
        this.paramsMap = params;
    }

    //设置PABaseBean格式的参数
    public void setParams(PABaseBean bean) {
        this.paramsBean = bean;
        parseParams(bean);
    }

    //设置string格式的参数
    public void setParasMap(String parasMap) {
        if (this.paramsMap == null) {
            this.paramsMap = new HashMap<>();
        }
        this.paramsMap = JsonUtils.parseObject(parasMap, HashMap.class);
    }

    public String getUrl() {
        return url;
    }

    public int getHttpId() {
        return httpId;
    }


    public HttpUtils.HttpListener getHttpCallBack() {
        return httpListener;
    }


    public void setHttpCallBack(HttpUtils.HttpListener httpListener) {
        this.httpListener = httpListener;

    }


    /**
     * 获取string格式的参数
     */
    public String getParamsString() {
        if (paramsMap == null) {
            return null;
        }
        return JsonUtils.toJSONString(paramsMap);
    }

    /**
     * 获取Map格式的参数
     */
    public Map<String, Object> getParamsMap() {
        return paramsMap;
    }

    /**
     * 获取头信息
     */
    public Map<String, List<String>> getHeadMap() {
        return headMap;
    }

    /**
     * 设置头信息
     */
    public void setHeadMap(Map<String, List<String>> headMap) {
        this.headMap = headMap;
    }


    public void addHeader(String key, String value) {
        ArrayList<String> values = new ArrayList<String>();
        values.add(value);
        headMap.put(key, values);
    }

    private void parseParams(PABaseBean bean) {
        if (bean != null) {
            String jsonString = bean.toString();
            if (paramsMap == null) {
                paramsMap = new HashMap<>();
            }
            this.paramsMap = JsonUtils.parseObject(jsonString, HashMap.class);
        }
    }

    public void setTimeOutInMilliseconds(int timeout) {
        this.timeOutInMilliseconds = timeout;
    }

    public int getTimeOutInMilliseconds() {
        return this.timeOutInMilliseconds;
    }
}
