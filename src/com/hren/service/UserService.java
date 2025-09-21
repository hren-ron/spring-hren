package com.hren.service;

import com.hren.spring.*;

@Component
public class UserService implements BeanNameAware, InitializingBean, UserInterface {

    @Autowired
    private OrderService orderService;

    // 管理员，从数据库中查到的管理员（User对象），
    // 程序员自己实现的逻辑，spring不知道
    // 在初始化之前执行对应的方法来初始化，例如a(), 加注解 PostConstruct
    private User admin;


    @PostConstruct
    public void postConstruct() {
        System.out.println("执行PostConstruct");
        this.admin = new User();
    }
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
