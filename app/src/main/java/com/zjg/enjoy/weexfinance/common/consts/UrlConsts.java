package com.zjg.enjoy.weexfinance.common.consts;


import com.zjg.enjoy.weexfinance.common.util.SiteHelper;

/**
 * Created by zhengjinguang829 on 2017-03-10.
 */
public class UrlConsts {
    public static String getHost(String module) {
        return SiteHelper.getInstance().getHttpSite(module);
    }

    public interface REST {
        String REST_HOST = getHost("rest");
        //转签创业板接口
        String SIGN_AGREEMENT_API = REST_HOST + "/restapi/cyb/getstatus";

        //短链
        String SHORT_LINK = REST_HOST + "/restapi/servicecenter/commonUtils/createShortLink";

    }
}
