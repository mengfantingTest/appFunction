package com.uiautomator.babyfs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static com.uiautomator.babyfs.Constants.Path_html;

public class GenerateReport {
    public static String testresult_table="<html>"
            +"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />" //使用 build fat jar打包后，出现中文乱码，用此行代码解决，指定html编码格式
            +"<body>"
            +"<h1 align=\"center\" style=\"color:#8B4513\" >自动化测试报告</h1>"
            +"<table align=\"center\" border=\"1\">"
            +"<tr bgcolor=\"#CD853F\" > <th>测试用例集</th>"
            +"<th>序号</th>"
            +"<th>关键字</th>"
            +"<th>页面元素定位</th>"
            +"<th>操作值</th>"
            +"<th>结果</th>"
            +"</tr>";


    //写入html
    static void writeHtml() throws IOException {
        // 写入html文件
        testresult_table += "</table> </body></html>";
        File file3 = new File(Path_html);// html文件路径
        OutputStreamWriter os3;
        os3 = new OutputStreamWriter(new FileOutputStream(file3));
        os3.write(testresult_table);
        os3.close();
    }






    //用于打印内部用例测试结果

     public static String insideCaseSultTable="<html>"
            +"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />" //使用 build fat jar打包后，出现中文乱码，用此行代码解决，指定html编码格式
            +"<body>"
            +"<h1 align=\"center\" style=\"color:#8B4513\" >自动化测试报告</h1>"
            +"<table align=\"center\" border=\"1\">"
            +"<tr bgcolor=\"#CD853F\" > <th>测试用例集</th>"
            +"<th>步骤</th>"
            +"<th>关键字</th>"
            +"<th>页面元素定位</th>"
            +"<th>操作值</th>"
            +"<th>结果</th>"
            +"</tr>";

    //结果fail
    static void contentFail(String action,String keyword, String locator, String value){
        insideCaseSultTable += "<tr bgcolor=\"#DEB887\">"
                + "<td align=\"left\">" + "" + "</td>"
                + "<td align=\"center\">" + action + "</td>"
                + "<td align=\"center\">" + "<div style=\"width:150px;height:25px;overflow-x:hidden;overflow-y:hidden;\">" + keyword + "</td>"
                + "<td align=\"left\">" + locator + "</td>"
                + "<td align=\"center\">" + value + "</td>"
                + "<td align=\"center\"  bgcolor=\"#F08080\">" + "fail" + "</td>" + "</tr>";
    }

    //结果pass
    static void contentPass(String action,String keyword, String locator, String value){
        insideCaseSultTable += "<tr bgcolor=\"#DEB887\">"
                + "<td align=\"left\">" + "" + "</td>"
                + "<td align=\"center\">" + action + "</td>"
                + "<td align=\"center\">" + "<div style=\"width:150px;height:25px;overflow-x:hidden;overflow-y:hidden;\">" + keyword + "</td>"
                + "<td align=\"left\">" + locator + "</td>"
                + "<td align=\"center\">" + value + "</td>"
                + "<td align=\"center\"  bgcolor=\"#6B8E23\">" + "pass" + "</td>" + "</tr>";
    }

    //case名字
    static void caseName(String name){
        insideCaseSultTable += "<tr bgcolor=\"#CDAA7D\">"
                + "<td align=\"left\">" + name + "</td>"
                + "<td align=\"center\">" + "" + "</td>"
                + "<td align=\"center\">" + "<div style=\"width:150px;height:25px;overflow-x:hidden;overflow-y:hidden;\">" + "" + "</td>"
                + "<td align=\"left\">" + "" + "</td>"
                + "<td align=\"center\">" + "" + "</td>"
                + "<td align=\"center\"  bgcolor=\"#CDAA7D\">" + "" + "</td>" + "</tr>";
    }

    //写入html
    static void insideCaseWriteHtml() throws IOException {
        // 写入html文件
        insideCaseSultTable += "</table> </body></html>";
        File file3 = new File(Path_html);// html文件路径
        OutputStreamWriter os3;
        os3 = new OutputStreamWriter(new FileOutputStream(file3));
        os3.write(insideCaseSultTable);
        os3.close();
    }
}
