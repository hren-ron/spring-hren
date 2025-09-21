package com.hren.service;

import com.hren.spring.BeanPostProcessor;
import com.hren.spring.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class HrenBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if(beanName.equals("userService")) {
            System.out.println("初始化之前执行");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
//        if (beanName.equals("userService")) {
//            Object proxyInstance = Proxy.newProxyInstance(HrenBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
//                @Override
//                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                    System.out.println("切面逻辑");
//                    return method.invoke(bean, args);
//                }
//            });
//            return proxyInstance;
//        }

        return bean;
    }
}
