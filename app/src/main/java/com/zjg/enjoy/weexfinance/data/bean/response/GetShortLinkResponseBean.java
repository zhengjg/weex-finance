package com.zjg.enjoy.weexfinance.data.bean.response;


/**
 * Created by chenjinbo on 16/12/19.
 */

public class GetShortLinkResponseBean extends PAResponseBaseBean {


    public Object actionAuth;
    public String errmsg;
    public String requestid;
    public ResultsBean results;

    public static class ResultsBean {

        public String key;
        public String source;
        public String url;
    }
}
