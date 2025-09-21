package com.hren.service;

import com.hren.spring.Autowired;
import com.hren.spring.Component;
import com.hren.spring.Scope;

@Component
public class UserService {

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
}
