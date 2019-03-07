package com.sh.justcoffee.constans;

/**
 * Created by Administrator on 2017/9/1.
 */

public class ConstValues {

    /**
     * 文件夹父路径
     */
    public static final String FILE_ROOT_DIRECTORY = "/WangLaoJi";
    public static final String FILE_DIRECTORY_IMG = FILE_ROOT_DIRECTORY+"/img";

    /**sharedpreference 判断是否已登录字段*/
    public static final String ISADD = "isAdd";

    public static final String ISLOGIN = "islogin";
    public static final String ISLOAN = "isloan";
    public static final String ISRECORD = "isrecord";
    public static final String USERID = "user_id";
    public static final String TOKEN = "token";
    public static final String USER_PHONE = "user_phone";
    public static final String USER_PWD = "user_pwd";
    public static final String USER_NAME = "user_name";
    public static final String USER_IDCARD = "user_idcard";
    public static final String USER_LATEST_LOGINTIME = "user_latest_logintime";
    public static final String USER_AUTHEN_STATUS = "user_status";
    public static final String USER_DEFAULT_BANKCARDNO = "user_default_card_no";
    public static final String USER_CREATETIME = "user_createtime";
    public static final String SERVICE_WEIXIN = "service_weixin";
    public static final String SERVICE_QQ = "service_qq";
    public static final String SERVICE_PHONE = "service_phone";
    public static final String SERVICE_TEL = "service_tel";
    public static final String SERVICE_PUHONENUM = "";
    public static final String SALT = "salt";
    public static final String IS_PAY = "is_pay";
    public static final String PUBLIC_KEY = "";

    /**
     * 服务器后台地址
     */
    public static final String BASE_URL = "https://qwy.hdyqzlg.com/client/";
//    public static final String BASE_URL = "http://192.168.4.5:8480/client/";
    public static final String BASE_LOCAL_URL = BASE_URL+"app/member/";
    public static final String BASE_PUNLIC_URL = BASE_URL+"common/";
    public static final String BASE_LOAN_URL = BASE_URL+"app/loan/";
    public static final String BASE_PAY_URL = BASE_URL+"app/payment/";

    //极验
    public static final String STARTCAPTCHA_URL = BASE_URL+"geetest/firstStep";
    public static final String SENDVERIFY_URL = BASE_URL+"geetest/secondStep";
    /**
     * 登录界面图形验证码获取url
     */
    public static final String IMG_CHECKNUM_URL = BASE_URL + "api/imgCode?phone=";

    /**
     * 注册协议
     */
    public static final String PROTOCOL_REGIST = BASE_URL+"api/zcxy";


    //fir.im id
    public static final String FIR_ID = "";
    //fir.im apitoken
    public static final String FIR_API_TOKEN = "";
    //bugly app key
    public static final String BUGLY_KEY = "";
    //魔蝎 apikey
    public static final String MOXIE_API_KEY ="moxie_api_key";
    //有盾商户pub_key ：
    public static final String YOUDUN_AUTHKEY = "youdun_authkey";
    //有盾回调地址
    public static final String YOUDUN_AUTHKEY_URL = "youdun_authkey_url";
    //富友移动支付key
    public static final String FUYOU_KEY = "";
    //富友支付商户号
    public static final String FUYOU_ID = "";
    //富有还款回调
    public static final String FUYOU_NOTIFY_URL_REPAY = BASE_URL+ "api/fy_hk";
    //富有续借回调
    public static final String FUYOU_NOTIFY_URL_RELOAN = BASE_URL+ "api/fy_xj";
    //富有延期回调
    public static final String FUYOU_NOTIFY_URL_EXTENSION = BASE_URL+ "api/fy_yq";
    //聚合支付key
    public static final String JUHEPAY_KEY = "";
    //聚合支付商户号
    public static final String JUHEPAY_MERID = "";
    //聚合支付回调地址还款
    public static final String JUHE_PAY_NOTIFYURL_hk = BASE_URL + "api/zfb_hk";
    //聚合支付回调地址续借
    public static final String JUHE_PAY_NOTIFYURL_XJ = BASE_URL + "api/zfb_xj";
    //聚合支付回调地址延期
    public static final String JUHE_PAY_NOTIFYURL_YANQI = BASE_URL + "api/zfb_yq";
    //快捷支付回调地址还款
    public static final String JUHE_QUICK_PAY_NOTIFYURL_hk = BASE_URL + "api/zfb_hk2";
    //快捷支付回调地址续借
    public static final String JUHE_QUICK_PAY_NOTIFYURL_XJ = BASE_URL + "api/zfb_xj2";
    //快捷支付回调地址延期
    public static final String JUHE_QUICK_PAY_NOTIFYURL_YANQI = BASE_URL + "api/zfb_yq2";

}
