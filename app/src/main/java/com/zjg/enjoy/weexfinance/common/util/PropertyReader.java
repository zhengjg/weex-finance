package com.zjg.enjoy.weexfinance.common.util;


import com.zjg.enjoy.weexfinance.R;
import com.zjg.enjoy.weexfinance.application.EleAppliction;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by yanghongbing on 16/2/24.
 */
public class PropertyReader {

    public static Properties getProperty(int resId, boolean encrypt) {
        InputStream is = EleAppliction.getContext().getResources().openRawResource(R.raw.build_env);//AppUtils.openEncryptRawFile(resId, encrypt);;
        if (is == null) {
            return null;
        }
        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(is, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static Map<String, String> loadKeyValues(int resId, boolean isEncrypt) {
        Properties properties = getProperty(resId, isEncrypt);
        Map<String, String> maps = new HashMap<String, String>();
        if (properties != null) {
            Set<Object> keys = properties.keySet();
            for (Object key : keys) {
                String value = properties.getProperty((String) key);
                maps.put((String) key, value);
            }
        }
        return maps;
    }
}
