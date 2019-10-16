package cn.itcast.startdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartDemo {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-*.xml");
        context.start();
        System.in.read(); // 按任意键退出
    }
}
