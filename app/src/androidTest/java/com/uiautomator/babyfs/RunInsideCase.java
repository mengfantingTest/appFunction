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
        Case.logOutMonitor(); //移除监听器
        Case.profit(); //分享有礼


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

    @Test
    public void mall() throws Exception {
        Email.sendFile("app自动化测试报告","AndroidApp功能自动化测试","/sdcard/测试结果.html","mft1027@163.com,mft1027@126.com");//发送邮件
    }
}

