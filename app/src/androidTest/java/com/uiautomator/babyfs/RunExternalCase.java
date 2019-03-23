package com.uiautomator.babyfs;

import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.StringTokenizer;

import static com.uiautomator.babyfs.Constants.Case_name1;
import static com.uiautomator.babyfs.Constants.Path_case;
import static com.uiautomator.babyfs.Constants.Path_picture;
import static com.uiautomator.babyfs.Constants.TAG;
import static com.uiautomator.babyfs.Constants.delime;
import static com.uiautomator.babyfs.KeyWord.clearData;
import static com.uiautomator.babyfs.KeyWord.selectedId;

public class RunExternalCase {


    public static String judge;

    // 读取txt文件内容，执行外部case
    @Test
    public void externalCase() throws IOException, RemoteException, UiObjectNotFoundException {

        Log.e(TAG, "开始检查测试环境");
        Environment.test();

        Log.e(TAG, "开始执行case: "+ Case_name1);
        FileReader fr = new FileReader(Path_case);//// 创建一个文件读取流对象，和文件相关联。
        BufferedReader br = new BufferedReader(fr);// 从fr中读取文本，缓冲各个字符
        String str;
        int row;
        int SerialNumber = 0;

        for (row = 1; row <= getTotalLines(); row++) {
            String keyword = "";
            String locator = "";
            String value = "";

            judge = "pass";


            //按行读取txt
            str = br.readLine();
            //判断内容中包含//或者为空
            if (str.contains("//")||str.equals("")){
                continue; //立刻跳转到下一次循环
            }
            //将读取到的内容，按指定字符分隔字符串
            StringTokenizer str1 = new StringTokenizer(str, delime, false);//false不返回分隔符
            //判断每行内容里的delime数量
            if ((getCount(str, delime)) == 0) {
                keyword = str1.nextToken();
            } else if ((getCount(str, delime)) == 1) {
                keyword = str1.nextToken();
                locator = str1.nextToken();
            } else if ((getCount(str, delime)) == 2) {
                keyword = str1.nextToken();
                locator = str1.nextToken();
                value = str1.nextToken();
            } else {
                Log.e(TAG, "用例格式错误");
            }

            //执行关键字对应的方法
            switch (keyword) {
                case "openApp":
                    KeyWord.openApp(locator);
                    break;
                case "closeApp":
                    KeyWord.closeApp(locator);
                    break;
                case "clearData":
                    clearData(locator);
                    break;
                case "monitorText":
                    KeyWord.monitorText(locator,value);
                    break;
                case "monitorId":
                    KeyWord.monitorId(locator,value);
                    break;
                case "removeMonitor":
                    KeyWord.removeMonitor(locator);
                    break;
                case "wakeScreen":
                    KeyWord.wakeScreen();
                    break;
                case "screenShot":
                    KeyWord.screenShot(locator);
                    break;
                case "newFolder":
                    KeyWord.newFolder(locator);
                    break;
                case "installApk":
                    KeyWord.installApk(locator);
                    break;
                case "clickText":
                    KeyWord.clickText(locator);
                    break;
                case "clickId":
                    KeyWord.clickId(locator);
                    break;
                case "assertText":
                    KeyWord.assertText(locator);
                    break;
                case "assertId":
                    KeyWord.assertId(locator);
                    break;
                case "inputId":
                    KeyWord.inputId(locator,value);
                    break;
                case "inputText":
                    KeyWord.inputText(locator,value);
                    break;
                case "swipeLeft":
                    KeyWord.swipeLeft(locator);
                    break;
                case "swipeRight":
                    KeyWord.swipeRight(locator);
                    break;
                case "swipeDown":
                    KeyWord.swipeDown(locator);
                    break;
                case "swipeUp":
                    KeyWord.swipeUp(locator);
                    break;
                case "wait":
                    KeyWord.wait(locator);
                    break;
                case "back":
                    KeyWord.back();
                    break;
                case "home":
                    KeyWord.home();
                    break;
                case "recentApps":
                    KeyWord.recentApps();
                    break;
                case "cleraId":
                    KeyWord.cleraId(locator);
                    break;
                case "cleraText":
                    KeyWord.cleraText(locator);
                    break;
                case "slideFindText":
                    KeyWord.slideFindText(locator);
                    break;
                case "slideFindId":
                    KeyWord.slideFindId(locator);
                    break;
                case "clickTextLong":
                    KeyWord.clickTextLong(locator);
                    break;
                case "clickIdLong":
                    KeyWord.clickIdLong(locator);
                    break;
                case "openNotification":
                    KeyWord.openNotification();
                    break;
                case "openUrl":
                    KeyWord.openUrl(locator);
                    break;
                case "unInstallApk":
                    KeyWord.unInstallApk(locator);
                    break;
                case "selectedId":
                    selectedId(locator);
                    break;
                case "assertTextNull":
                    KeyWord.assertTextNull(locator);
                    break;
                case "assertIdNull":
                    KeyWord.assertIdNull(locator);
                    break;
                case "selectedIdTrue":
                    KeyWord.selectedIdTrue(locator);
                    break;
                case "selectedIdFalse":
                    KeyWord.selectedIdFalse(locator);
                    break;
                default:
                    Log.e(TAG, "没有匹配任何关键字");
                    break;
            }

            //生成测试报告
            if (judge.equals("fail")) {
                SerialNumber++;
                GenerateReport.testresult_table += "<tr bgcolor=\"#DEB887\">"
                        + "<td align=\"left\">" + Case_name1 + "</td>"
                        + "<td align=\"center\">" + SerialNumber + "</td>"
                        + "<td align=\"center\">" + "<div style=\"width:150px;height:25px;overflow-x:hidden;overflow-y:hidden;\">" + keyword + "</td>"
                        + "<td align=\"left\">" + locator + "</td>"
                        + "<td align=\"center\">" + value + "</td>"
                        + "<td align=\"center\"  bgcolor=\"#F08080\">" + judge + "</td>" + "</tr>";
            } else {
                SerialNumber++;
                GenerateReport.testresult_table += "<tr bgcolor=\"#DEB887\">"
                        + "<td align=\"left\">" + Case_name1 + "</td>"
                        + "<td align=\"center\">" + SerialNumber + "</td>"
                        + "<td align=\"center\">" + "<div style=\"width:150px;height:25px;overflow-x:hidden;overflow-y:hidden;\">" + keyword + "</td>"
                        + "<td align=\"left\">" + locator + "</td>"
                        + "<td align=\"center\">" + value + "</td>"
                        + "<td align=\"center\"  bgcolor=\"#6B8E23\">" + judge + "</td>" + "</tr>";
            }

        }
        fr.close();
        br.close();
    }


    // 文件内容的总行数。
    private static int getTotalLines() throws IOException {
        String fileName = Path_case;
        BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileName)));
        LineNumberReader reader = new LineNumberReader(in);
        String s = reader.readLine();
        int lines = 0;
        while (s != null) {
            lines++;
            s = reader.readLine();
        }
        reader.close();
        in.close();
        return lines;
    }

    //查找指定字符串的数量
    private static int getCount(String str, String tag) {
        int index = 0;
        int count = 0;
        while ((index = str.indexOf(tag)) != -1) {
            str = str.substring(index + tag.length());
            count++;
        }
        return count;
    }


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
    public void after() throws IOException {
        // 写入html文件
        GenerateReport.writeHtml();
        Log.e(TAG,"测试结束");
    }

}