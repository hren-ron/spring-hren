package com.hren.service;

import com.hren.spring.BeanPostProcessor;
import com.hren.spring.Component;

@Component
public class HrenBeanPostProcessor implements BeanPostProcessor {
    @Override
    public void postProcessBeforeInitialization(Object bean, String beanName) {
        if(beanName.equals("userService")) {
            System.out.println("初始化之前执行");
        }
    }

    @Override
    public void postProcessAfterInitialization(Object bean, String beanName) {
        if (beanName.equals("userService")) {
            System.out.println("初始化之后执行");
        }
    }
}
