package com.prospring.aop.advice;

import com.prospring.aop.common.Guitarist;
import com.prospring.aop.util.ProxyFactoryUtils;
import org.junit.Test;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-17 오전 10:15
 **/


public class SimpleAfterReturningAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("After '" + method.getName() + "' put down guitar.");
    }

    @Test
    public void demo1() {
        Guitarist target = new Guitarist();
        Guitarist proxy = ProxyFactoryUtils.getProxy(target, new SimpleAfterReturningAdvice());
        proxy.sing();
    }
}
