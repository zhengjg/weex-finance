package com.zjg.enjoy.weexfinance.data.cache;

/**
 * Created by YANGHONGBING615 on 2015/7/22.
 */
public interface CacheKeys {

    //跟用户相关的缓存前缀，如果缓存key加上此前缀，则用户在注销账号后，回删除此key对于的缓存
    String USER_PREFIX = "_USER_";

    String KEY_MY_BANK_LIST = USER_PREFIX + "key_my_bank_list";
    String KEY_MY_BANK_LIST_EXTAND = USER_PREFIX + "key_my_bank_list_extand";
    String PT_USER_INFO = USER_PREFIX + "pt_user_info";
    String CY_STATUS = USER_PREFIX + "cy_status";
    String MYASSETS = USER_PREFIX + "myAssets";
    String MYACCOUNT = USER_PREFIX + "myAccount";
    String MYFINANCE = USER_PREFIX + "myfinance";
    String LICAIASSETS = USER_PREFIX + "licaiAssets";
    String USER_DETAIL_INFO = USER_PREFIX + "userDetailInfo";
    String SESSION = USER_PREFIX + "session";
    String SESSIONS = USER_PREFIX + "sessions";
    String SESSION_INDEX = USER_PREFIX + "sessionindex";
    String USER_INVESTMENT_TYPE = USER_PREFIX + "userInvestmentType";
    String YZT_CHECK_USER_INFO = USER_PREFIX + "yzt_check_user_info";
    String USER_SIGN_INFO = USER_PREFIX + "user_sign_info";
    String LICAIASSETSForApp = USER_PREFIX + "licaiAssetsForApp";
    String LASTLOGINTYPE = USER_PREFIX + "lastLoginType";
    String MYSTOCK_ASSETS = USER_PREFIX + "myStockAssets";
    String USER_COUPON_INFO = USER_PREFIX + "user_coupon_info";
    String IM_USER_REG_INFO = USER_PREFIX + "imUserRegInfo";
    String IM_USER_CHAT_LIST_INFO = USER_PREFIX + "imUserChatListInfo";
    String IM_USER_LOGIN_FAILED_COUNT = USER_PREFIX + "imUserLoginFailedCount";
    /**
     * 注意：
     * 这里的缓存分为与用户登陆态相关和不相关两部分。
     * 如果你缓存的数据与用户相关，请写到上半部分，并在key加上前缀USER_PREFIX
     * 用户在退出账号时，这些缓存将会被清除
     * 如果缓存的数据与用户无关，请写到下半部分，这些缓存在用户退出账号时，不会被清除
     **/

    String IS_STARTED_BEFORE = "isStartedBefore";
    String APP_VERSION = "appVersion";
    String MYASSETS_TIP = "myAssstsTip";
    String KEY_SUPPORTED_BANK_LIST = "key_supported_bank_list";
    String KEY_NEW_STOCK = "key_new_stock";
    String SERVER_TIME = "serverTime";
    String HOT_STOCK = "hotStock";
    String HOT_FUND = "hotFund";
    String HOT_ANCHOR = "hotAnchor";
    String KEY_ADVERTISING = "launch_advertise";
    String KEY_ADV_BANNER = "home_adv_banner";
    String LICAI_BANNER = "licaiBanner";
    String HOME_ENTRY = "home_entry";
    String HOME_ENTRY_NEW = "home_entry_new";
    String HOME_ICON = "home_icon";
    String HOME_CONFIG = "home_config";
    String MOCK_STOCK_CONFIG = "mock_stock_config";
    String HOME_STOCK_NEWS = "home_stock_news";
    String RENYIMEN_TICKET = "rym_ssoticket";
    String RENYIMEN_SECRET = "rym_sessionsecret";
    String RENYIMEN_MAMCID = "rym_mamcId";
    String HAS_SHOWN_ENTRY_TIP = "hasShownEntryTip";
    String LOGIN_ACCOUNTS = "loginAccounts";
    String KEY_SUPPORTED_BANK_RULE_LIST = "key_supported_bank_rule_list";
    String IM_FIRST_BOOT_INIT = "im_first_boot_init";
    String PERSON_CENTER = "user_center";
    String COMMENT_IMAGE = "comment_image";
    String REPLYFLAG = "replyflag";
    String VALID_MODIFICATION = "valid_modification";
    String PRESET_ENTRUST_REFRESH_TIME = "preset_refresh_time";
    String PRESET_ENTRUST_DIRECTION = "preset_entrust_direction";
    String LOGIN_TIME = "loginTime";
    String HISTORY_STOCK = "historyStock";
    String INTELLIGENCE_STOCK = "Intelligence_stock";
    String DZHL2STOCKCACHE = "DZHL2_stock_cache";
    String LIVE_ADVERTISE = "liveAdvertise";
    String STOCK_IMPORT_CHICANG = "stock_import_chicang";
    String KEY_IS_DISPLAYED_HK_ALERT = "is_displayed_hk_alert";
    String USER_ID = "userId";
    String NUMBER_ADVERTISING = "number_advertising";
    String LIVE_TIP_FLAG = "live_tip_flag";
    String HS_MARKET_LIST = "hs_market_list";
    String SGT_MARKET_LIST = "sgt_market_list";
    String HGT_MARKET_LIST = "hgt_market_list";
    String STOCK_LIST = "stock_list";
    String STOCK_INDEX = "stock_index";
    String ASSETS_EYES = "assets_eyes";
    String HOME_MENUBAR_IMG = "home_menubar_img";
    String LOCAL_GROUPID = "local_groupid";
    String BUILD_ENV = "build_env";
    String CHECK_PERMISSIN = "check_permission";
    String IS_MARKET_DELAY = "is_market_delay";
    String FLAG_FOR_MYSTOCK_ZIXUAN = "FLAG_FOR_MYSTOCK_ZIXUAN";
    String FLAG_FOR_TAB_WITHOUT_MYSTOCK_CLICK = "FLAG_FOR_TAB_WITHOUT_MYSTOCK_CLICK";
    String NEW_CMS_INFO_CACHE="new_cms_info_cache";
    String NEW_CMS_INFO_CONF_VERSION="new_cms_info_conf_version";

    String HOME_PUSH_CMSG_FLAG = "home_push_cmsg_flag";
}
