package com.hren.spring;

public interface InitializingBean {

    /**
     * 初始化 方法
     *
     */
    void afterPropertiesSet();
}
