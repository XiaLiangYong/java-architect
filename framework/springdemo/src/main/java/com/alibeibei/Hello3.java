package com.alibeibei;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 构造方法构建
 *
 * @version V3.0
 * @Title: Hello
 * @Company:
 * @Description: 描述
 * @author: alibeibei
 * @date 2018/8/27 上午10:33
 */
public class Hello3 implements InitializingBean, DisposableBean {
    private Integer id;
    private String name;

    public Hello3() {
    }

    public Hello3(Integer id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("222");

    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("1111");
    }

    public void destroy() throws Exception {
        System.out.println("22222");
    }
}
