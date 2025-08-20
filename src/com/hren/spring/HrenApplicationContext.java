package com.hren.spring;

public class HrenApplicationContext {

    private Class configClass;

    public HrenApplicationContext(Class configClass) {
        this.configClass = configClass;
    }

    // 获取bean
    public Object getBean(String beanName) {
        return null;
    }
}
