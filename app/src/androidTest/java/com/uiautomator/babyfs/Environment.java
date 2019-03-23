package com.uiautomator.babyfs;

import android.util.Log;

import java.io.File;

import static com.uiautomator.babyfs.Constants.Path_picture;
import static com.uiautomator.babyfs.Constants.TAG;
import static org.junit.Assert.fail;


public class Environment {
    //检查测试环境
    public static void test() {
        File Path_AUA2 = new File(Constants.Path_AUA2); //根目录
        if (!Path_AUA2.exists()) {
            Log.e(TAG, Path_AUA2 + " 不存在");
            fail("根目录文件夹不存在");
        } else {
            File Path_properties = new File(Constants.Path_properties); //配置文件
            if (!Path_properties.exists()) {
                Log.e(TAG, "配置文件不存在");
                fail("配置文件不存在");
            }
            File Path_case = new File(Constants.Path_case); //case文件
            if (!Path_case.exists()) {
                Log.e(TAG, "case文件不存在");
                fail("case文件不存在");
            }
            File PathPicture = new File(Path_picture); //图片路径
            if (!PathPicture.exists()) {
                PathPicture.mkdir();//创建文件夹
            }
        }
    }

}