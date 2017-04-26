package com.zjg.enjoy.weexfinance.common.util;

import android.text.TextUtils;


import com.zjg.enjoy.weexfinance.R;
import com.zjg.enjoy.weexfinance.application.EleAppliction;
import com.zjg.enjoy.weexfinance.data.cache.CacheKeys;
import com.zjg.enjoy.weexfinance.data.cache.CacheManager;

import java.util.Map;

/**
 * Created by yanghongbing on 16/2/22.
 */
public class BuildEnviroment {

    private static Map<String, String> buildEnvs;

    static {
        buildEnvs = PropertyReader.loadKeyValues(R.raw.build_env, false);
    }

    public static String getBuildEnv() {
        //先从本地文件配置中获取，如果没有，就用默认配置
        CacheManager cacheManager = EleAppliction.getInstance().getCacheManager();
        String buildEnv = cacheManager.getCache(CacheManager.MODE_PREFERENCE, CacheKeys.BUILD_ENV);
        if (TextUtils.isEmpty(buildEnv)) {
            buildEnv =  buildEnvs.get("build_env");
        }
        return buildEnv;
    }

    public static String getBuildVersion() {
        return buildEnvs.get("build_version");
    }

    public static String getBuildNumber() {
        return buildEnvs.get("build_number");
    }

    public static String getBuildTime() {
        return buildEnvs.get("build_timestamp");
    }

    //FAT环境的H5和rest端口配置信息，区分打39999，40000
    public static String getHtmlFatPort() {
        return buildEnvs.get("html_fat_port");
    }

    public static boolean isEncryptConfig() {
        if (buildEnvs == null) {
            return false;
        }
        return true;
        //return "true".equalsIgnoreCase(buildEnvs.get(ConfigKeys.IS_CONFIG_ENCRYPT));
    }
}
