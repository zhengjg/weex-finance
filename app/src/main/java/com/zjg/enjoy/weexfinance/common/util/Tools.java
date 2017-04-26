package com.zjg.enjoy.weexfinance.common.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.text.InputType;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.zjg.enjoy.weexfinance.application.EleAppliction;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yanghongbing on 16/2/25.
 */
public class Tools {
    public static void showToast(String msg) {
        if(isEmpty(msg)){
            return;
        }
        Toast.makeText(EleAppliction.getInstance(), msg, Toast.LENGTH_LONG).show();
    }

    public static void showToast(int resId) {
        Toast.makeText(EleAppliction.getInstance(), resId, Toast.LENGTH_LONG).show();
    }

    public static void showToastWithLocation(final String message, final int location) {
        if (!TextUtils.isEmpty(message)) {
            Toast toast = Toast.makeText(EleAppliction.getInstance(), message, Toast.LENGTH_SHORT);
            toast.setGravity(location, 0, 0);
            toast.show();
        }
    }

    public static void showAddView(View view) {
        Toast toast = Toast.makeText(EleAppliction.getInstance(), "", Toast.LENGTH_SHORT);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dpToPx(35), dpToPx(35));
        params.setMargins(0, 0, 0, dpToPx(10));
        view.setLayoutParams(params);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 全角转半角
     * @param input String.
     * @return 半角字符串
     */
    public static String toDBC(String input) {
        if(TextUtils.isEmpty(input))
        {
            return "";
        }
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = (char) (c[i] - 65248);
            }
        }
        String returnString = new String(c);
        return returnString;
    }
    /**
     * 字符串转换为Map
     *
     * @param str
     * @param split1
     * @param split2
     * @return
     */
    public static Map<String, String> str2Map(String str, String split1, String split2)
    {
        Map<String, String> retMap = new HashMap<String, String>();
        if (str == null || split1 == null || split2 == null)
            return retMap;
        String[] arr = str2Array(str, split1);
        for (int i = 0; i < arr.length; i++)
        {
            String[] temp = str2Array(arr[i], split2);
            if (temp != null)
            {
                if (temp.length == 2)
                {
                    retMap.put(temp[0], temp[1]);
                }
            }

        }
        return retMap;
    }
    /**
     * 字符串转换为字符串数组
     *
     * @param str
     * @param splite
     * @return
     */
    public static String[] str2Array(String str, String splite)
    {
        String[] retValue = null;
        if (null == str)
            return retValue;
        retValue = str.split(splite);
        return retValue;
    }


    /**
     * 取涨幅
     *
     * @return
     */
    public static String setUpDownPrecent(float newPrice, float prevClose) {
        if (prevClose == 0 || newPrice == 0)
            return "--%";
        return Tools.getPersentDecimalFormat().format((newPrice - prevClose) * 100 / prevClose) + "%";
    }


    /**
     * 判断浮点是否为0
     *
     * @author heke 2012-3-30
     * @return
     */
    public static boolean isFloatZero(float num) {
        if (num < 0.00001f && num > -0.00001f)
            return true;
        return false;
    }

    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
    /**
     * 判断字符串是否为空
     * @param trim
     * @return
     */
    public static boolean isEmpty(String trim) {
        if (trim == null || trim.trim().equals("")){
            return true;
        }
        return false;
    }

    public static boolean isEmpty(List list){
        return !(list != null && list.size() > 0);
    }

    /**
     * 判断字符串src是不是空的，空返回true否则返回false。 字符串会经过trim操作，就是如果字符串里都是空格也当成是空串
     *
     * @param str
     * @return
     */
    public static boolean isTrimEmpty(CharSequence str) {
        if (str == null || isEmpty(str.toString().trim())) {
            return true;
        }
        return false;
    }

    public static byte[] getSSLBytes() {
        byte[] key = null;
        InputStream ip;
        Resources res = EleAppliction.getInstance().getResources();
        try {
            ip = res.getAssets().open("tt.bks");
            key = stream2Bytes(ip);
            ip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return key;
    }


    private static byte[] stream2Bytes(InputStream inStream) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            int len = 0;
            byte[] temp = new byte[100];
            while ((len = inStream.read(temp)) != -1) {
                bos.write(temp, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



    /**
     * 字符串换数字
     *
     * @param numberStr
     * @param nDefault
     * @return
     */
    public static int stringToInt(String numberStr, int nDefault)
    {
        if (numberStr == null || numberStr.trim().length() == 0)
            return nDefault;
        numberStr = numberStr.trim().toLowerCase();
        if (numberStr.length() == 0 || numberStr.equals("0x"))
            return nDefault;
        int ret = nDefault;
        try
        {
            if (numberStr.startsWith("0x"))
            {
                ret = Integer.parseInt(numberStr.substring(2), 10);
            }
            else
            {
                ret = Integer.parseInt(numberStr);
            }
        }
        catch (Exception ex)
        {
        }
        return ret;
    }

    /**
     * 格式化下一年日期
     *
     * @param cal
     * @return
     */
    public static String getDateAfterYearByCalendar(Calendar cal)
    {
        cal.add(Calendar.YEAR, 1);
        int year = cal.get(Calendar.YEAR);
        int monthOfYear = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DATE);
        if (monthOfYear == 2 && dayOfMonth == 29)
        {
            return (year + 1) + "0301";
        }
        String text = String.valueOf(year);
        monthOfYear += 1;
        if (monthOfYear < 10)
            text += "0";
        text += monthOfYear;
        if (dayOfMonth < 10)
            text += "0";
        text += dayOfMonth;
        return text;
    }

    /**
     * 格式化时间点
     *
     * @param cal
     * @return
     */
    public static String getTimeByCalendar(Calendar cal)
    {
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        String ret = null;
        if (hour < 10)
            ret = "0" + hour;
        else
            ret = String.valueOf(hour);
        if (minute < 10)
            ret += "0" + minute;
        else
            ret += minute;

        if (second < 10)
            ret += "0" + second;
        else
            ret += second;
        return ret;
    }



    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    /**
     * 检测sdcard是否可用
     *
     * @return true为可用，否则为不可用
     */
    public static boolean sdCardIsAvailable() {
        String status = Environment.getExternalStorageState();
        if (!status.equals(Environment.MEDIA_MOUNTED))
            return false;
        return true;
    }

    public static String encoderUrl(String urlStr) {
        String url ="";
        try {
            url = URLEncoder.encode(urlStr, "utf-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        url = url.replaceAll("%3A", ":").replaceAll("%2F", "/");
        return url;
    }

    public static String completeFileName(String url,String fileName) {
        int fileLastIndex = fileName.lastIndexOf(".");
        if(fileLastIndex <= 0) {
            int urlLastIndex = url.lastIndexOf(".");
            fileName += url.substring(urlLastIndex);
        }
        return fileName;
    }

    public static void openFile(File file,Context context) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //设置intent的Action属性
        intent.setAction(Intent.ACTION_VIEW);
        //获取文件file的MIME类型
        String type = Tools.getMIMEType(file);
        //设置intent的data和Type属性。
        intent.setDataAndType(/*uri*/Uri.fromFile(file), type);
        //跳转
        context.startActivity(intent);
    }

    /**
     * 根据文件后缀名获得对应的MIME类型。
     * @param file
     */
    public static String getMIMEType(File file) {
        String type="*/*";
        String fName=file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if(dotIndex < 0) {
            return type;
        }
	    /* 获取文件的后缀名 */
        String end = fName.substring(dotIndex,fName.length()).toLowerCase();
        if(end == "")return type;
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        for(int i=0; i<MIME_MapTable.length; i++) {
            if(end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }

    //建立一个MIME类型与文件后缀名的匹配表
    private static final String[][] MIME_MapTable={
            //{后缀名，    MIME类型}
            {".3gp",    "video/3gpp"},
            {".apk",    "application/vnd.android.package-archive"},
            {".asf",    "video/x-ms-asf"},
            {".avi",    "video/x-msvideo"},
            {".bin",    "application/octet-stream"},
            {".bmp",      "image/bmp"},
            {".c",        "text/plain"},
            {".class",    "application/octet-stream"},
            {".conf",    "text/plain"},
            {".cpp",    "text/plain"},
            {".doc",    "*/*"},
            {".exe",    "application/octet-stream"},
            {".gif",    "image/gif"},
            {".gtar",    "application/x-gtar"},
            {".gz",        "application/x-gzip"},
            {".h",        "text/plain"},
            {".htm",    "text/html"},
            {".html",    "text/html"},
            {".jar",    "application/java-archive"},
            {".java",    "text/plain"},
            {".jpeg",    "image/jpeg"},
            {".jpg",    "image/jpeg"},
            {".js",        "application/x-javascript"},
            {".log",    "text/plain"},
            {".m3u",    "audio/x-mpegurl"},
            {".m4a",    "audio/mp4a-latm"},
            {".m4b",    "audio/mp4a-latm"},
            {".m4p",    "audio/mp4a-latm"},
            {".m4u",    "video/vnd.mpegurl"},
            {".m4v",    "video/x-m4v"},
            {".mov",    "video/quicktime"},
            {".mp2",    "audio/x-mpeg"},
            {".mp3",    "audio/x-mpeg"},
            {".mp4",    "video/mp4"},
            {".mpc",    "application/vnd.mpohun.certificate"},
            {".mpe",    "video/mpeg"},
            {".mpeg",    "video/mpeg"},
            {".mpg",    "video/mpeg"},
            {".mpg4",    "video/mp4"},
            {".mpga",    "audio/mpeg"},
            {".msg",    "application/vnd.ms-outlook"},
            {".ogg",    "audio/ogg"},
            {".pdf",    "application/pdf"},
            {".png",    "image/png"},
            {".pps",    "application/vnd.ms-powerpoint"},
            {".ppt",    "application/vnd.ms-powerpoint"},
            {".prop",    "text/plain"},
            {".rar",    "*/*"},
            {".rc",        "text/plain"},
            {".rmvb",    "audio/x-pn-realaudio"},
            {".rtf",    "application/rtf"},
            {".sh",        "text/plain"},
            {".tar",    "application/x-tar"},
            {".tgz",    "application/x-compressed"},
            {".txt",    "text/plain"},
            {".wav",    "audio/x-wav"},
            {".wma",    "audio/x-ms-wma"},
            {".wmv",    "audio/x-ms-wmv"},
            {".wps",    "application/vnd.ms-works"},
            {".xml",    "text/plain"},
            {".z",        "application/x-compress"},
            {".zip",    "application/zip"},
            {"",        "*/*"}
    };

    public static boolean getUninatllApkInfo(Context context, String filePath) {
        boolean result = false;
        try {
            PackageManager pm = context.getPackageManager();
            Log.e("archiveFilePath", filePath);
            PackageInfo info = pm.getPackageArchiveInfo(filePath,
                    PackageManager.GET_ACTIVITIES);
//		    String packageName = null;
            if (info != null) {
                result = true;
            }
        } catch (Exception e) {
            result = false;
            Log.e("install error", "*****  解析未安装的 apk 出现异常 *****" + e.getMessage());
        }
        return result;
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null) {
            return false;
        }
        if (phone.length() == 11 && phone.startsWith("1")) {
            return true;
        }
        return false;
    }

    public static boolean isNum(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
        return pattern.matcher(str).matches();
    }

    public static boolean isNumber2(String str) {

        if(isEmpty(str)||"-".equals(str.trim())||"+".equals(str.trim())){
            return false;
        }else {
            return isNumber(str);
        }

    }


    /**
     * 数字字符串转为有单位(万, 亿, 千亿)的字符串
     *
     * @param numberStr
     * @param dec
     *            小数点
     * @return
     */
    public static String numberToStringWithUnit(String numberStr, int dec)
    {
        if (numberStr.contains("E") || numberStr.contains("e"))
        {
            DecimalFormat decimalFormat = new DecimalFormat("0.00000000");
            numberStr = "" + decimalFormat.format(new BigDecimal(numberStr));
        }
        return numberToStringWithoutUnit(numberStr, dec, true);
    }

    /**
     * 数字字符串转为有单位(万, 亿, 千亿)的字符串
     *
     * @param numberStr
     * @param dec
     *            小数点
     * @return
     */
    public static String numberToStringWithoutUnit(String numberStr, int dec, boolean withUnit)
    {
        if (numberStr.endsWith("-"))
        {
            return numberStr;
        }
        int retDec = dec;
        String ret = numberStr;
        String unit = "";
        long point = numberStr.indexOf(".");
        if (point < 0)
        {
            point = numberStr.length();
        }
        if (point < 5)
        {
            point = 1;
            retDec = 0;
        }
        else if (point < 9)
        {
            point = 10000;
            unit = "万";
        }
        else if (point < 13)
        {
            point = 100000000;
            unit = "亿";
        }
        else
        {
            point = 1000000000000L;
            unit = "万亿";
        }
        ret = numberToStringWithUnit(numberStr, retDec, point);
        /*
         * ret = numberStr.substring(0, point); String decStr = ""; if (dec > 0)
         * { if (numberStr.length() == point) { for (int i = 0; i < dec; i++) {
         * decStr += "0"; } } else if (numberStr.length() - point <= dec + 1) {
         * decStr = numberStr.substring(point + 1); for (int i =
         * decStr.length(); i < dec; i++) { decStr += "0"; } } else { decStr =
         * numberStr.substring(point, point + dec); } } if (decStr.length() >
         * 0){ if(decStr.indexOf(".") < 0) ret += "." + decStr; else ret +=
         * decStr; }
         */
        return withUnit ? ret + unit : ret;
    }

    /**
     * 数字字符串转为有单位(万, 亿, 千亿)的字符串
     *
     * @param numberStr
     * @param dec
     *            小数点
     * @param unit
     *            保留基数
     * @return
     */
    public static String numberToStringWithUnit(String numberStr, int dec, long unit)
    {
        double num = Double.parseDouble(numberStr);
        num /= unit;
        String format = "%." + dec + "f";
        return String.format(format, num);
    }

    public static float stringToNumber(String numberStr) {
        if(Tools.isEmpty(numberStr)) {
            return 0.0f;
        }
        if (numberStr.endsWith("-")) {
            return 0.0f;
        }
        float value, ratio = 1;
        int index = numberStr.length() - 1;
        String unint = numberStr.substring(index);
        if (unint.equals("万")) {
            ratio = 1e4f;
            value = Float.valueOf(numberStr.substring(0, index - 1));
        } else if (unint.equals("亿")) {
            ratio = 1e8f;
            value = Float.valueOf(numberStr.substring(0, index - 1));
            unint = numberStr.substring(index - 1);
            if (unint.equals("千亿")) {
                ratio = 1e11f;
                value = Float.valueOf(numberStr.substring(0, index - 1));
            }
        } else {
            value = Float.valueOf(numberStr);
        }
        return value * ratio;
    }

    public static float stringToNumberNew(String numberStr) {
        if(isEmpty(numberStr)){
            return 0.0f;
        }
        if (numberStr.endsWith("-")) {
            return 0.0f;
        }
        float value, ratio = 1;
        int index = numberStr.length() - 1;
        int unitCount = 0;
        if(numberStr.contains("十")) {
            unitCount++;
            ratio *= 10;
        }
        if(numberStr.contains("百")) {
            unitCount++;
            ratio *= 1e2f;
        }
        if(numberStr.contains("千")) {
            unitCount++;
            ratio *= 1e3f;
        }
        if(numberStr.contains("万")) {
            unitCount++;
            ratio *= 1e4f;
        }
        if(numberStr.contains("亿")) {
            unitCount++;
            ratio *= 1e8f;
        }
        String num = numberStr.substring(0, index - unitCount + 1);
        if(Tools.isEmpty(num)) {
            return 0f;
        }
        try{
            value = Float.valueOf(num);
        }catch (Exception e){
            e.printStackTrace();
            return 0f;
        }
        return value * ratio;
    }

    /**
     * 格式化银行卡号，格式： ****1234
     * **/
    public static String formatBankAccount(String bankAccount) {
        if(bankAccount == null || bankAccount.length() <= 4) {
            return bankAccount;
        }
        int length = bankAccount.length();
        return "****" + bankAccount.substring(length - 4);
    }

    public static String getFormatDate(long time,String formatStr) {
        Date targetDate = new Date(time);
        DateFormat format = new SimpleDateFormat(formatStr);
        return format.format(targetDate);
    }

    public static String getDate(long time) {
        Date targetDate = new Date(time);
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(targetDate);
    }

    public static String getTime(long time) {
        Date targetDate = new Date(time);
        DateFormat format = new SimpleDateFormat("HH.mm");
        return format.format(targetDate);
    }

    public static String getDate(int days) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -days);
        return getDate(calendar.getTimeInMillis());
    }

    public static String getDate(String formatStr){
        SimpleDateFormat df ;
        try {
            df = new SimpleDateFormat(formatStr);//设置日期格式
        }catch (Exception e){
            return "";
        }

        return df.format(new Date());
    }

    /**
     * 替换字符串中的字符
     */
    public static String convertString(String targetStr,CharSequence oldChar, CharSequence newChar){
        if(!isEmpty(targetStr) && targetStr.contains(oldChar)){
            targetStr = targetStr.replace(oldChar,newChar);
        }
        return targetStr;
    }


    /**
     * 格式化日期
     *
     * @param cal
     * @return
     */
    public static String getDateByCalendar(Calendar cal)
    {
        int year = cal.get(Calendar.YEAR);
        int monthOfYear = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DATE);
        String text = String.valueOf(year);
        monthOfYear += 1;
        if (monthOfYear < 10)
            text += "0";
        text += monthOfYear;
        if (dayOfMonth < 10)
            text += "0";
        text += dayOfMonth;
        return text;
    }


    /**
     * 判断字符串numStr是否是浮点数
     */
    public static boolean isFloat(String numStr) {
        if (!isTrimEmpty(numStr)) {
            try {
                Float.parseFloat(numStr);
                return true;
            } catch (Exception e)
            {
            }
        }
        return false;
    }

    /**
     * 格式化浮点数
     *
     * @param count
     * @param value
     * @return
     */
    public static String formatDouble(int count, String value) {
        if (isTrimEmpty(value)) {
            return value;
        }
        String retVal = null;
        int dx = value.indexOf('.');
        if (dx != -1) {
            String[] splitStr = value.split("\\.");
            retVal = splitStr[0];
            if (count <= 0) {
                return retVal;
            }
            String decimalStr = "";
            if (splitStr.length > 1) {
                decimalStr = splitStr[1];
            }
            if (Tools.isTrimEmpty(decimalStr)) {
                String s = "";
                while (count != 0) {
                    s += "0";
                    count--;
                }
                retVal = s.length() > 0 ? retVal + "." + s : retVal;
            } else if (decimalStr.length() == count) {
                retVal = value;
            } else if (decimalStr.length() > count) {
                retVal = retVal + "." + decimalStr.substring(0, count);
            } else if (decimalStr.length() < count) {
                while (decimalStr.length() < count) {
                    decimalStr += "0";
                }
                retVal = retVal + "." + decimalStr;
            }
        } else if (count > 0) {
            retVal = value + '.';
            for (int b = 0; b < count; b++)
                retVal += '0';
        } else {
            retVal = value;
        }
        return retVal;
    }

    public static <T> List<T> arrayToList(T[] array) {
        if (array == null) {
            return null;
        }
        List<T> list = new ArrayList<T>();
        if (array.length > 0) {
            for (T element : array) {
                list.add(element);
            }
        }
        return list;
    }

    public static String toPercent(String numString) {
        try {
            double num = Double.parseDouble(numString);
            double temp = num * 100;
            return temp + "%";
        } catch (NumberFormatException e) {
            throw new IllegalStateException(numString + "is not a num string.");
        }
    }

    /**
     * 币种中文转义
     *
     * @param moneyType
     * @return
     */
    public static String getMoneyTypeName(String moneyType)
    {
        if (moneyType == null || moneyType.trim().length() <= 0)
        {
            return "";
        }
        if ("0".equals(moneyType))
        {
            return "人民币";
        }
        else if ("1".equals(moneyType))
        {
            return "美元";
        }
        else if ("2".equals(moneyType))
        {
            return "港币";
        }
        return "";
    }


    /**
     * 判断字符串numStr是否是Double
     *
     * @param numStr
     * @return
     */
    public static boolean isDouble(String numStr)
    {
        if (!isTrimEmpty(numStr))
        {
            try
            {
                Double.parseDouble(numStr);
                return true;
            }
            catch (Exception e)
            {
            }
        }
        return false;
    }

    /**
     * 数字字符串中是否包含字母
     *
     * @param s
     * @return
     */
    public static boolean isContainLetter(String s)
    {
        boolean isContainLetter = false;
        for (int i = 0; i < s.length(); i++)
        {
            if (Character.isLetter(s.charAt(i)))
            {
                isContainLetter = true;
                break;
            }
        }
        return isContainLetter;
    }

    // color: ccc, 0xaabbcc, 0xaabbccdd
    public static int parseColor(String color) {
        if (color == null) {
            return -1;
        }
        color = color.replaceAll("0x", "");
        if (color.length() == 3) {
            String[] s = new String[3];
            s[0] = color.substring(0, 1);
            s[1] = color.substring(1, 2);
            s[2] = color.substring(2, 3);
            StringBuilder colorBuild = new StringBuilder();
            colorBuild.append("FF").append(s[0]).append(s[0])
                    .append(s[1]).append(s[1])
                    .append(s[2]).append(s[2]);
            color = colorBuild.toString();
        } else if (color.length() == 6) {
            color = "FF" + color;
        } else if (color.length() == 8) {
            color = color;
        } else {
            return -1;
        }
        String[] argb = new String[4];
        argb[0] = color.substring(0, 2);
        argb[1] = color.substring(2, 4);
        argb[2] = color.substring(4, 6);
        argb[3] = color.substring(6, 8);
        int a = Integer.parseInt(argb[0], 16);
        int r = Integer.parseInt(argb[1], 16);
        int g = Integer.parseInt(argb[2], 16);
        int b = Integer.parseInt(argb[3], 16);
        return Color.argb(a, r, g, b);
    }

    public static void openSysCalendar(Context context){
        try {
            Intent intent = new Intent();
            ComponentName cn =  new ComponentName("com.android.calendar", "com.android.calendar.LaunchActivity");
            intent.setComponent(cn);
            context.startActivity(intent);
        } catch (Exception e) {
            Log.e("openCalendar error", e.toString());
        }
    }


    /**
     *
     * @param date yyyy-MM-dd
     * @return 星期
     */
    public static String getWeekDay(String date) {
        String week="";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC+8"));
            cal.setTime(dateFormat.parse(date));
            switch (cal.get(Calendar.DAY_OF_WEEK)){
                case 1:
                    week="周一";
                    break;
                case 2:
                    week="周二";
                    break;
                case 3:
                    week="周三";
                    break;
                case 4:
                    week="周四";
                    break;
                case 5:
                    week="周五";
                    break;
                case 6:
                    week="周六";
                    break;
                case 7:
                    week="周日";
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return week;
    }

    /**
     * 日期转毫秒
     * @param dateStr
     * @param dateFormat
     * @return
     */
    public static long date2Millis(String dateStr, String dateFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try{
            long millis = sdf.parse(dateStr).getTime();
            return millis;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    public static int getScreenWidth(Activity activity){
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getScreenHeight(Activity activity){
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }



    /**
     * 像素转换为密度值
     *
     * @param px
     * @return
     */
    public static int pxToDp(float px)
    {
        final float scale = EleAppliction.getInstance().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 密度转换为像素值
     *
     * @param dp
     * @return
     */
    public static int dpToPx(float dp)
    {
        final float scale = EleAppliction.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static String getCurrentTime(String format){
        SimpleDateFormat df ;
        try {
            df = new SimpleDateFormat(format);//设置日期格式
        }catch (Exception e){
            return "";
        }

        return df.format(new Date());
    }


    public static double StringToDouble (String num) {
        if(isEmpty(num)){
            return 0.00;
        }
        try {
            Double value = Double.valueOf(num);
            return value;
        }catch (Exception e){
            e.printStackTrace();
            return 0.00;
        }
    }
    /**
     * 对中文进行编码
     *
     * @param name
     * @return
     */
    public static String encodes(String name) {
        if (name != null) {
            try {
                name = URLEncoder.encode(name, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return name;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static String getURLBase(String s) {
        String s1 = s;
        if (s1 == null) {
            return "";
        }
        if (!s1.startsWith("http://") && !s1.startsWith("https://")) {
            s1 = "http://" + s1;
        }
        //解决url中出现'\'导致解析根地址出现问题
        int pos = s1.indexOf("?");
        if(pos > 0){
            s1 = s1.substring(0,pos);
        }
        //解决url中出现'\'导致解析根地址出现问题
        int i = s1.length();
        int j;
        for (j = i - 1; j >= 0 && s1.charAt(j) != '/' && s1.charAt(j) != '\\'; j--) {
        }
        s1 = s1.substring(0, j + 1);
        if (s1.equals("http://")) {
            s1 = s + "/";
        }
        return s1;
    }



    public static String getStrDate()
    {
        return getStrDate("/");
    }

    public static String getStrDate(String separateStr)
    {
        String date = "";
        Calendar c = Calendar.getInstance();
        date = c.get(Calendar.YEAR) + separateStr + (c.get(Calendar.MONTH) + 1) + separateStr + c.get(Calendar.DAY_OF_MONTH);
        return date;
    }


    /**
     * 获取客户端版本号
     * @param context
     * @return
     */
    public static String getVersion(Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = null;
        String version = "";
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            version = pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 全角转半角
     * @param input String.
     * @return 半角字符串
     */
    public static String ToDBC(String input) {
        if(TextUtils.isEmpty(input))
        {
            return "";
        }
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = (char) (c[i] - 65248);
            }
        }
        String returnString = new String(c);
        return returnString;
    }

    /**
     * 取市场名称
     *
     * @param value
     * @return
     */
    public static CharSequence getMarketName(CharSequence value)
    {
        String ret = null;
        if ("1".equals(value))
        {
            ret = "沪A";
        }
        else if ("2".equals(value))
        {
            ret = "深A";
        }
        else if ("D".equals(value))
        {
            ret = "沪B";
        }
        else if ("H".equals(value))
        {
            ret = "深B";
        }
        else if ("9".equals(value))
        {
            ret = "三A";
        }
        else if ("A".equals(value))
        {
            ret = "三B";
        }
        else if ("8".equals(value))
        {
            ret = "创业板";
        }
        else if("G".equals(value))
        {
            ret = "港股通";
        }
        else if("WJS".equals(value))
        {
            ret = "交易所";
        }
        else if("O1".equals(value))
        {
            ret = "大汉交易所";
        }
        else
            ret = "";
        return ret;
    }

    public static void showSimpleDialog(){

    }



    /**
     * 格式化可买、可卖数量，当小数点后面都是0时，去掉小数点后的这些0 否则原样返回
     *
     * 例如：123.00 返回123， 123.45 返回123.45
     *
     * huangcheng 2012-4-25
     *
     * @param pAmountStr
     * @return
     */
    public static String formatBuySellAmount(String pAmountStr)
    {
        if (!Tools.isEmpty(pAmountStr) && pAmountStr.indexOf(".") > 0)
        {
            pAmountStr = pAmountStr.trim();
            Pattern pattern = Pattern.compile("\\d+([.]0+)");
            Matcher matcher = pattern.matcher(pAmountStr);
            return matcher.matches() ? pAmountStr.substring(0, pAmountStr.indexOf(".")) : pAmountStr;
        }
        return pAmountStr;
    }

    private static final DecimalFormat persentDecimalFormat = new DecimalFormat("0.00");
    public static DecimalFormat getPersentDecimalFormat()
    {
        return persentDecimalFormat;
    }

    public static String multiply(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        double result = b1.multiply(b2).doubleValue();
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(false);
        return numberFormat.format(result);
    }

    public static String exchangeType2DhzType(String exchangeType) {
        String str = null;
        if (Tools.isEmpty(exchangeType))
            return str;
        if(exchangeType.equals("1") || exchangeType.equals("D")) {
            str = "SH";
        } else if(exchangeType.equals("2") || exchangeType.equals("H") || exchangeType.equals("8")) {
            str = "SZ";
        } else if(exchangeType.equals("9") || exchangeType.equals("A")) {
            str = "SO";
        } else if(exchangeType.equals("G")) {
            str = "HH";
        }
        return str;
    }

    /**
     * 隐藏系统输入法，但显示光标
     * @param editText
     * @return
     */
    public static void hideSysInput(Activity activity, EditText editText){
        int type = editText.getInputType();
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            editText.setInputType(InputType.TYPE_NULL);
        } else {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod(
                        "setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(editText, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        editText.setInputType(type);
    }

    /**
     * scrollview
     * 滑动到底部
     * @param scroll
     * @param inner
     */
    public static void scrollToBottom(final View scroll, final View inner) {

        Handler mHandler = new Handler();

        mHandler.post(new Runnable() {
            public void run() {
                int offset = inner.getMeasuredHeight() - scroll.getHeight();
                if (offset < 0) {
                    offset = 0;
                }

                scroll.scrollTo(0, offset);
            }
        });
    }

    public static int getViewHeight(View view){
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int height = view.getMeasuredHeight();
        return height;
    }

    /**
     * double 相减(去掉分隔符)
     * @param d1
     * @param d2
     * @return
     */
    public static String sub(Double d1,Double d2){
        BigDecimal b1 = new BigDecimal(d1.toString());
        BigDecimal b2 = new BigDecimal(d2.toString());
        DecimalFormat format = (DecimalFormat) NumberFormat.getInstance();
        format.setGroupingUsed(false);//去掉分隔符
        return format.format(b1.subtract(b2).doubleValue());
    }


    /**
     * double 相除
     * @param v1
     * @param v2
     * @return
     */
    public static String div(Double v1,Double v2){
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        DecimalFormat format = (DecimalFormat) NumberFormat.getInstance();
        format.setGroupingUsed(false);//去掉分隔符
        return format.format(b1.divide(b2,5, BigDecimal.ROUND_HALF_EVEN).doubleValue());
    }


    /**
     * double 相乘
     * @param v1
     * @param v2
     * @return
     */
    public static String mul(Double v1,Double v2){
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        DecimalFormat format = (DecimalFormat) NumberFormat.getInstance();
        format.setGroupingUsed(false);//去掉分隔符
        return format.format(b1.multiply(b2).doubleValue());
    }

    /**
     * double 相加（不会使用科学计数法）
     * @param v1
     * @param v2
     * @return
     */
    public static String add2(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        DecimalFormat format = (DecimalFormat) NumberFormat.getInstance();
        format.setGroupingUsed(false);//去掉分隔符
        return format.format(b1.add(b2).doubleValue());
    }



}
