package com.zjg.enjoy.weexfinance.common.consts;


import com.zjg.enjoy.weexfinance.common.util.BuildEnviroment;

/**
 * Created by ZHENGJINGUANG829 on 2017-02-20.
 */
public class PinganConsts {

    public static final String BUILD_ENV = BuildEnviroment.getBuildEnv();
    public static final String HPAAY_SCHEME = "pazqhappy";
    public static final String APP_NAME = "PAZQHAPPY";
    public static final String APP_VERSION = "0.0.1";
    public static final String HAPPY_JS_SCHEME = "zqhappy";

    public interface Build {
        String FAT = "fat";
        String UAT = "uat";
        String PRO = "pro";
        String BETA = "beta";
    }





}
