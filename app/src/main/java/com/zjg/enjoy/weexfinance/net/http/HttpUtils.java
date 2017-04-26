package com.zjg.enjoy.weexfinance.net.http;

import android.util.Log;


import com.zjg.enjoy.weexfinance.common.util.AppUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtils {

    private static OkHttpClient httpClient;
    private static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    private static final MediaType FORM = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");

    static {
        httpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    public static void doHttpGet(HttpRequest httpRequest) {
        doHttpRequest(httpRequest, "GET");
    }

    public static void doHttpPost(HttpRequest httpRequest) {
        doHttpRequest(httpRequest, "POST");
    }

    public static void doHttpPut(HttpRequest httpRequest) {
        doHttpRequest(httpRequest, "PUT");
    }

    public static void doHttpDelete(HttpRequest httpRequest) {
        doHttpRequest(httpRequest, "DELETE");
    }

    private static void doHttpRequest(HttpRequest httpRequest, String method) {
        if (httpRequest == null) {
            return;
        }
        if (!isSupportedMethod(method)) {
            throw new RuntimeException("not supported http method for " + method);
        }
        String userAgent = "Dalvik/1.6.0(Linux;U;"+android.os.Build.MODEL+")";
        userAgent += " deviceinfo/A|"+ AppUtils.getVersion()+"|"+AppUtils.getMacAddress()+"|"+AppUtils.getImei();
        Request.Builder requestBuilder = new Request.Builder().url(httpRequest.getUrl());
        Map<String, List<String>> headers = httpRequest.getHeadMap();
        if (headers == null) {
            headers = new HashMap<>();
        }
        List<String> list = null;
        if (!headers.containsKey("Content-Type")) {
             list = new ArrayList<>();
            list.add("application/json");
            headers.put("Content-Type", list);
        }
        list = new ArrayList<>();
        list.add(userAgent);
        headers.put("User-Agent", list);
        if (headers != null && !headers.isEmpty()) {
            Set<String> keySet = headers.keySet();
            for (String key : keySet) {
                List<String> values = headers.get(key);
                if (values.size() == 1) {
                    requestBuilder.header(key, values.get(0));
                } else {
                    requestBuilder.removeHeader(key);
                    for (String value : values) {
                        requestBuilder.addHeader(key, value);
                    }
                }
            }
        }
        requestBuilder.tag(httpRequest);
        Log.d(HttpConstants.TAG, "--------Http Request " + httpRequest.getHttpId() + "--------");
        Log.d(HttpConstants.TAG, "url: " + httpRequest.getUrl());
        Log.d(HttpConstants.TAG, "method: " + method);
        Log.d(HttpConstants.TAG, "params:" + httpRequest.getParamsString());
        String headString = "";
        if (httpRequest.getHeadMap() != null) {
            headString = httpRequest.getHeadMap().toString();
        }
        Log.d(HttpConstants.TAG, "headers: " + headString);
        if (httpRequest.getParamsString() != null) {
            if (method.equals("POST")
                    || method.equals("PUT")
                    || method.equals("DELETE")) {
                RequestBody requestBody = null;
                List<String> contentTypes = headers.get("Content-Type");
                String mediaType = JSON.type() + "/" + JSON.subtype();
                if (contentTypes != null && !contentTypes.isEmpty()) {
                    mediaType = contentTypes.get(0);
                }
                if (mediaType.equals(FORM.type() + "/" + FORM.subtype())) {
                    FormBody.Builder builder = new FormBody.Builder();
                    Map<String, Object> params = httpRequest.getParamsMap();
                    if (params != null && !params.isEmpty()) {
                        for (String name : params.keySet()) {
                            builder.add(name, params.get(name).toString());
                        }
                    }
                    requestBody = builder.build();
                } else {
                    requestBody = RequestBody.create(JSON, httpRequest.getParamsString());
                }
                requestBuilder.method(method.toUpperCase(), requestBody);
            }
        }
        int timeOut = httpRequest.getTimeOutInMilliseconds();
        OkHttpClient client = httpClient;
        if (timeOut > 0) {
            client = httpClient.newBuilder().connectTimeout(timeOut, TimeUnit.MILLISECONDS)
                    .writeTimeout(timeOut, TimeUnit.MILLISECONDS)
                    .readTimeout(timeOut, TimeUnit.MILLISECONDS).build();
        }
        Call call = client.newCall(requestBuilder.build());
        call.enqueue(httpRequest.getHttpCallBack());
    }

    public static void postMultipartsRequest(HttpRequest httpRequest) {
        if (httpRequest == null) {
            return;
        }
        MultipartBody.Builder mBuilder = new MultipartBody.Builder();
        mBuilder.setType(MultipartBody.FORM);
        Map<String, Object> params = httpRequest.getParamsMap();
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                Object value = params.get(key);
                if (value instanceof File) {
                    File file = (File) value;
                    mBuilder.addFormDataPart(key, file.getName(), RequestBody.create(null, file));
                } else {
                    mBuilder.addFormDataPart(key, value.toString());
                }
            }
        }
        RequestBody body = mBuilder.build();
        Request request = new Request.Builder().url(httpRequest.getUrl()).post(body).tag(httpRequest).build();
        OkHttpClient client = httpClient.newBuilder().writeTimeout(60, TimeUnit.SECONDS).build();
        Call call = client.newCall(request);
        call.enqueue(httpRequest.getHttpCallBack());
    }

    public interface HttpListener extends Callback {

    }

    public static boolean isSupportedMethod(String method) {
        return "GET".equalsIgnoreCase(method)
                || "POST".equalsIgnoreCase(method)
                || "PUT".equalsIgnoreCase(method)
                || "DELETE".equalsIgnoreCase(method);
    }
}
