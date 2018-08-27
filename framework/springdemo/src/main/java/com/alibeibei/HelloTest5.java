package com.alibeibei;


import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 指定一个Bean工厂来创建对象，对象构建初始化 完全交给该工厂来实现。配置Bean时指定该工厂类的类名。
 *
 * @version V3.0
 * @Title: HelloTest
 * @Company:
 * @Description: 描述
 * @author: alibeibei
 * @date 2018/8/27 上午10:34
 */
public class HelloTest5 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Hello4 hello4 = applicationContext.getBean(Hello4.class);
        System.out.println(hello4);
    }


}
