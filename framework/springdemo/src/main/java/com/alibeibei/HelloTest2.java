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
public class HelloTest2 {
    private Hello2 hello2;

    public Hello2 getHello2() {
        return hello2;
    }

    public void setHello2(Hello2 hello2) {
        this.hello2 = hello2;
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        HelloTest2 helloTest2 = applicationContext.getBean(HelloTest2.class);
        System.out.println(helloTest2);
    }
}
