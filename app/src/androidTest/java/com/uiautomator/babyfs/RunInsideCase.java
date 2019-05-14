package com.uiautomator.babyfs;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import javax.mail.MessagingException;

import static com.uiautomator.babyfs.Constants.Path_picture;
import static com.uiautomator.babyfs.Constants.TAG;

public class RunInsideCase {


    @Before
    public void before() {
        Log.e(TAG, "开始初始化");
        KeyWord.instrumentation = InstrumentationRegistry.getInstrumentation();
        KeyWord.mDevice = UiDevice.getInstance(KeyWord.instrumentation);
        KeyWord.context = KeyWord.instrumentation.getContext();
        KeyWord.newFolder(Path_picture);
        Log.e(TAG, "开始执行case");
    }


    @After
    public void after() throws Exception {
        GenerateReport.insideCaseWriteHtml(); //写入html文件
        Email.sendFile("app自动化测试报告","AndroidApp功能自动化测试","/sdcard/app.html","mft1027@163.com");//发送邮件
        ProUtil.openAssignFolder("/sdcard/app.html"); //打开html 文件
        Log.e(TAG, "测试结束");
    }


    @Test
    public void app() throws Exception {
        Case.Monitor();
        Case.profit(); //分享有礼
//        Case.feedBack(); //常见问题与意见反馈
        Case.logOutMonitor(); //移除监听器
    }

}

