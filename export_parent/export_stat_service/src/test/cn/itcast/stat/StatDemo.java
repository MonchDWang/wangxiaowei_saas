package cn.itcast.stat;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StatDemo {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-*.xml");
        context.start();
        System.in.read(); // 按任意键退出
    }
}
