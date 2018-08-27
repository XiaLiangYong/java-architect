package com.alibeibei;

/**
 * @version V3.0
 * @Title: Hello
 * @Company:
 * @Description: 描述
 * @author: alibeibei
 * @date 2018/8/27 上午10:33
 */
public class Hello4 {
    private Integer id;
    private String name;

    public Hello4() {
    }

    public Hello4(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hello3{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static Hello4 build(String type) {
        if (type.equals("A")) {
            return new Hello4(1, "夏天");
        } else if (type.equals("B")) {
            return new Hello4(2, "秋天");
        } else {
            throw new IllegalArgumentException("type must A or B");
        }
    }
}
