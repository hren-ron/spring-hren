package com.hren.service;

import com.hren.spring.HrenApplicationContext;

public class Test {
    public static void main(String[] args) {
        HrenApplicationContext hrenApplicationContext = new HrenApplicationContext(AppConfig.class);

        System.out.println(hrenApplicationContext.getBean("userService"));
        System.out.println(hrenApplicationContext.getBean("userService"));
        System.out.println(hrenApplicationContext.getBean("userService"));
        System.out.println(hrenApplicationContext.getBean("userService"));
    }
}
