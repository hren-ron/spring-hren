package com.hren.spring;

public interface BeanPostProcessor {

    /**
     * 初始化前
     *
     * @param bean
     * @param beanName
     * @return
     */
     void postProcessBeforeInitialization(Object bean, String beanName);

    /**
     * 初始化后
     *
     * @param bean
     * @param beanName
     * @return
     */
    void postProcessAfterInitialization(Object bean, String beanName);
}
