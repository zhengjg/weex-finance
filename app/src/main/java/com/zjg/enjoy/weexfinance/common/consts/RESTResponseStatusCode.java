package com.zjg.enjoy.weexfinance.common.consts;

/**
 * Created by YANGHONGBING615 on 2015/7/21.
 */
public interface RESTResponseStatusCode {
    public final static int SUCESS = 1;
    public final static int FAILIED = 0;
    public final static int NOT_TRADE_TIMEWINDOW = 6; //非交易时间
    public final static int NETWORK_ERROR = 7;//网络异常
    public final static int TRADESSO_ERROR = 999;//请求sso无数据返
    public final static int TRADESSO_RELOGIN = 998;//系统异常,请重新登录
    public final static int TRADESSO_APP_NORELOGIN = 997;//APP取不到登录信息
    public final static int ThirdBankCancle_failed_801 = 801;//801，资产账户销户失败
    public final static int ThirdBankCancle_failed_802 = 802;//802，三方存管解约失败
    public final static int WARNNING_B_ACCOUNT = 803; //您的资金账号中有外币业务，请确认变更后的账号已开通外币业务，否则将影响交易！
    public final static int ET_MAINCHANGOBEY_FAILED = 804; //;资金帐户设置主帐户，主从关系设置失败

    public final static int NO_BRANCH_OPER_FAILED = 805; //营业部无操作员配置
    public final static int REPEAT_SUCESS_REQUEST = 806; //重复已成功的请求
    public final static int MORE_THAN_ONE_CARD_IN_ONE_BANK = 807;//同一银行只能绑定一个三方存管帐户
    public final static int SIGN_THIRDBANK_FAILED = 808;//银行三方存管绑定失败
    public final static int SIGN_MULT_THIRDBANK_FAILED = 809;//银行多方存管绑定失败
    public final static int BUS913_ERROR = 810;//券商发起银证开户 ，会员专区绑定三方专用。

}

