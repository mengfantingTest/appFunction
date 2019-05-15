package com.uiautomator.babyfs;


import android.content.Intent;
import android.net.Uri;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import static com.uiautomator.babyfs.KeyWord.context;

public class Email {


    //纯文本邮件
    public static void sendText() throws Exception {
        String to = "mft1027@163.com";

        String from = "mengfanting@babyfs.cn";

        // 获取系统属性
        Properties properties = new Properties();

        properties.setProperty("mail.transport.protocol", "SMTP");
        properties.setProperty("mail.smtp.host", "smtp.qiye.163.com");
        properties.setProperty("mail.smtp.port", "25");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.timeout", "3000");

        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("mengfanting@babyfs.cn", "mft666666.");
                    }
                });

        // 创建默认的 MimeMessage 对象
        MimeMessage message = new MimeMessage(session);

        // Set From: 头部头字段
        message.setFrom(new InternetAddress(from));

        // Set To: 头部头字段
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        // Set Subject: 头部头字段
        message.setSubject("邮件标题");

        // 设置消息体
        message.setText("邮件内容");

        Transport.send(message);
    }


    public static void sendFile(String title, String txt, String path, String to) throws MessagingException {

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.host", "smtp.qiye.163.com");
        //可省略
        properties.setProperty("mail.smtp.port", "25");
        properties.setProperty("mail.smtp.timeout", "25000");
        properties.setProperty("mail.smtp.connectiontimeout", "25000");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mengfanting@babyfs.cn",
                        "mft666666.");
            }
        });

        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("mengfanting@babyfs.cn"));
        msg.setSubject(title);
        msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(to));
        msg.addRecipients(Message.RecipientType.CC, "");

        // 设置第一个附件
        MimeBodyPart attch1 = new MimeBodyPart();// 附件1
        DataHandler dh1 = new DataHandler(new FileDataSource(path));// 附件的信息
        attch1.setDataHandler(dh1);// 指定附件
        attch1.setFileName(dh1.getName());//附件名称

        // 设置第二个附件
        MimeBodyPart attch2 = new MimeBodyPart();// 附件2
        DataHandler dh2 = new DataHandler(new FileDataSource(""));// 附件的信息
        attch2.setDataHandler(dh2);// 指定附件
        attch2.setFileName(dh2.getName());//附件名称

        // 设置第三个附件
        MimeBodyPart attch3 = new MimeBodyPart();// 附件3
        DataHandler dh3 = new DataHandler(new FileDataSource(""));// 附件的信息
        attch3.setDataHandler(dh3);// 指定附件
        attch3.setFileName(dh3.getName());//附件名称

        //设置邮件的正文
        MimeBodyPart content = new MimeBodyPart();
        content.setContent(txt, "text/plain;charset=UTF-8");

        // 标明邮件的组合关系，混合的关系
        MimeMultipart msgMultipart = new MimeMultipart("mixed");
        msgMultipart.addBodyPart(content);
        msgMultipart.addBodyPart(attch1);
//        msgMultipart.addBodyPart(attch2);
//        msgMultipart.addBodyPart(attch3);


        msg.setContent(msgMultipart);// 设置邮件体

        msg.saveChanges();
        Transport.send(msg);


    }






    //带附件的邮件，备用
    public static void sendFile1(String title,String text,String path) throws Exception {

        File file = new File(path);

        String [] to = {"mft1027@163.com"};

        String cc = "mengfanting@babyfs.cn";//和发件人一致


        // 获取系统属性
        Properties properties = new Properties();

        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.host", "smtp.qiye.163.com");
        properties.setProperty("mail.smtp.port", "25");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.timeout", "25000");
        properties.put("mail.debug", true);

        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                "mengfanting@babyfs.cn", "mft666666.");
                    }
                });

        MimeBodyPart content = new MimeBodyPart();
        content.setContent(text, "text/html;charset=UTF-8");

        MimeBodyPart attach = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource(file));
        attach.setDataHandler(dh);
        attach.setFileName(dh.getName());

        // 创建默认的 MimeMessage 对象
        MimeMessage message = new MimeMessage(session);


        message.setFrom(new InternetAddress("mengfanting@babyfs.cn"));//发件人
        message.addRecipients(Message.RecipientType.CC,cc);


        for (String toNumber : to) {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toNumber));
        }

        //创建容器描述数据关系
        MimeMultipart mp = new MimeMultipart();// 声明一个邮件体
        message.setSubject(title);
        message.setContent(mp);
        mp.addBodyPart(content);
        mp.addBodyPart(attach);
        mp.setSubType("mixed");

        message.saveChanges();

        // 不使用 Transport.send(message);
        Transport transport = session.getTransport();
        transport.connect();
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }


    //发送纯文本
    static void sendTo (){
        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:mft1027@163.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "这是标题");
        intent.putExtra(Intent.EXTRA_TEXT, "这是内容");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    //发送一个附件
    static void mail() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        String[] tos = { "mft1027@163.com" };
        String[] ccs = { "mft1027@163.com" };
        String[] bccs = {"mft1027@163.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, tos);
        intent.putExtra(Intent.EXTRA_CC, ccs);
        intent.putExtra(Intent.EXTRA_BCC, bccs);
        intent.putExtra(Intent.EXTRA_TEXT, "内容");
        intent.putExtra(Intent.EXTRA_SUBJECT, "主题");

        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/测试结果.html"));
        intent.setType("text/html");
        intent.setType("message/rfc882");
        Intent.createChooser(intent, "Choose Email Client");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    //发送多个附件
    static void mailMore(){
        Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        String[] tos = { "mft1027@163.com" };
        String[] ccs = { "mft1027@163.com" };
        String[] bccs = {"mft1027@163.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, tos);
        intent.putExtra(Intent.EXTRA_CC, ccs);
        intent.putExtra(Intent.EXTRA_BCC, bccs);
        intent.putExtra(Intent.EXTRA_TEXT, "内容");
        intent.putExtra(Intent.EXTRA_SUBJECT, "主题");

        ArrayList<Uri> imageUris = new ArrayList<Uri>();
        imageUris.add(Uri.parse("file:///sdcard/测试结果.html"));
        imageUris.add(Uri.parse("file:///sdcard/测试结果.html"));
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        intent.setType("text/html");
        intent.setType("message/rfc882");
        Intent.createChooser(intent, "Choose Email Client");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
