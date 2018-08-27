package com.alibeibei;


import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @version V3.0
 * @Title: HelloTest
 * @Company:
 * @Description: 描述
 * @author: alibeibei
 * @date 2018/8/27 上午10:34
 */
public class HelloTest3 {
    private Hello3 hello3;

    public Hello3 getHello3() {
        return hello3;
    }

    public void setHello3(Hello3 hello3) {
        this.hello3 = hello3;
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Hello3 helloTest3 = applicationContext.getBean(Hello3.class);
        System.out.println(helloTest3);
        Hello3 helloTest4 = applicationContext.getBean(Hello3.class);
        System.out.println(helloTest4);

    }
}
