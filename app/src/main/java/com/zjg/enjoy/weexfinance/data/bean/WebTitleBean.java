package com.zjg.enjoy.weexfinance.data.bean;

/**
 * Created by YANGHONGBING615 on 2015/7/8.
 */
public class WebTitleBean {

    public TitleBean left;
    public TitleBean center;
    public TitleBean right;

    public class TitleBean {
        public int icontype;

        public String callback;

        public String text;
    }
}
