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
        Case.registered();
        Case.login();
        Case.logOutMonitor();
        Case.systemMessage();
        Case.ivScan();
        Case.profit();
        Case.courseRenew();
        Case.shoppingMall();
        Case.feedBack();
        Case.setParent();
        Case.setBaby();
        Case.accountWeChat();//发送验证码
        Case.accountPhone();//发送验证码
        Case.accountPassword();//发送验证码
        Case.accountDevices();
        Case.courseCard();
        Case.collectibles();
        Case.tapeSwitch();
        Case.flowSwitch();
        Case.cache();
        Case.updateCheck();
        Case.browserKernel();
        Case.encourage();
        Case.about();
        Case.learningPage();
        Case.courseWare();
        Case.punch();
        Case.menuMore();
        Case.courseLevelSet();
        Case.groupHome();
        Case.groupSend();

        GenerateReport.insideCaseWriteHtml(); //写入html文件
        Email.sendFile("自动化测试报告","正文","/sdcard/测试结果.html","mft1027@163.com");//发送邮件
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

