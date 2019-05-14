package com.uiautomator.babyfs;

import android.support.test.uiautomator.UiObjectNotFoundException;

import static com.uiautomator.babyfs.GenerateReport.caseName;
import static com.uiautomator.babyfs.KeyWord.assertId;
import static com.uiautomator.babyfs.KeyWord.assertText;
import static com.uiautomator.babyfs.KeyWord.assertTextNull;
import static com.uiautomator.babyfs.KeyWord.back;
import static com.uiautomator.babyfs.KeyWord.clickId;
import static com.uiautomator.babyfs.KeyWord.clickText;
import static com.uiautomator.babyfs.KeyWord.closeApp;
import static com.uiautomator.babyfs.KeyWord.inputId;
import static com.uiautomator.babyfs.KeyWord.judgment_id;
import static com.uiautomator.babyfs.KeyWord.judgment_text;
import static com.uiautomator.babyfs.KeyWord.monitorId;
import static com.uiautomator.babyfs.KeyWord.monitorText;
import static com.uiautomator.babyfs.KeyWord.openApp;
import static com.uiautomator.babyfs.KeyWord.removeMonitor;
import static com.uiautomator.babyfs.KeyWord.selectedId;
import static com.uiautomator.babyfs.KeyWord.selectedIdFalse;
import static com.uiautomator.babyfs.KeyWord.slideFindId;
import static com.uiautomator.babyfs.KeyWord.swipeDown;
import static java.lang.Thread.sleep;

class Case {

    //注册监听器
    static void Monitor() {
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
        monitorId("不再显示", "cn.babyfs.android:id/cbDisplay");
        monitorId("关闭弹窗", "cn.babyfs.android:id/close");


    }

    //移除监听器
    static void logOutMonitor() {
        removeMonitor("去授权");
        removeMonitor("继续安装");
        removeMonitor("安装");
        removeMonitor("确认安装");
        removeMonitor("确定");
        removeMonitor("确认");
        removeMonitor("允许");
        removeMonitor("同意");
        removeMonitor("总是允许");
        removeMonitor("始终允许");
        removeMonitor("开启启蒙之旅");
        removeMonitor("取消");
    }

    //注册监听器2
    private static void Monitor2() {
        monitorText("确定", "确定");
        monitorText("确认", "确认");
        monitorText("允许", "允许");
        monitorText("同意", "同意");
        monitorText("总是允许", "总是允许");
        monitorText("始终允许", "始终允许");
    }

    //移除监听器2
    private static void logOutMonitor2() {
        removeMonitor("确定");
        removeMonitor("确认");
        removeMonitor("允许");
        removeMonitor("同意");
        removeMonitor("总是允许");
        removeMonitor("始终允许");
    }

    //分享有礼
    static void profit() throws UiObjectNotFoundException {
        caseName("分享有礼");
        closeApp("cn.babyfs.android");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("我的");
        clickText("分享有礼");
        assertText("分享有礼");
    }

    //常见问题与意见反馈
    static void feedBack() throws UiObjectNotFoundException {
        caseName("常见问题与意见反馈");
        closeApp("cn.babyfs.android");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("我的");
        clickText("常见问题与意见反馈");
        assertText("常见问题");
        clickId("cn.babyfs.android:id/toolbar_tv_right");
        assertText("我的反馈");
        assertText("自动化测试，自动化测试");
        clickText("写反馈");
        assertText("提交反馈");
        assertText("功能异常");
        assertText("APP使用");
        assertText("优化建议");
        assertText("学习疑问");
        clickId("cn.babyfs.android:id/fd_diagnose");
        assertText("诊断工具");
        assertText("以下功能在成长兔英语团队指导下使用：");
        assertText("上传日志");
        assertText("上传网络诊断");
        back();
        inputId("cn.babyfs.android:id/fd_desc", "自动化测试，自动化测试");
        clickText("提交");
        assertText("我的反馈");
        swipeDown("10");
        assertText("自动化测试，自动化测试");
    }

    // 学习圈_发布笔记
    static void groupSend() throws UiObjectNotFoundException {
        caseName("学习圈_发布笔记");
        openApp("cn.babyfs.android/.home.view.SplashActivity");
        clickText("学习圈");
        clickId("cn.babyfs.android:id/iv_right");
        inputId("cn.babyfs.android:id/note_text", "成长兔英语，成长兔英语");
        clickText("笔记主题");
        assertText("绘本");
        assertText("我娃我秀");
        clickText("学习笔记");
        clickText("发布");
        clickText("我的");
        swipeDown("10");
        swipeDown("10");
        swipeDown("10");
        assertText("成长兔英语，成长兔英语");
        clickId("cn.babyfs.android:id/flShare");
        assertText("微信");
        assertText("朋友圈");
        clickText("关闭");
        clickId("cn.babyfs.android:id/flComment");
        inputId("cn.babyfs.android:id/editText", "厉害");
        clickText("发布");
        assertText("厉害");
        clickId("cn.babyfs.android:id/ivMore");
        assertText("微信");
        assertText("朋友圈");
        assertText("删除");
        clickText("删除");
        clickText("删除");
        assertTextNull("成长兔英语，成长兔英语");
        closeApp("cn.babyfs.android");
    }



}
