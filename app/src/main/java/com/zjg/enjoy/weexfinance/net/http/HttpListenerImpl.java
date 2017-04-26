package com.zjg.enjoy.weexfinance.net.http;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;


import com.zjg.enjoy.weexfinance.application.EleAppliction;
import com.zjg.enjoy.weexfinance.common.consts.PinganConsts;
import com.zjg.enjoy.weexfinance.common.consts.RESTResponseStatusCode;
import com.zjg.enjoy.weexfinance.common.util.JsonUtils;
import com.zjg.enjoy.weexfinance.common.util.ProgressDialogUtils;
import com.zjg.enjoy.weexfinance.common.util.Tools;
import com.zjg.enjoy.weexfinance.data.bean.PABaseBean;
import com.zjg.enjoy.weexfinance.data.bean.response.PAResponseBaseBean;
import com.zjg.enjoy.weexfinance.data.cache.CacheManager;
import com.zjg.enjoy.weexfinance.listener.IDataCallBack;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by YANGHONGBING615 on 2015/7/21.
 */
public class HttpListenerImpl implements HttpUtils.HttpListener {

    private Class<? extends PABaseBean> respBean;
    private IDataCallBack dataCallBack;
    private String cacheKey;
    private CacheManager cacheManager;
    private int cacheMode = CacheManager.MODE_MEMORY;
    private boolean dataIsList; //返回数据最外层是否是列表

    public HttpListenerImpl(Class<? extends PABaseBean> respBean, IDataCallBack dataCallBack) {
        this.respBean = respBean;
        WeakReference<IDataCallBack> weakReference = new WeakReference<IDataCallBack>(dataCallBack);
        this.dataCallBack = weakReference.get();
        cacheManager = EleAppliction.getInstance().getCacheManager();
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public void setCacheMode(int cacheMode) {
        this.cacheMode = cacheMode;
    }

    public void setDataIsList(boolean dataIsList) {
        this.dataIsList = dataIsList;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        Request okRequest = call.request();
        final HttpRequest request = (HttpRequest) okRequest.tag();
        Log.d(HttpConstants.TAG, "--------onFailure " + request.getHttpId() + "--------");
        Log.d(HttpConstants.TAG, "" + e.getMessage());
        Log.d(HttpConstants.TAG, "--------request " + request.getHttpId() + " end--------");
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                boolean isConsumed = false;
                if (dataCallBack != null) {
                    isConsumed = dataCallBack.onReceiveError(request.getHttpId(), null);
                }
                if (!isConsumed) {
                    String httpId = "";
                    if ((PinganConsts.BUILD_ENV.equals(PinganConsts.Build.FAT)
                            || PinganConsts.BUILD_ENV.equals(PinganConsts.Build.UAT))) {
                        httpId = "(" + request.getHttpId() + ")";
                    }
                    Tools.showToast("无法连接服务器，请稍后再试！" + httpId);
                }
            }
        });
    }

    @Override
    public void onResponse(Call call, Response okResponse) throws IOException {
        Request okRequest = okResponse.request();
        HttpRequest request = (HttpRequest) okRequest.tag();
        ResponseBody body = okResponse.body();
        String responseString = null;
        if (body != null) {
            responseString = body.string();
        }
        Log.d(HttpConstants.TAG, "--------onResponse " + request.getHttpId() + "--------");
        Log.d(HttpConstants.TAG, "response Code: " + okResponse.code());
        Log.d(HttpConstants.TAG, "response info : \n" + responseString);
        Log.d(HttpConstants.TAG, "--------request " + request.getHttpId() + " end--------");
        final HttpResponse response = new HttpResponse();
        response.setHttpId(request.getHttpId());
        response.setRequestProperties(request.getHeadMap());
        response.setResponseBody(responseString);
        response.setResponseCode(okResponse.code());
        response.setUrl(request.getUrl());
        List<String> session = okResponse.headers("Set-Cookie");
        List<String> sess = new ArrayList<>();
        if (session != null && !session.isEmpty()) {
            for (String ss : session) {
                int index = ss.indexOf(";");
                if (index != -1) {
                    sess.add(ss.substring(0, index));
                }
            }
        }
        Map<String, List<String>> headParams = new HashMap<>();
        headParams.put("Cookie", sess);
        response.setRequestProperties(headParams);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (response.getResponseCode() == 200) {
                    //网络层返回成功
                    String responseString = response.getResponseBody();
                    responseString = responseString.replaceAll("[\\n\\t\\r]", "");
                    Object resultBean = null;
                    if (dataIsList) {
                        resultBean = JsonUtils.parseArray(responseString, respBean);
                    } else {
                        resultBean = JsonUtils.parseObject(responseString, respBean);
                    }
                    if (resultBean != null) {
                        if (resultBean instanceof PABaseBean) {
                            PABaseBean bean = (PABaseBean) resultBean;
                            bean.session = response.getRequestProperties();
                        }
                        if (resultBean instanceof PAResponseBaseBean) {
                            PAResponseBaseBean responseBaseBean = (PAResponseBaseBean) resultBean;
                            if (responseBaseBean.status == RESTResponseStatusCode.SUCESS) {
                                if (dataCallBack != null) {
                                    dataCallBack.onDataRefresh(response.getHttpId(), resultBean);
                                }
                                if (cacheKey != null) {
                                    cacheManager.setCache(cacheMode, cacheKey, responseString);
                                }
                            } else {
                                boolean isConsumed = false;
                                if (dataCallBack != null) {
                                    isConsumed = dataCallBack.onReceiveError(response.getHttpId(), responseBaseBean);
                                    ProgressDialogUtils.dismiss();
                                }
                                if (!isConsumed) {
                                    if (!TextUtils.isEmpty(responseBaseBean.errmsg)) {
                                        Tools.showToast(responseBaseBean.errmsg);
                                    }
                                }
                            }
                        } else {
                            if (dataCallBack != null) {
                                dataCallBack.onDataRefresh(response.getHttpId(), resultBean);
                            }
                        }
                    }else {
                        if (dataCallBack != null) {
                            dataCallBack.onReceiveError(response.getHttpId(), null);
                        }
                    }
                } else {
                    //网络层返回失败
                    boolean isConsumed = false;
                    if (dataCallBack != null) {
                        isConsumed = dataCallBack.onReceiveError(response.getHttpId(), null);
                    }
                    if (!isConsumed) {
                        String httpId = "";
                        if (response != null &&
                                (PinganConsts.BUILD_ENV.equals(PinganConsts.Build.FAT)
                                        || PinganConsts.BUILD_ENV.equals(PinganConsts.Build.UAT))) {
                            httpId = "(" + response.getHttpId() + ")";
                        }
                        Tools.showToast("无法连接服务器，请稍后再试！" + httpId);
                    }
                }
            }
        });
    }
}
