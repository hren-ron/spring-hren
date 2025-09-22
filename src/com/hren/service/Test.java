package com.hren.service;

import com.hren.spring.HrenApplicationContext;

public class Test {
    public static void main(String[] args) {
        HrenApplicationContext hrenApplicationContext = new HrenApplicationContext(AppConfig.class);

        UserService userService = (UserService) hrenApplicationContext.getBean("userService");
        userService.test();
    }
}

