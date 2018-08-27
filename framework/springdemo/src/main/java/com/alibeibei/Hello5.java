package com.alibeibei;

import org.springframework.beans.factory.FactoryBean;

/**
 * @version V3.0
 * @Title: Hello
 * @Company:
 * @Description: 描述
 * @author: alibeibei
 * @date 2018/8/27 上午10:33
 */
public class Hello5 implements FactoryBean {
    public Object getObject() throws Exception {
        return new Hello4(3, "冬天");
    }

    public Class<?> getObjectType() {
        return Hello4.class;
    }

    public boolean isSingleton() {
        return false;
    }
}
