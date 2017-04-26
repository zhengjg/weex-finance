package com.zjg.enjoy.weexfinance.net.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Created by cloud on 7/17/15
 */
public class HttpResponse {

    private int httpId;

    private String url;
    /** http response content **/
    private String responseBody;

    private Map<String, Object> responseHeaders;

    private Map<String, List<String>> requestProperties;

    private int resCode;

    private int responseCode = -1;

    public Map<String, List<String>> getRequestProperties() {
        return requestProperties;
    }

    public void setRequestProperties(Map<String, List<String>> requestProperties) {
        this.requestProperties = requestProperties;
    }


    public HttpResponse(){
        responseHeaders = new HashMap<String, Object>();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public int getHttpId() {
        return httpId;
    }

    public void setHttpId(int httpId) {
        this.httpId = httpId;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

}
