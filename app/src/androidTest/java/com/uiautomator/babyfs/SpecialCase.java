package com.uiautomator.babyfs;

import android.support.test.uiautomator.UiObjectNotFoundException;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.uiautomator.babyfs.Constants.TAG;
import static java.lang.Thread.sleep;

public class SpecialCase {


    //时间戳
    private static String timeStamp(){
        Date time = new Date();
        SimpleDateFormat format = new SimpleDateFormat( "yy年MM月dd日  HH:mm:ss" );//定义时间格式
        return format.format(time);
    }

    public static void jiqiren() throws UiObjectNotFoundException, InterruptedException {

        String str1 = "条：当你回首往事的时候，不会因碌碌无为而羞愧，也不会因为虚度年华而悔恨，他能够说我的毕生心血和全部精力都献给了世界上最伟大的事业，为解放全人类而奋斗。  时间：";

        for (int i = 2455;i<=50000;i++){
            KeyWord.clickText("萌翻天111");//选择群组
            if (i%2 == 0){//偶数
                KeyWord.inputId("com.tencent.mm:id/aif","第"+i+str1+timeStamp());//发送文字
                KeyWord.clickId("com.tencent.mm:id/ail");//点击发送
                KeyWord.clickId("com.tencent.mm:id/j6");//返回
                sleep(1000);
            }else {//奇数
                KeyWord.clickId("com.tencent.mm:id/aik");//点击更多功能按钮
                KeyWord.clickId("com.tencent.mm:id/th");//点击相册
                KeyWord.clickId("com.tencent.mm:id/bhm");//点击选中框
                KeyWord.clickId("com.tencent.mm:id/j1");//点击发送
                KeyWord.clickId("com.tencent.mm:id/j6");//返回
                sleep(1000);
            }

            KeyWord.clickText("萌翻天222");//选择群组
            if (i%2 == 0){//偶数
                KeyWord.inputId("com.tencent.mm:id/aif","第"+i+str1+timeStamp());//发送文字
                KeyWord.clickId("com.tencent.mm:id/ail");//点击发送
                KeyWord.clickId("com.tencent.mm:id/j6");//返回
                sleep(1000);
            }else {//奇数
                KeyWord.clickId("com.tencent.mm:id/aik");//点击更多功能按钮
                KeyWord.clickId("com.tencent.mm:id/th");//点击相册
                KeyWord.clickId("com.tencent.mm:id/bhm");//点击选中框
                KeyWord.clickId("com.tencent.mm:id/j1");//点击发送
                KeyWord.clickId("com.tencent.mm:id/j6");//返回
                sleep(1000);
            }

            KeyWord.clickText("萌翻天333");
            if (i%2 == 0){//偶数
                KeyWord.inputId("com.tencent.mm:id/aif","第"+i+str1+timeStamp());//发送文字
                KeyWord.clickId("com.tencent.mm:id/ail");//点击发送
                KeyWord.clickId("com.tencent.mm:id/j6");//返回
                sleep(1000);
            }else {//奇数
                KeyWord.clickId("com.tencent.mm:id/aik");//点击更多功能按钮
                KeyWord.clickId("com.tencent.mm:id/th");//点击相册
                KeyWord.clickId("com.tencent.mm:id/bhm");//点击选中框
                KeyWord.clickId("com.tencent.mm:id/j1");//点击发送
                KeyWord.clickId("com.tencent.mm:id/j6");//返回
                sleep(1000);
            }

            KeyWord.clickText("萌翻天444");
            if (KeyWord.judgment_id("com.tencent.mm:id/ani")){
                KeyWord.inputId("com.tencent.mm:id/aif","第"+i+"条消息发出"+"时间："+timeStamp());//发送文字
                KeyWord.clickId("com.tencent.mm:id/ail");//点击发送
                Log.e(TAG, i+"   条时被禁言");
                return;
            }
            if (i%2 == 0){//偶数
                KeyWord.inputId("com.tencent.mm:id/aif","第"+i+str1+timeStamp());//发送文字
                KeyWord.clickId("com.tencent.mm:id/ail");//点击发送
                KeyWord.clickId("com.tencent.mm:id/j6");//返回
                sleep(1000);
            }else {//奇数
                KeyWord.clickId("com.tencent.mm:id/aik");//点击更多功能按钮
                KeyWord.clickId("com.tencent.mm:id/th");//点击相册
                KeyWord.clickId("com.tencent.mm:id/bhm");//点击选中框
                KeyWord.clickId("com.tencent.mm:id/j1");//点击发送
                KeyWord.clickId("com.tencent.mm:id/j6");//返回
                sleep(1000);
            }
        }
    }

}
