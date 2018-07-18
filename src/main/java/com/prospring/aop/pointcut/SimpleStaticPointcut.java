package com.prospring.aop.pointcut;

import com.prospring.aop.common.GoodSinger;
import com.prospring.aop.common.GreatSinger;
import com.prospring.aop.common.Singer;
import com.prospring.aop.util.ProxyFactoryUtils;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-18 오전 10:45
 **/


public class SimpleStaticPointcut extends StaticMethodMatcherPointcut {
    private static final String TAGET_METHOD = "sing";

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return TAGET_METHOD.equals(method.getName());
    }

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> GoodSinger.class == clazz;
    }

    public static class SimpleAroundAdvice implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            System.out.println("Before joinpoint for " + invocation.getMethod().getName());
            Object result = invocation.proceed();
            System.out.println("After joinpoint ");
            return result;
        }

    }

    @Test
    public void demo() {
        Singer singer = ProxyFactoryUtils
                .getProxy(new GreatSinger(), new DefaultPointcutAdvisor(new SimpleStaticPointcut(), new SimpleAroundAdvice()));
        singer.sing();

        singer = ProxyFactoryUtils
                .getProxy(new GoodSinger(), new DefaultPointcutAdvisor(new SimpleStaticPointcut(), new SimpleAroundAdvice()));
        singer.sing();
    }
}
