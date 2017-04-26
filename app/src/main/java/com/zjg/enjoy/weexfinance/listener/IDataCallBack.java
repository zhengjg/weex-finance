package com.zjg.enjoy.weexfinance.listener;


import com.zjg.enjoy.weexfinance.data.bean.PABaseBean;

/**
 * Created by zhengjinguang829 on 2017-03-10.
 */
public interface IDataCallBack {
    //返回值为true, 表示不需要底层弹提示，由调用方自己提示错误，否则由底层统一弹出
    public boolean onReceiveError(int httpId, PABaseBean bean);

    public void onDataRefresh(int httpId, Object responseBean);
}
