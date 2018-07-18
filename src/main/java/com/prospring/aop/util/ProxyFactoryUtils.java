package com.prospring.aop.util;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-13 오후 12:04
 **/


public class ProxyFactoryUtils {

    public static <T,S extends Advice> T getProxy(T target, S advice) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(advice);
        return (T) proxyFactory.getProxy();
    }

    public static <T,S extends Advisor> T getProxy(T target, S advisor) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        return (T) proxyFactory.getProxy();
    }
}
