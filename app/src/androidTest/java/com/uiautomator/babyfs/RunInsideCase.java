package com.uiautomator.babyfs;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.uiautomator.babyfs.Constants.Path_picture;
import static com.uiautomator.babyfs.Constants.TAG;

public class RunInsideCase {


    @Before
    public void before() {
        Log.e(TAG, "开始初始化");
        KeyWord.instrumentation = InstrumentationRegistry.getInstrumentation();
        KeyWord.mDevice = UiDevice.getInstance(KeyWord.instrumentation);
        KeyWord.context = KeyWord.instrumentation.getContext();
        //删除picture文件夹
        KeyWord.deletePath(Path_picture);
    }


    @After
    public void after() {
        Log.e(TAG, "测试结束");
    }


    @Test
    public void insideCase() throws Exception {
        Log.e(TAG, "开始执行内部case");
        Case.Monitor();
        KeyWord.clearData("cn.babyfs.android");//清除app数据
        Case.onBoarding();
        Case.registered(); //手机号注册
        Case.login(); //手机号+密码，登录
        Case.logOutMonitor(); //移除监听器
        Case.systemMessage(); //系统消息
        Case.ivScan(); //扫一扫
        Case.profit(); //分享有礼
        Case.courseRenew(); //课程续费
        Case.shoppingMall(); //积分兑换
        Case.feedBack(); //常见问题与意见反馈
        Case.setParent(); //设置-家长信息
        Case.setBaby(); //设置-宝宝信息
        Case.accountWeChat();//发送验证码
        Case.accountPhone();//发送验证码
        Case.accountPassword();//发送验证码
        Case.accountDevices(); //设置-账户安全-设备管理
        Case.courseCard(); //设置-课程卡关联
        Case.collectibles(); //设置-收藏
        Case.tapeSwitch(); //设置-互动课录音自动开始
        Case.flowSwitch(); //设置-启动2G／3G／4G流量提醒
        Case.cache(); //设置-清除缓存
        Case.updateCheck(); //设置-检查更新
        Case.browserKernel(); //设置-浏览器内核
        Case.encourage(); //设置-鼓励一下
        Case.about(); //关于宝玩、诊断工具
        Case.learningPage(); //学习_学习页面
        Case.courseWare(); //学习_课件
        Case.punch(); //学习_去打卡
        Case.menuMore(); //学习_课件更多功能
        Case.courseLevelSet(); //学习_合集
        Case.groupHome(); // 学习圈首页
        Case.groupSend(); // 学习圈_发布笔记

        GenerateReport.insideCaseWriteHtml(); //写入html文件
        Email.sendFile("app自动化测试报告","AndroidApp功能自动化测试","/sdcard/测试结果.html","mft1027@163.com");//发送邮件
        ProUtil.openAssignFolder("/sdcard/测试结果.html"); //打开html 文件
    }
    @Test
    public void monkeyEmail() throws Exception {
        MonkeyTest.email();
    }

    @Test
    public void monkeyLogin() throws Exception {
        MonkeyTest.login();
    }
}

