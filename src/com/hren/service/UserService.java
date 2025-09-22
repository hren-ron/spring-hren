package com.hren.service;


import org.springframework.stereotype.Component;

@Component
public class UserService {

    private OrderService orderService;

//    // 管理员，从数据库中查到的管理员（User对象），
//    // 程序员自己实现的逻辑，spring不知道
//    // 在初始化之前执行对应的方法来初始化，例如a(), 加注解 PostConstruct
//    private User admin;

    /***
     * 构造方法注入: 如果只有一个构造方法，spring使用这一个。如果没有，使用默认无参构造方法。如果有多个，则报错
     *
     * 从Map<BeanName, bean>中寻找对应的bean， 如果没有则创建OrderService对象
     * 先按type查找， 再按名字查找
     * @param orderService
     */
    public UserService(OrderService orderService) {
        this.orderService = orderService;
    }


//    @PostConstruct
//    public void postConstruct() {
//        System.out.println("执行PostConstruct");
////        this.admin = new User();
//    }
//    /**
//     * 记录bean名字，
//     * 需要Aware回调机制，从spring中获取bean名字
//     */
//    private String beanName;


    public void test()
    {
        System.out.println(orderService);
    }

//    @Override
//    public void setBeanName(String beanName) {
//        this.beanName = beanName;
//    }

//    @Override
//    public void afterPropertiesSet() {
//        System.out.println("afterPropertiesSet");
//    }
}
