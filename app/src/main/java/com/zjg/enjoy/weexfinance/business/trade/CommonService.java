package com.zjg.enjoy.weexfinance.business.trade;


import com.zjg.enjoy.weexfinance.data.bean.response.GetShortLinkResponseBean;
import com.zjg.enjoy.weexfinance.listener.IDataCallBack;
import com.zjg.enjoy.weexfinance.net.http.HttpListenerImpl;
import com.zjg.enjoy.weexfinance.net.http.HttpManager;

/**
 * Created by zhengjinguang829 on 2017-03-10.
 */
public class CommonService {

    public static void getShortLinkBean(IDataCallBack callBack, String url) {
        HttpListenerImpl httpListener = new HttpListenerImpl(GetShortLinkResponseBean.class, callBack);
        HttpManager.getShortLinkBean(httpListener, url);
    }
}
