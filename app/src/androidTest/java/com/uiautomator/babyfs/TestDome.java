package com.uiautomator.babyfs;

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

public class TestDome {


    //   https://www.cnblogs.com/zhujiabin/p/6295467.html
    public static void testMail(String title, String txt, String path, String to) throws MessagingException{

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");// 服务器需要认证
        properties.setProperty("mail.transport.protocol", "smtp");// 声明发送邮件使用的端口
        properties.setProperty("mail.host", "smtp.qiye.163.com");// 发送邮件的服务器地址
        //可省略
        properties.setProperty("mail.smtp.port", "25");
        properties.setProperty("mail.smtp.timeout", "25000");//读超时时间
        properties.setProperty("mail.smtp.connectiontimeout", "25000");//连接超时时间

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mengfanting@babyfs.cn",
                        "mft666666.");
            }
        });

        MimeMessage msg = new MimeMessage(session);// 声明一个邮件体
        msg.setFrom(new InternetAddress("mengfanting@babyfs.cn"));//发件人
        msg.setSubject(title);//设置邮件主题
        msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(to));//收件人列表
        msg.addRecipients(Message.RecipientType.CC, "");//抄送人

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
        MimeBodyPart content = new MimeBodyPart();// 邮件的正文，混合体（图片+文字）
        content.setContent(txt + "<img src=http://mimg.126.net/logo/126logo.gif>", "text/html;charset=UTF-8");//指定正文，网络图片

        // 标明邮件的组合关系，混合的关系
        MimeMultipart msgMultipart = new MimeMultipart("mixed");
        // 将附件和正文设置到这个邮件体中
        msgMultipart.addBodyPart(content);
        msgMultipart.addBodyPart(attch1);
//        msgMultipart.addBodyPart(attch2);
//        msgMultipart.addBodyPart(attch3);


        msg.setContent(msgMultipart);// 设置邮件体

        msg.saveChanges();//保存邮件
        Transport.send(msg);//发送邮件


    }
}
