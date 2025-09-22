package com.hren.service;

import com.hren.spring.HrenApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext hrenApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = (UserService) hrenApplicationContext.getBean("userService");
        userService.test();
    }
}

