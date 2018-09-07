package com.colin.tool;

import com.colin.tool.mail.NiceEmail;

import javax.mail.MessagingException;
import java.io.File;

public class MailUtils {

    //采购部地址
    public static String pureUsername = "purchase_dp@fionacos.com";
    //采购部账户名
    public static String pureFromName = "";
//    public static String pureFromName = "妍丽-采购部";
    //采购部账户密码
    public static String purePassword = "dp123456";

    //财务部地址
    public static String accUsername = "acc_ap1@fionacos.com";
    //财务部账户名
    public static String accFromName = "";
//    public static String accFromName = "妍丽-财务部";
    //财务部账户密码
    public static String accPassword = "gth6ry@123";

    /**
     * @param title     邮件标题
     * @param toEmails  邮件地址,群发以 ,或;分割
     * @param content   邮件内容html格式
     * @return           成功:true, 失败:false
     */
    public static boolean SendHtml(String department,String toEmails,String title,String content){
        Boolean b;
        if(department != null && "purchase".equals(department)){
            NiceEmail.config(NiceEmail.SMTP_FIONACOS(), pureUsername, purePassword);
            try {
                NiceEmail.subject(title)
                        .from(pureFromName)
                        .to(toEmails)
                        //html内容即可
                        .html(content)
                        .send();
                b = true;
            } catch (MessagingException e) {
                b = false;
                e.printStackTrace();
            }
        }else {
            NiceEmail.config(NiceEmail.SMTP_FIONACOS(), accUsername, accPassword);
            try {
                NiceEmail.subject(title)
                        .from(accFromName)
                        .to(toEmails)
                        //html内容即可
                        .html(content)
                        .send();
                b = true;
            } catch (MessagingException e) {
                b = false;
                e.printStackTrace();
            }
        }
        return b;
    }


    /**
     * @param title     邮件标题
     * @param toEmails  邮件地址,群发以 ,或;分割
     * @param content   邮件内容html格式
     * @param filePath  文件路径
     * @param fileName  文件名称
     */
    public static boolean testSendAttach(String department,String toEmails,String title,String content,String filePath,String fileName){
        boolean b;
        if(department != null && "purchase".equals(department)){
            try {
                NiceEmail.config(NiceEmail.SMTP_FIONACOS(), pureUsername, purePassword);
                NiceEmail.subject(title)
                        .from(pureFromName)
                        .to(toEmails)
                        .html(content)
                        //附件的路径，以及名称
                        .attach(new File(filePath), fileName)
                        .send();
                b = true;
            } catch (MessagingException e) {
                e.printStackTrace();
                b = false;
            }
        }else{
            try {
                NiceEmail.config(NiceEmail.SMTP_FIONACOS(), accUsername, accPassword);
                NiceEmail.subject(title)
                        .from(accFromName)
                        .to(toEmails)
                        .html(content)
                        //附件的路径，以及名称
                        .attach(new File(filePath), fileName)
                        .send();
                b = true;
            } catch (MessagingException e) {
                e.printStackTrace();
                b = false;
            }
        }
        return b;
    }



    /**
     * @param title     邮件标题
     * @param toEmails  邮件地址,群发以 ,或;分割
     * @param content   邮件内容html格式
     * @return           成功:true, 失败:false
     */
    public static boolean SendEmailCopyTo(String department,String toEmails,String copyTo,String title,String content){
        Boolean b;
        if(department != null && "purchase".equals(department)){
            NiceEmail.config(NiceEmail.SMTP_FIONACOS(), pureUsername, purePassword);
            try {
                NiceEmail.subject(title)
                        .from(pureFromName)
                        .to(toEmails)
                        .copyTo(copyTo)
                        //html内容即可
                        .html(content)
                        .send();
                b = true;
            } catch (MessagingException e) {
                b = false;
                e.printStackTrace();
            }
        }else {
            NiceEmail.config(NiceEmail.SMTP_FIONACOS(), accUsername, accPassword);
            try {
                NiceEmail.subject(title)
                        .from(accFromName)
                        .to(toEmails)
                        //html内容即可
                        .html(content)
                        .send();
                b = true;
            } catch (MessagingException e) {
                b = false;
                e.printStackTrace();
            }
        }
        return b;
    }


}
