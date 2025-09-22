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
 *         target.test();
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
