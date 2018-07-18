package com.prospring.aop.pointcut;

import com.prospring.aop.common.SampleBean;
import com.prospring.aop.util.ProxyFactoryUtils;
import org.junit.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-18 오전 11:12
 **/


public class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut {
    private static final String TARGET_METHOD = "foo";
    private static final int NUMBER_LIMIT = 100;

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        System.out.println("Dynamic check for " + method.getName());
        int x = ((Integer) args[0]).intValue();
        return (x < NUMBER_LIMIT);
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        System.out.println("Static check for " + method.getName());
        return TARGET_METHOD.equals(method.getName());
    }

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> clazz == SampleBean.class;
    }

    @Test
    public void demo() {
        SampleBean proxy = ProxyFactoryUtils
                .getProxy(new SampleBean(), new DefaultPointcutAdvisor(new SimpleDynamicPointcut(), new SimpleStaticPointcut.SimpleAroundAdvice()));

        proxy.bar();
        proxy.foo(120);
        proxy.foo(30);
    }
}
