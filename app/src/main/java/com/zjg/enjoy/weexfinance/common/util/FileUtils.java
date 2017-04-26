/**
 * <p>本地文件SD卡上储存类</p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 恒生电子股份有限公司</p>
 * <p>Project: 投资赢家移动理财终端5.0</p>
 * @author shenlj
 * @version 1.0
 * @history
 */
package com.zjg.enjoy.weexfinance.common.util;

import android.os.Environment;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class FileUtils {
	private String FILE_DIR_NAME;
	private HashMap<String, String> configMap = new HashMap<String, String>();
	private static FileUtils instance;
	
	public static FileUtils getInstance(){
		if(instance == null)
			instance = new FileUtils();
		return instance;
	}
	
	private void initDir(){
		String path = FILE_DIR_NAME.substring(0, FILE_DIR_NAME.lastIndexOf(File.separator));
		File file = new File(path);
		if(!file.exists())
			file.mkdirs();
	}
	
	public void writeLog(String fileName,String content)
	{
	    BufferedWriter bufferedWriter = null;
        try
        {
            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + fileName);
            if(!file.exists())
            {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file
                    ,true);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos));
            bufferedWriter.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()) + "\r\n" + content + "\r\n");
            bufferedWriter.flush();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(bufferedWriter != null)
                try
                {
                    bufferedWriter.close();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
	}
	
	
	private FileUtils() {
		FILE_DIR_NAME = Environment.getExternalStorageDirectory().getPath();
		FILE_DIR_NAME = FILE_DIR_NAME + "/winner/winner.dat";
		if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			return;
		}
		String content = "";
		try {
			FileInputStream fin = new FileInputStream(FILE_DIR_NAME);
			int length = fin.available();
			byte[] buffer = new byte[length];
			fin.read(buffer);
			//content = EncodingUtils.getString(buffer, "UTF-8");
			fin.close();
		} catch(FileNotFoundException fnex){
			initDir();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] ayConfig = content.split("\n");
		for(int i=0;i<ayConfig.length;i++){
			String[] cfg = ayConfig[i].split("=");
			if(cfg.length < 2)
				continue;
			configMap.put(cfg[0], cfg[1]);
		}
		content = null;
	}
	
	public void write(String key, String value){
		if(key == null)
			return;
		configMap.put(key, value);
	}
	
	public void delete(String key){
		if(key == null)
			return;
		configMap.remove(key);
	}

	
	public void clear(){
		File file = new File(FILE_DIR_NAME);
		file.delete();
		configMap.clear();
	}
	
	public String get(String key){
		if(key == null)
			return null;
		return configMap.get(key);
	}
	
	public Map<String, String> getAll(){
		return configMap;
	}
	
//	public static InputStream getWinnerConfig(Context context)
//	{
//		InputStream is = HsEncrypt.decodeResourceReturnInputSource(context, R.raw.winner_config);
//		return is;
//	}
//
//	public static InputStream getTradeConfig(Context context)
//	{
//		InputStream is = HsEncrypt.decodeResourceReturnInputSource(context, R.raw.trade_config);
//		return is;
//	}

    /**
    * 将String写入文件中
    *@author xujianhui030
    *created at 2015-12-01
    *
    */
    public static boolean string2File(String res, String filePath) {
        boolean flag = true;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            File distFile = new File(filePath);
            if (!distFile.getParentFile().exists()) distFile.getParentFile().mkdirs();
            bufferedReader = new BufferedReader(new StringReader(res));
            bufferedWriter = new BufferedWriter(new FileWriter(distFile));
            char buf[] = new char[1024];         //字符缓冲区
            int len;
            while ((len = bufferedReader.read(buf)) != -1) {
                bufferedWriter.write(buf, 0, len);
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
            return flag;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
    * 读取文件中的String
    *@author xujianhui030
    *created at 2015-12-01
    *
    */
    public static String file2String(File file, String encoding) {
		// 如果不存在则返回空
		if (file != null && !file.exists()) {
			return null;
		}

        InputStreamReader reader = null;
        StringWriter writer = new StringWriter();
        try {
            if (encoding == null || "".equals(encoding.trim())) {
                reader = new InputStreamReader(new FileInputStream(file));
            } else {
                reader = new InputStreamReader(new FileInputStream(file), encoding);
            }
            //将输入流写入输出流
            char[] buffer = new char[1024];
            int n = 0;
            while (-1 != (n = reader.read(buffer))) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        //返回转换结果
        if (writer != null)
            return writer.toString();
        else return null;
    }
}
