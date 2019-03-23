package com.uiautomator.babyfs;

import android.os.RemoteException;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.util.Log;

import javax.mail.MessagingException;

import static com.uiautomator.babyfs.Constants.TAG;
import static com.uiautomator.babyfs.KeyWord.clickId;
import static com.uiautomator.babyfs.KeyWord.clickText;
import static com.uiautomator.babyfs.KeyWord.closeApp;
import static com.uiautomator.babyfs.KeyWord.home;
import static com.uiautomator.babyfs.KeyWord.inputId;
import static com.uiautomator.babyfs.KeyWord.judgment_text;
import static com.uiautomator.babyfs.KeyWord.monitorId;
import static com.uiautomator.babyfs.KeyWord.monitorText;
import static com.uiautomator.babyfs.KeyWord.openApp;
import static com.uiautomator.babyfs.KeyWord.wakeScreen;
import static com.uiautomator.babyfs.ProUtil.load;
import static java.lang.Thread.sleep;

class MonkeyTest {



    static void email() throws MessagingException {
        String detailTxt = "/sdcard/A-monkey/detail.txt";
        String errorTxt = "/sdcard/A-monkey/error.txt";
        String errorPicture = "/sdcard/A-monkey/error.png";
        String e = load(errorTxt);
        if (e.contains("CRASH") || e.contains("ANR") || e.contains("Error") || e.contains("Exception") || e.contains("error") || e.contains("anr")) {
            Email.sendFile("AndroidApp稳定性测试结果",load(detailTxt)+"\n---------------异常日志--------------\n"+ load(errorTxt),errorPicture,"mft1027@163.com");
            Log.e(TAG, "monkey: 邮件发送成功");
        }else {
            Log.e(TAG, "monkey: 没有错误日志");
        }
    }

    static void login() throws UiObjectNotFoundException, InterruptedException, RemoteException {
        //唤醒屏幕
        wakeScreen();
        closeApp("cn.babyfs.android");
        home();
        //注册监听器
        monitorText("去授权", "去授权");
        monitorText("继续安装", "继续安装");
        monitorText("安装", "安装");
        monitorText("确认安装", "确认安装");
        monitorText("确定", "确定");
        monitorText("确认", "确认");
        monitorText("允许", "允许");
        monitorText("同意", "同意");
        monitorText("总是允许", "总是允许");
        monitorText("始终允许", "始终允许");
        monitorText("开启启蒙之旅", "开启启蒙之旅");
        monitorText("取消", "取消");
        monitorId("跳过", "cn.babyfs.android:id/tv_count_down");
//打开app
        openApp("cn.babyfs.android/.home.view.SplashActivity");
//测试手机号+密码，登录
        clickText("我的");
        if (!judgment_text("登录/注册")) {
            clickText("学习");
            Log.e(TAG, "账号已经登录，开始测试");
        } else if (judgment_text("登录/注册")) {
            clickText("登录/注册");
            if (judgment_text("更换登录账号")) {
                clickText("密码登录");
                inputId("cn.babyfs.android:id/et_pwd", "mft666");
                clickId("cn.babyfs.android:id/login");
                clickText("跳过");
                clickText("学习");
                sleep(4000);
            } else if (judgment_text("使用手机号注册")) {
                clickText("登录");
                clickText("使用手机号码登录");
                clickText("密码登录");
                inputId("cn.babyfs.android:id/bw_et_phone", "10071087596");
                inputId("cn.babyfs.android:id/et_pwd", "mft666");
                clickId("cn.babyfs.android:id/login");
                clickText("跳过");
                clickText("学习");
                sleep(4000);
                clickText("知道了");
                clickText("知道了");
                clickText("知道了");
                clickText("知道了");
                clickId("cn.babyfs.android:id/rl_lesson_layout");
                sleep(2000);
            }
        }
    }
}
