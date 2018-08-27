package com.alibeibei;


import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @version V3.0
 * @Title: HelloTest
 * @Company:
 * @Description: 描述
 * @author: alibeibei
 * @date 2018/8/27 上午10:34
 */
public class HelloTest {
    private Hello hello;

    public HelloTest(Hello hello) {
        System.out.println("注入前");
        this.hello = hello;
        System.out.println("注入后");
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        applicationContext.getBean(HelloTest.class);
    }
}
