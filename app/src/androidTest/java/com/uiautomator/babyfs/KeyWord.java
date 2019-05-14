package com.uiautomator.babyfs;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.RemoteException;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.UiWatcher;
import android.support.test.uiautomator.Until;
import android.util.Log;

import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.os.SystemClock.sleep;
import static com.uiautomator.babyfs.Constants.Path_picture;
import static com.uiautomator.babyfs.Constants.TAG;
import static com.uiautomator.babyfs.Constants.layout;
import static com.uiautomator.babyfs.GenerateReport.contentFail;
import static com.uiautomator.babyfs.GenerateReport.contentPass;

@RunWith(AndroidJUnit4.class)
public class KeyWord {

    static Instrumentation instrumentation;
    static UiDevice mDevice;
    static Context context;

    private static int waitTime = 20 * 1000;

    //调用shell命令
    public static void shell(String string) {
        try {
            mDevice.executeShellCommand(string);//执行一个shell命令，需要5.0以上手机
            Log.e(Constants.TAG, "调用shell命令成功" + string);
        } catch (IOException e) {
            Log.e(Constants.TAG, "调用shell命令失败" + string);
            e.printStackTrace();
        }
    }

    //启动宝玩app2，通过命令启动
    public static void openApp(String string) {
        try {
            mDevice.executeShellCommand("am start -n " + string);//执行一个shell命令，需要5.0以上手机
            Log.e(Constants.TAG, "启动app成功");
            mDevice.wait(Until.hasObject(By.pkg(string).depth(0)), 20);
            contentPass("启动app", "openApp", string, "");
        } catch (IOException e) {
            contentFail("启动app", "openApp", string, "");
            Log.e(Constants.TAG, "openapp: 打开app失败");
            e.printStackTrace();
        }
    }

    //启动宝玩app,部分版本的手机无法启动
    public static void openApp2(String string) {
        Log.e(TAG, "启动app");
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(string);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        sleep(8000);
        mDevice.wait(Until.hasObject(By.pkg(string).depth(0)), 20);
    }


    //关闭app
    public static void closeApp(String string) {
        try {
            sleep(2000);
            mDevice.executeShellCommand("am force-stop " + string);
            Log.e(TAG, "关闭app成功");
            contentPass("关闭app", "closeApp", string, "");
        } catch (IOException e) {
            contentFail("关闭app", "closeApp", string, "");
            Log.e(TAG, "关闭app失败");
            e.printStackTrace();
        }
    }

    //清除app的数据和缓存
    public static void clearData(String string) {
        try {
            mDevice.executeShellCommand("pm clear " + string);
            Log.e(TAG, "清除app的数据和缓存成功");
            contentPass("清除app的数据和缓存", "clearData", string, "");
        } catch (IOException e) {
            contentFail("清除app的数据和缓存", "clearData", string, "");
            e.printStackTrace();
            Log.e(TAG, "清除app的数据和缓存失败");
        }
    }

    //注册监听器1,并且点击对应text
    public static void monitorText(final String MonitorName, final String name) {
        Log.e(TAG, MonitorName + " 监听器注册成功");
        mDevice.registerWatcher(MonitorName, new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                if (mDevice.hasObject(By.text(name))) {
                    Log.e(TAG, MonitorName + " 监听器被触发啦！");
                    final UiObject2 x = mDevice.findObject(By.text(name));
                    try {
                        x.click();
                        Log.e(TAG, "点击text: " + name + " 成功");
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
    }


    //注册监听器1,并且点击对应id
    public static void monitorId(final String MonitorName, final String name) {
        Log.e(TAG, MonitorName + " 监听器注册成功");
        mDevice.registerWatcher(MonitorName, new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                if (mDevice.hasObject(By.res(name))) {
                    Log.e(TAG, MonitorName + " 监听器被触发啦！");
                    final UiObject2 x = mDevice.findObject(By.res(name));
                    try {
                        x.click();
                        Log.e(TAG, "点击id: " + name + " 成功");
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
    }

    //移除监听器
    public static void removeMonitor(String MonitorName) {
        mDevice.removeWatcher(MonitorName);//移除名称为"MonitorName"的监听器
        Log.e(TAG, "监听器移除成功: " + MonitorName);

    }

    //唤醒屏幕
    public static void wakeScreen() throws RemoteException {
        if (!mDevice.isScreenOn()) {  //唤醒屏幕
            mDevice.wakeUp();
            Log.e(TAG, "唤醒屏幕成功");
            contentPass("唤醒屏幕", "wakeScreen", "", "");
        } else {
            contentFail("唤醒屏幕", "wakeScreen", "", "");
            Log.e(TAG, "手机处于亮屏状态");
        }
    }


    //截图
    public static void screenShot(String name) {
        File file = new File(Path_picture);
        if (!file.exists()) {
            file.mkdir();
        }
        mDevice.takeScreenshot(new File(Path_picture + name + ".png"));
        Log.e(TAG, "截图成功");
    }

    //在sdcard上，删除、新建文件夹
    public static void newFolder(String name) {
        try {
            mDevice.executeShellCommand("rm -r " + name);
            mDevice.executeShellCommand("mkdir " + name);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 删除文件夹和里面的文件
    public static void deletePath(String string) {
        File file = new File(string);
        if (file.exists()) {
            deleteFile(file);
        }

    }

    // 删除文件夹和里面的文件
    private static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                deleteFile(f);
            }
//            file.delete();//保留文件夹，只删除里面的文件
            Log.e(TAG, "deleteFile: 删除文件夹成功：" + file);
        } else if (file.exists()) {
            file.delete();
            Log.e(TAG, "deleteFile: 删除文件成功：" + file);
        }
    }


    //安装apk,传入路径
    public static void installApk(String string) {
        try {
            Log.e(TAG, "开始安装app：" + string);
            mDevice.executeShellCommand("pm install -t -r -d " + string);//-t 允许测试apk被安装 -r 重新安装应用，且保留应用数据 -d 允许降级安装（同一应用低级换高级）
            Log.e(TAG, "安装成功app：" + string);
            sleep(2000);
            contentPass("安装app", "installApk", string, "");
        } catch (IOException e) {
            contentFail("安装app", "installApk", string, "");
            e.printStackTrace();
        }
    }

    //卸载apk,传入包名
    public static void unInstallApk(String string) {
        if (isAppInstalled(string)) {
            try {
                Log.e(TAG, "开始卸载app：" + string);
                mDevice.executeShellCommand("pm uninstall " + string);
                Log.e(TAG, "卸载成功app：" + string);
                contentPass("卸载app", "unInstallApk", string, "");
            } catch (IOException e) {
                contentFail("卸载app", "unInstallApk", string, "");
                e.printStackTrace();
            }
        } else {
            Log.e(TAG, "要卸载的app不存在: " + string);
        }
    }


    //通过text点击
    public static void clickText(String name) throws UiObjectNotFoundException {
        mDevice.wait(Until.findObject(By.text(name)), waitTime);
        UiObject2 x = mDevice.findObject(By.text(name));
        if (x != null) {
            x.click();
            Log.e(TAG, "点击text: " + name + " 成功");
            contentPass("点击text", "clickText", name, "");
        } else {
            Log.e(TAG, "没找到: " + name + " 开始滚动屏幕查找");
            slideFindText(name);
            UiObject2 y = mDevice.findObject(By.text(name));
            if (y != null) {
                y.click();
                Log.e(TAG, "点击text: " + name + " 成功");
                contentPass("点击text", "clickText", name, "");
            } else {
                screenShot("点击text失败");
//                RunExternalCase.judge = "fail";
                Log.e(TAG, "点击text: " + name + " 失败");
                contentFail("点击text", "clickText", name, "");

            }
        }
    }


    //通过id点击
    public static void clickId(String name) throws UiObjectNotFoundException {
        mDevice.wait(Until.findObject(By.res(name)), waitTime);
        UiObject2 x = mDevice.findObject(By.res(name));
        if (x != null) {
            x.click();
            Log.e(TAG, "点击id: " + name + " 成功");
            contentPass("点击id", "clickId", name, "");
        } else {
            Log.e(TAG, "没找到: " + name + " 开始滚动屏幕查找");
            slideFindId(name);
            UiObject2 y = mDevice.findObject(By.res(name));
            if (y != null) {
                y.click();
                Log.e(TAG, "点击id: " + name + " 成功");
                contentPass("点击id", "clickId", name, "");
            } else {
                screenShot("点击id失败");
//                RunExternalCase.judge = "fail";
                Log.e(TAG, "点击id: " + name + " 失败");
                contentFail("点击id", "clickId", name, "");

            }
        }
    }

    //断言text存在
    public static void assertText(String name) throws UiObjectNotFoundException {
        mDevice.wait(Until.findObject(By.text(name)), waitTime);
        UiObject2 x = mDevice.findObject(By.text(name));
        if (x != null) {
            Log.e(TAG, "text断言成功: " + name);
            contentPass("text断言", "assertText", name, "");
        } else {
            Log.e(TAG, "没找到: " + name + " 开始滚动屏幕查找");
            slideFindText(name);
            UiObject2 y = mDevice.findObject(By.text(name));
            if (y != null) {
                Log.e(TAG, "text断言成功: " + name);
                contentPass("text断言", "assertText", name, "");
            } else {
                screenShot("text断言失败-" + name);
//                RunExternalCase.judge = "fail";
                contentFail("text断言", "assertText", name, "");

            }
        }
    }

    //断言text不存在
    public static void assertTextNull(String name) {
        mDevice.wait(Until.findObject(By.text(name)), waitTime);
        UiObject2 x = mDevice.findObject(By.text(name));
        if (x == null) {
            Log.e(TAG, "text断言成功: " + name);
            contentPass("text断言", "assertTextNull", name, "");
        } else {
            screenShot("text断言失败-" + name);
//            RunExternalCase.judge = "fail";
            contentFail("text断言", "assertTextNull", name, "");

        }
    }


    //断言id存在
    public static void assertId(String name) throws UiObjectNotFoundException {
        mDevice.wait(Until.findObject(By.res(name)), waitTime);
        UiObject2 x = mDevice.findObject(By.res(name));
        if (x != null) {
            Log.e(TAG, "id断言成功: " + name);
            contentPass("id断言", "assertId", name, "");
        } else {
            Log.e(TAG, "没找到: " + name + " 开始滚动屏幕查找");
            slideFindId(name);
            UiObject2 y = mDevice.findObject(By.res(name));
            if (y != null) {
                Log.e(TAG, "id断言成功: " + name);
                contentPass("id断言", "assertId", name, "");
            } else {
                screenShot("id断言失败");
//                RunExternalCase.judge = "fail";
                Log.e(TAG, "id断言失败-" + name);
                contentFail("id断言", "assertId", name, "");

            }
        }
    }

    //断言id不存在
    public static void assertIdNull(String name) {
        mDevice.wait(Until.findObject(By.res(name)), waitTime);
        UiObject2 x = mDevice.findObject(By.res(name));
        if (x == null) {
            Log.e(TAG, "id断言成功: " + name);
            contentPass("id断言", "assertIdNull", name, "");
        } else {
            screenShot("id断言失败-" + name);
//            RunExternalCase.judge = "fail";
            contentFail("id断言", "assertIdNull", name, "");

        }
    }


    //根据id输入文本
    public static void inputId(String name1, String name2) throws UiObjectNotFoundException {
        mDevice.wait(Until.findObject(By.res(name1)), waitTime);
        UiObject2 x = mDevice.findObject(By.res(name1));
        if (x != null) {
            x.setText(name2);
            Log.e(TAG, "输入成功: " + name2);
            contentPass("输入", "inputId", name1, name2);
        } else {
            Log.e(TAG, "没找到: " + name1 + " 开始滚动屏幕查找");
            slideFindId(name1);
            UiObject2 y = mDevice.findObject(By.res(name1));
            if (y != null) {
                y.setText(name2);
                Log.e(TAG, "输入成功: " + name2);
                contentPass("输入", "inputId", name1, name2);
            } else {
                screenShot("id输入失败");
//                RunExternalCase.judge = "fail";
                Log.e(TAG, "输入失败: " + name2);
                contentFail("输入", "inputId", name1, name2);
            }

        }

    }

    //根据text输入文本
    public static void inputText(String name1, String name2) throws UiObjectNotFoundException {
        mDevice.wait(Until.findObject(By.text(name1)), waitTime);
        UiObject2 x = mDevice.findObject(By.text(name1));
        if (x != null) {
            x.setText(name2);
            Log.e(TAG, "输入成功: " + name2);
            contentPass("输入", "inputText", name1, name2);
        } else {
            Log.e(TAG, "没找到: " + name1 + " 开始滚动屏幕查找");
            slideFindText(name1);
            UiObject2 y = mDevice.findObject(By.text(name1));
            if (y != null) {
                y.setText(name2);
                Log.e(TAG, "输入成功: " + name2);
                contentPass("输入", "inputText", name1, name2);
            } else {
                screenShot("text输入失败");
//                RunExternalCase.judge = "fail";
                Log.e(TAG, "输入失败: " + name2);
                contentFail("输入", "inputText", name1, name2);

            }
        }

    }

    //根据id清空文本
    public static void cleraId(String name1) throws UiObjectNotFoundException {
        mDevice.wait(Until.findObject(By.res(name1)), waitTime);
        UiObject2 x = mDevice.findObject(By.res(name1));
        if (x != null) {
            x.clear();//清空输入框
            Log.e(TAG, "清空输入框成功: " + name1);
            contentPass("清空输入框", "cleraId", name1, "");
        } else {
            Log.e(TAG, "没找到: " + name1 + " 开始滚动屏幕查找");
            slideFindId(name1);
            UiObject2 y = mDevice.findObject(By.res(name1));
            if (y != null) {
                y.clear();//清空输入框
                Log.e(TAG, "清空输入框成功: " + name1);
                contentPass("清空输入框", "cleraId", name1, "");
            } else {
                screenShot("id清空输入框失败");
//                RunExternalCase.judge = "fail";
                Log.e(TAG, "清空输入框失败: " + name1);
                contentFail("清空输入框", "cleraId", name1, "");

            }
        }

    }

    //根据text清空文本
    public static void cleraText(String name1) throws UiObjectNotFoundException {
        mDevice.wait(Until.findObject(By.text(name1)), waitTime);
        UiObject2 x = mDevice.findObject(By.text(name1));
        if (x != null) {
            x.clear();//清空输入框
            Log.e(TAG, "清空输入框成功: " + name1);
            contentPass("清空输入框", "cleraText", name1, "");
        } else {
            Log.e(TAG, "没找到: " + name1 + " 开始滚动屏幕查找");
            slideFindText(name1);
            UiObject2 y = mDevice.findObject(By.text(name1));
            if (y != null) {
                y.clear();//清空输入框
                Log.e(TAG, "清空输入框成功: " + name1);
                contentPass("清空输入框", "cleraText", name1, "");
            } else {
                screenShot("text清空输入框失败");
//                RunExternalCase.judge = "fail";
                Log.e(TAG, "清空输入框失败: " + name1);
                contentFail("清空输入框", "cleraText", name1, "");
            }
        }
    }

    //向左滑动
    public static void swipeLeft(String time) {
        sleep(1000);

        int x = mDevice.getDisplayWidth();//获取屏幕的宽
        int y = mDevice.getDisplayHeight();//获取屏幕的高
        Log.e(TAG, "屏幕分辨率: " + String.valueOf(x) + " X " + String.valueOf(y));
        mDevice.swipe((int) (x / 1.3), y / 2, x / 6, y / 2, Integer.parseInt(time));//左滑
        Log.e(TAG, "左滑");
        sleep(500);
    }

    //向右滑动
    public static void swipeRight(String time) {
        sleep(1000);
        int x = mDevice.getDisplayWidth();//获取屏幕的宽
        int y = mDevice.getDisplayHeight();//获取屏幕的高
        Log.e(TAG, "屏幕分辨率: " + String.valueOf(x) + " X " + String.valueOf(y));
        mDevice.swipe(x / 6, y / 2, (int) (x / 1.3), y / 2, Integer.parseInt(time));//右滑
        Log.e(TAG, "右滑");
        sleep(500);
    }

    //向下滑动
    public static void swipeDown(String time) {
        sleep(1000);
        int x = mDevice.getDisplayWidth();//获取屏幕的宽
        int y = mDevice.getDisplayHeight();//获取屏幕的高
        mDevice.swipe(x / 2, y / 6, x / 2, (int) (y / 1.2), Integer.parseInt(time));//下滑
        Log.e(TAG, "下滑");
        sleep(500);
    }

    //向上滑动
    public static void swipeUp(String time) {
        sleep(1000);
        int x = mDevice.getDisplayWidth();//获取屏幕的宽
        int y = mDevice.getDisplayHeight();//获取屏幕的高
        mDevice.swipe(x / 2, (int) (y / 1.2), x / 2, y / 6, Integer.parseInt(time));//上滑
        Log.e(TAG, "上滑");
        sleep(500);
    }

    //等待
    public static void wait(String time) {
        Log.e(TAG, "等待: " + time + " 毫秒");
        sleep(Long.parseLong(time));
    }

    //返回键
    public static void back() {
        mDevice.pressBack();
        sleep(1000);
        Log.e(TAG, "返回键");
    }

    //home键
    public static void home() {
        mDevice.pressHome();
        sleep(1000);
        Log.e(TAG, "home键");
    }

    //多任务键
    public static void recentApps() throws RemoteException {
        mDevice.pressRecentApps();
        sleep(1000);
        Log.e(TAG, "多任务键");

    }

    //滚动屏幕找到指定text
    public static void slideFindText(String string) throws UiObjectNotFoundException {
        UiScrollable scroll = new UiScrollable(new UiSelector().className(layout));
//        scroll.setMaxSearchSwipes(1);//设置最大滚动次数，没有此代码则一直滚动
        boolean text = scroll.scrollIntoView(new UiSelector().text(string));//滚动到某个对象，scrollIntoView里面的对象只能是UiObject或UiSelector
        if (text) {
            Log.e(TAG, "找到了text：" + string);
            contentPass("滚动屏幕找到指定text", "slideFindText", string, "");
        } else {
//            RunExternalCase.judge = "fail";
            Log.e(TAG, "没找到text：" + string);
            contentFail("滚动屏幕找到指定text", "slideFindText", string, "");

        }
    }


    //滚动屏幕找到指定id
    public static void slideFindId(String string) throws UiObjectNotFoundException {
        UiScrollable scroll = new UiScrollable(new UiSelector().className(layout));
//        scroll.setMaxSearchSwipes(1);//设置最大滚动次数，没有此代码则一直滚动
        boolean id = scroll.scrollIntoView(new UiSelector().resourceId(string));//滚动到某个对象，scrollIntoView里面的对象只能是UiObject或UiSelector
        if (id) {
            Log.e(TAG, "找到了id：" + string);
            contentPass("滚动屏幕找到指定id", "slideFindId", string, "");
        } else {
//            RunExternalCase.judge = "fail";
            Log.e(TAG, "没找到id：" + string);
            contentFail("滚动屏幕找到指定id", "slideFindId", string, "");
        }
    }

    //通过text长按
    public static void clickTextLong(String name) throws UiObjectNotFoundException {
        mDevice.wait(Until.findObject(By.text(name)), waitTime);
        UiObject2 x = mDevice.findObject(By.text(name));
        if (x != null) {
            x.longClick();
            Log.e(TAG, "长按text: " + name + " 成功");
            contentPass("长按text", "clickTextLong", name, "");
        } else {
            Log.e(TAG, "没找到: " + name + " 开始滚动屏幕查找");
            slideFindText(name);
            UiObject2 y = mDevice.findObject(By.text(name));
            if (y != null) {
                y.longClick();
                Log.e(TAG, "长按text: " + name + " 成功");
                contentPass("长按text", "clickTextLong", name, "");
            } else {
//                RunExternalCase.judge = "fail";
                Log.e(TAG, "长按text: " + name + " 失败");
                contentFail("长按text", "clickTextLong", name, "");
            }
        }
    }

    //通过id长按
    public static void clickIdLong(String name) throws UiObjectNotFoundException {
        mDevice.wait(Until.findObject(By.res(name)), waitTime);
        UiObject2 x = mDevice.findObject(By.res(name));
        if (x != null) {
            x.longClick();
            Log.e(TAG, "长按id: " + name + " 成功");
            contentPass("长按id", "clickIdLong", name, "");
        } else {
            Log.e(TAG, "没找到: " + name + " 开始滚动屏幕查找");
            slideFindId(name);
            UiObject2 y = mDevice.findObject(By.res(name));
            if (y != null) {
                y.longClick();
                Log.e(TAG, "长按id: " + name + " 成功");
                contentPass("长按id", "clickIdLong", name, "");
            } else {
//                RunExternalCase.judge = "fail";
                Log.e(TAG, "长按id: " + name + " 失败");
                contentFail("长按id", "clickIdLong", name, "");
            }
        }
    }

    //打开系统通知栏
    public static void openNotification() {
        mDevice.openNotification();
        Log.e(TAG, "打开系统通知栏");
    }

    //调用浏览器打开网址
    public static void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        Log.e(TAG, "打开: " + url + "成功");
    }


    //id点击前后，selected选中状态是否不同
    public static void selectedId(String name) {
        mDevice.wait(Until.findObject(By.res(name)), waitTime);
        UiObject2 x = mDevice.findObject(By.res(name));
        boolean ob = x.isSelected();
        Log.e(TAG, "id: " + name + " 选中状态：" + ob);
        x.click();
        Log.e(TAG, "点击id: " + name + " 成功");
        sleep(2000);
        UiObject2 x1 = mDevice.findObject(By.res(name));
        boolean ob1 = x1.isSelected();
        Log.e(TAG, "id: " + name + " 选中状态：" + ob1);
        if (ob != ob1) {
            contentPass("点击后，选中状态变化", "selectedId", name, "");
        } else {
            contentFail("点击后，选中状态变化", "selectedId", name, "");
//            RunExternalCase.judge = "fail";
        }
    }

    //断言，id为选中状态
    public static void selectedIdTrue(String name) {
        sleep(2000);
        mDevice.wait(Until.findObject(By.res(name)), waitTime);
        UiObject2 x = mDevice.findObject(By.res(name));
        boolean ob = x.isSelected();
        Log.e(TAG, "id: " + name + " 选中状态：" + ob);
        if (ob) {
            contentPass("检查按钮选中状态", "selectedIdTrue", name, "");
        } else {
            contentFail("检查按钮选中状态", "selectedIdTrue", name, "");
        }

    }

    //断言，id为非选中状态
    public static void selectedIdFalse(String name) {
        sleep(2000);
        mDevice.wait(Until.findObject(By.res(name)), waitTime);
        UiObject2 x = mDevice.findObject(By.res(name));
        boolean ob = x.isSelected();
        Log.e(TAG, "id: " + name + " 选中状态：" + ob);
        if (!ob) {
            contentPass("检查按钮选中状态", "selectedIdFalse", name, "");
        } else {
            contentFail("检查按钮选中状态", "selectedIdFalse", name, "");
        }
    }


    //////////////////////////////////////////////////////////////////////////////内部方法
    //判断app是否安装，布尔值，内部方法
    public static boolean isAppInstalled(String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        List<String> pName = new ArrayList<>();
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);
    }

    //判断text否存在，布尔值，内部方法
    public static boolean judgment_text(String name) {
        mDevice.wait(Until.findObject(By.text(name)), waitTime);
        UiObject2 x = mDevice.findObject(By.text(name));
        if (x != null) {
            Log.e(TAG, "text“ " + name + " ”存在 ");
            return true;
        } else {
            Log.e(TAG, "text“ " + name + " ”不存在 ");
            return false;
        }
    }

    //判断id否存在，布尔值，内部方法
    public static boolean judgment_id(String name) {
//        mDevice.wait(Until.findObject(By.res(name)), waitTime);
        UiObject2 x = mDevice.findObject(By.res(name));
        if (x != null) {
            Log.e(TAG, "text“ " + name + " ”存在 ");
            return true;
        } else {
            Log.e(TAG, "text“ " + name + " ”不存在 ");
            return false;
        }
    }


}


//调整代码格式command+option+L
//替换command+R