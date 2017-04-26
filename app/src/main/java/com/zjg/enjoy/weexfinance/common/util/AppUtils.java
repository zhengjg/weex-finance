package com.zjg.enjoy.weexfinance.common.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;


import com.zjg.enjoy.weexfinance.application.EleAppliction;

import java.io.File;
import java.io.InputStream;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by yanghongbing on 16/2/23.
 */
public class AppUtils {

    private final static String TAG = "AppUtils";

    public static String getVersion() {
        Context context = EleAppliction.getInstance();
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = null;
        String version = "";
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            version = pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public static int getVersionInt() {
        Context context = EleAppliction.getInstance();
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = null;
        int version = 0;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            version = pi.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 获取电话号码
     */
    public static String getPhoneNumber() {
        TelephonyManager telephonyManager = (TelephonyManager) (EleAppliction.getInstance()).getSystemService(Context.TELEPHONY_SERVICE);
        String phoneNumber = telephonyManager.getLine1Number();
        return Tools.isEmpty(phoneNumber) ? "" : phoneNumber;
    }

    public static String getMacAddress() {
        WifiManager wifiManager = (WifiManager) EleAppliction.getInstance().getSystemService(Context.WIFI_SERVICE);
        return wifiManager.getConnectionInfo().getMacAddress();
    }

    public static int[] getScreenSize(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int[] size = new int[2];
        size[0] = display.getWidth();
        size[1] = display.getHeight();
        return size;
    }

    public static String getImei() {
        String imei="";
        try{
            TelephonyManager tm = (TelephonyManager) EleAppliction.getInstance()
                    .getSystemService(Context.TELEPHONY_SERVICE);
            //String imei = TCAgent.getDeviceId(PASApplication.getInstance().getApplicationContext());
            imei=tm.getDeviceId();

        }catch (Exception e){
            e.printStackTrace();
        }
        return imei;
    }



    //程序是否在前台运行
    public static boolean isAppOnForeground() {
        Context context = EleAppliction.getInstance();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String packgeName = context.getPackageName();
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcesses) {
            if (appProcessInfo.processName.equals(packgeName)
                    && appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    public static void openApp(Context context, String packageName) throws ActivityNotFoundException {
        PackageManager pm = context.getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(packageName);
        context.startActivity(intent);
    }


    public static int getResId(String name, String field) {
        ApplicationInfo appInfo = EleAppliction.getInstance().getApplicationInfo();
        int resId = EleAppliction.getInstance().getResources().getIdentifier(name, field, appInfo.packageName);
        return resId;
    }

    public static int getMipmapRes(String name) {
        ApplicationInfo appInfo = EleAppliction.getInstance().getApplicationInfo();
        int resID = EleAppliction.getInstance().getResources().getIdentifier(name, "mipmap", appInfo.packageName);
        return resID;
    }

    public static InputStream openRawResource(int resId) {
        Resources res = EleAppliction.getInstance().getResources();
        InputStream is = null;
        try {
            is = res.openRawResource(resId);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return is;
    }

    public static String getString(int resId, Object... args) {
        Resources res = EleAppliction.getInstance().getResources();
        String str = res.getString(resId, args);
        return str;
    }

    public static String getString(int resId) {
        Resources res = EleAppliction.getInstance().getResources();
        String str = res.getString(resId);
        return str;
    }

    public static int getDimensionPixelSize(int resId) {
        Resources res = EleAppliction.getInstance().getResources();
        return res.getDimensionPixelSize(resId);
    }

    public static float getDimension(int resId) {
        Resources res = EleAppliction.getInstance().getResources();
        return res.getDimension(resId);
    }


    public static String getLocalIpAddress() {
        String hostIp = null;
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            InetAddress ia = null;
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    ia = ias.nextElement();
                    if (ia instanceof Inet6Address) {
                        continue;// skip ipv6
                    }
                    String ip = ia.getHostAddress();
                    if (!"127.0.0.1".equals(ip)) {
                        hostIp = ia.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return hostIp;
    }

    public static int getScreenOrientation() {
        Resources res = EleAppliction.getInstance().getResources();
        return res.getConfiguration().orientation;
    }

    /**
     * 获取当前应用包名
     */
    public static String getPackageName() {
        Context context = EleAppliction.getInstance();
        return context.getPackageName();
    }


    /**
     * @return 非加密IMEI
     */
    public static String getCommonImei() {
        TelephonyManager telephonyManager = (TelephonyManager) EleAppliction.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    private static void deleteDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                if (item.isDirectory()) {
                    deleteDirectory(item);
                }
                item.getAbsoluteFile().delete();
            }
        }
    }
}
