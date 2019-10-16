package cn.itcast.utils;

import javax.mail.MessagingException;

public class Demo {

    public static void main(String[] args) throws MessagingException {
        //固定发送者：luqingcast@126.com
        //收件人：13021101663@163.com

        String email="13021101663@163.com";
        String msg="今晚小树林见..";
        MailUtils.sendMail(email,msg);
    }
}
