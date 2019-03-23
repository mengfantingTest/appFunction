package com.uiautomator.babyfs;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.test.uiautomator.UiObjectNotFoundException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ProUtil {
    private Properties prop;
    private String filePath;

    //构造方法
    ProUtil(String string) {
        this.filePath = string;
        this.prop = readProperties();
    }

    //读取key值
    private Properties readProperties() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(filePath);
            BufferedInputStream in = new BufferedInputStream(inputStream);
            properties.load(in);
        }catch(IOException e) {
            e.printStackTrace();
        }
        return properties;
    }


    //获取配置文件
    public String getPro(String key) {
        if(prop.containsKey(key)) {
            String name = prop.getProperty(key);
            return name;
        }else {
            System.out.println("没有获取到"+key+"值");
            return "";
        }

    }

    //手机自动打开html文件
    public static void openAssignFolder(String string) throws UiObjectNotFoundException {
        File file = new File(string);
        if(!file.exists()){
            return;
        }
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(uri,"text/html");
        try {
            KeyWord.context.startActivity(intent);
            if (KeyWord.judgment_text("HTML 查看器")){
                KeyWord.clickText("HTML 查看器");
                KeyWord.clickText("HTML 查看器");
            }
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }


    //读取txt文件，返回字符串
    public static String load(String filePath) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));//构造一个BufferedReader类来读取文件
            String s;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result.append(System.lineSeparator()).append(s);//换行+内容
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(result);
    }


}
