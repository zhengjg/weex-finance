package com.zjg.enjoy.weexfinance.data.cache;




import com.zjg.enjoy.weexfinance.application.EleAppliction;
import com.zjg.enjoy.weexfinance.common.util.FileUtils;

import java.io.File;

/**
 * 缓存请求的String到文件
 * Created by XUJIANHUI030 on 2015-11-30.
 */
public class FileCacheManager extends AbstractCacheManager {
    private String filePath;

    @Override
    public String getCache(String key) {
        filePath = getFileAbsolutePath(key);
        if(filePath != null){
            return FileUtils.file2String(new File(filePath), "utf-8");
        }
        return null;
    }

    @Override
    public void setCache(String key, Object value) {
        FileUtils.string2File((String) value, getFileAbsolutePath(key));
    }

    @Override
    public void removeCache(String key) {
        filePath = getFileAbsolutePath(key);
        if(filePath != null ){
            File file = new File(filePath);
            if(file.exists()){
                file.delete();
            }
        }
    }

    @Override
    public void clearCache() {
        File file = new File(getFilePath());
        if(file.exists()){
            file.delete();
        }
    }

    @Override
    public void clearUserCache() {

    }

    private String getFileAbsolutePath(String key){
        return getFilePath()+key+".txt";
    }

    private String getFilePath(){
        return EleAppliction.getInstance().getCacheDir().getPath()+"/fileCache/";
    }

}
