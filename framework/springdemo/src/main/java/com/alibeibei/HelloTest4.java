package com.alibeibei;


import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 静态工厂方法创建
 * @version V3.0
 * @Title: HelloTest
 * @Company:
 * @Description: 描述
 * @author: alibeibei
 * @date 2018/8/27 上午10:34
 */
public class HelloTest4 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Hello4 hello4 = applicationContext.getBean(Hello4.class);
        System.out.println(hello4);
    }

}
