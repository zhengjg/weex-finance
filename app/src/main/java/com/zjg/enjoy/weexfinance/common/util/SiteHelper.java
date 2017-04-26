package com.zjg.enjoy.weexfinance.common.util;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Xml;


import com.zjg.enjoy.weexfinance.R;
import com.zjg.enjoy.weexfinance.application.EleAppliction;
import com.zjg.enjoy.weexfinance.data.bean.Site;
import com.zjg.enjoy.weexfinance.data.bean.SiteHost;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yanghongbing on 16/2/22.
 */
public class SiteHelper {

    private static volatile SiteHelper instance;
    private SiteHost mSiteHost;

    public static SiteHelper getInstance() {
        if (instance == null) {
            synchronized (SiteHelper.class) {
                if (instance == null) {
                    instance = new SiteHelper();
                }
            }
        }
        return instance;
    }

    private SiteHelper() {
        try {
            getDefaultSites();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getHttpSite(String name) {
        List<Site> sites = getSite("http", name);
        if (sites != null && sites.size() > 0) {
            if (sites.size() == 1) {
                return sites.get(0).getValue();
            } else {
                String fatport = BuildEnviroment.getHtmlFatPort();
                if ("fat39999".equals(fatport)) {
                    return sites.get(0).getValue();
                } else {
                    return sites.get(1).getValue();
                }
            }
        }
        return null;
    }

    private List<Site> getSite(String field, String name) {
        List<Site> fieldSite = null;
        if ("macs".equals(field)) {
            fieldSite = mSiteHost.getMacs();
        } else if ("http".equals(field)) {
            fieldSite = mSiteHost.getHttp();
        } else if ("quote".equals(field)) {
            fieldSite = mSiteHost.getQuote();
        } else if ("dzhQuote".equals(field)) {
            fieldSite = mSiteHost.getDzhQuote();
        } else if ("tcp".equals(field)) {
            fieldSite = mSiteHost.getTcp();
        }
        if (fieldSite == null) {
            return null;
        }
        List<Site> sites = new ArrayList<Site>(fieldSite);
        String env = BuildEnviroment.getBuildEnv();
        Iterator<Site> iterator = sites.iterator();
        while(iterator.hasNext()) {
            Site site = iterator.next();
            if (!env.equals(site.getType())) {
                iterator.remove();
            }
        }
        if (name != null) {
            iterator = sites.iterator();
            while (iterator.hasNext()) {
                Site site = iterator.next();
                if (!name.equals(site.getName())) {
                    iterator.remove();
                }
            }
        }
        return sites;
    }

    public void getDefaultSites() throws Exception {
        if (mSiteHost == null) {
            Site site = null;
            //InputStream is = AppUtils.openEncryptRawFile(R.raw.site, true);
            InputStream is = EleAppliction.getInstance().getResources().openRawResource(R.raw.site);
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(is, "UTF-8");
            int eventType = parser.getEventType();
            String field = null;
            while(eventType != XmlPullParser.END_DOCUMENT) {
                switch(eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        mSiteHost = new SiteHost();
                        break;
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("host")) {
                            field = parser.getAttributeValue(null, "field");
                        } else if (parser.getName().equals("site")) {
                            site = new Site();
                        } else if (parser.getName().equals("type")){
                            eventType = parser.next();
                            site.setType(parser.getText());
                        } else if (parser.getName().equals("name")) {
                            eventType = parser.next();
                            site.setName(parser.getText());
                        } else if (parser.getName().equals("value")) {
                            eventType = parser.next();
                            site.setValue(parser.getText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("site")) {
                            if ("macs".equals(field)) {
                                mSiteHost.getMacs().add(site);
                            } else if ("http".equals(field)) {
                                mSiteHost.getHttp().add(site);
                            } else if ("quote".equals(field)) {
                                mSiteHost.getQuote().add(site);
                            } else if ("dzhQuote".equals(field)) {
                                mSiteHost.getDzhQuote().add(site);
                            } else if ("tcp".equals(field)) {
                                mSiteHost.getTcp().add(site);
                            }
                        }
                        break;
                }
                eventType = parser.next();
            }
        }
    }
}
