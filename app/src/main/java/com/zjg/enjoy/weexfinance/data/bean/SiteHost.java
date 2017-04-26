package com.zjg.enjoy.weexfinance.data.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanghongbing on 16/2/22.
 */
public class SiteHost implements Serializable {

    private List<Site> macs;

    private List<Site> http;

    private List<Site> quote;

    private List<Site> dzhQuote;

    private List<Site> tcp;

    public SiteHost() {
        macs = new ArrayList<Site>();
        http = new ArrayList<Site>();
        quote = new ArrayList<Site>();
        dzhQuote = new ArrayList<Site>();
        tcp = new ArrayList<Site>();
    }

    public List<Site> getMacs() {
        return macs;
    }

    public void setMacs(List<Site> macs) {
        this.macs = macs;
    }

    public List<Site> getHttp() {
        return http;
    }

    public void setHttp(List<Site> http) {
        this.http = http;
    }

    public List<Site> getQuote() {
        return quote;
    }

    public void setQuote(List<Site> quote) {
        this.quote = quote;
    }

    public void setDzhQuote(List<Site> dzhQuote) {
        this.dzhQuote = dzhQuote;
    }

    public List<Site> getDzhQuote() {
        return dzhQuote;
    }

    public List<Site> getTcp() {
        return tcp;
    }

    public void setTcp(List<Site> tcp) {
        this.tcp = tcp;
    }
}
