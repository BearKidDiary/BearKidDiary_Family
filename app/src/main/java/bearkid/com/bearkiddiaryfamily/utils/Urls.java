package bearkid.com.bearkiddiaryfamily.utils;

/**
 * Created by admin on 2016/7/28.
 */
public class Urls {
    public static final String BASE_URL = "http://172.27.35.1:8888/BearKidDiary_Service/";

    /**
     * 用户
     */
    //登陆
    public static final String URL_LOGIN = "user/login";
    //注册
    public static final String URL_REGIST = "user/regist";
    //更新用户信息
    public static final String URL_UPDATEUERINFO = "user/updateinfo";
    /**
     * 孩子
     */
    //添加孩子
    public static final String URL_ADDKID = "kid/add";
    //获取孩子信息
    public static final String URL_KID = "kid";
    //更新孩子信息
    public static final String URL_KID_UPDATE = "kid/update";
    //获取时间轴事件
    public static final String URL_TIMELINE = "kid/timeline";
    //添加时间轴事件
    public static final String URL_ADD_TIMELINE = "kid/timeline/add";
    //获取孩子身体数据
    public static final String URL_BODY = "kid/body";
    //添加孩子身体数据
    public static final String URL_BODY_ADD = "kid/body/add";
    /**
     * 家庭
     */
    //获取家庭成员
    public static final String URL_FAMILY_MEMBERS = "family/members";
    //删除家庭成员
    public static final String URL_REMOVE_FAMILY_MEMBERS = "family/members/remove";
}
