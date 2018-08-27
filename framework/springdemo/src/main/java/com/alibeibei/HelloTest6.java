package com.alibeibei;


import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.util.Arrays;

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
public class HelloTest6 {

    public static void main(String[] args) {
        //创建一个简单注册器
        BeanDefinitionRegistry register = new SimpleBeanDefinitionRegistry();
        //创建bean定义读取器
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(register);
        // 创建资源读取器
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        // 获取资源
        Resource xmlResource = resourceLoader.getResource("spring.xml");
        // 装载Bean的定义
        reader.loadBeanDefinitions(xmlResource);
        // 打印构建的Bean 名称
        System.out.println(Arrays.toString(register.getBeanDefinitionNames()));
    }


}
