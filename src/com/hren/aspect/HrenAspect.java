package com.hren.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


/**
 * userServiceProxy --> userService代理对象 ---> userservice代理对象.target = 普通对象
 * userService代理对象.test()
 *
 * class UserServiceProxy extends UserService {
 *
 *     Userservice target;
 *
 *     public void test() {
 *         // 执行切面逻辑@Before
 *
 *
 *
 *         // 检查是否游tracnsactional
 *
 *         //开启事务
 *         // 1. 事务管理器新建数据库连接
 *         // 2. conn.commit = false
 *         target.test();
 *
 *         //  conn.commit
 *     }
 * }
 */

@Aspect
@Component
public class HrenAspect {

    @Before("execution(public void com.hren.service.UserService.test())")
    public void hrenBefore()
    {
        System.out.println("HrenAspect.before()");
    }
}
