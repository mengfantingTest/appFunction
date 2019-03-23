package com.uiautomator.babyfs;


public class Constants {


    // 引入指定目录配置文件
    static final String Path_AUA2 = "/sdcard/AU";
    static final String Path_properties = "/sdcard/AU/element.properties";
    private static final ProUtil properties = new ProUtil(Constants.Path_properties);

    //用例路径
    static final String Path_case = properties.getPro("Path_case");
    //报告路径
//    static final String Path_html = properties.getPro("Path_html");
    static final String Path_html = "/sdcard/测试结果.html";
    //截图文件夹路径
//    static final String Path_picture = properties.getPro("Path_picture");
    static final String Path_picture = "/sdcard/AU_picture/";

    //截取用例集名称
    private static final String Case_name = Path_case.substring(Path_case.lastIndexOf("/") + 1);//取最后一个/后面所有的字符
    static final String Case_name1 = Case_name.substring(0, Case_name.lastIndexOf("."));//取最后一个.前面所有的字符

    //布局classname
//    static final String layout = "android.widget.FrameLayout";
    //布局classname
    static final String layout = "android.widget.LinearLayout";

    //log标签
    static final String TAG = "自动化测试";

    //用例分隔符号
    static final String delime = "|";

    //注册用手机号
    static final String registeredPhone = "18281094167";

    //登录用手机号
    static final String loginPhone = "19952003646";

    //登录密码
    static final String loginPassword = "mft666666";

}
