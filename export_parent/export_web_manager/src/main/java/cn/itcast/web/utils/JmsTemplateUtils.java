package cn.itcast.web.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class JmsTemplateUtils {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Destination queue;

    public void send(String email,String password,String msg){
        jmsTemplate.send(queue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                // 给中间件传递保存的数据
                MapMessage map = session.createMapMessage();
                map.setStringProperty("email",email);
                map.setStringProperty("password",password);
                map.setStringProperty("msg",msg);
                return map;
            }
        });
    }
}
