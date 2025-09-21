package com.hren.service;

import com.hren.spring.*;

@Component
public class UserService implements BeanNameAware, InitializingBean {

    @Autowired
    private OrderService orderService;

    /**
     * 记录bean名字，
     * 需要Aware回调机制，从spring中获取bean名字
     */
    private String beanName;


    public void test()
    {
        System.out.println("UserService test");
        System.out.println(orderService);
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("beanName:" + beanName);
    }
}
