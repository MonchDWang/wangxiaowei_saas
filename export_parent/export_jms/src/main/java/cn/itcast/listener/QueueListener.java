package cn.itcast.listener;

import cn.itcast.utils.MailUtils;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class QueueListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        MapMessage map=(MapMessage)message;
        try {
            String email = map.getStringProperty("email");
            String password = map.getStringProperty("password");
            String msg = map.getStringProperty("msg");
            System.out.println(email);
            System.out.println(password);
            System.out.println(msg);
            // 调用工具发邮件
            MailUtils.sendMail(email,msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
