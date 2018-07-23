package com.prospring.aop.executor;

import com.prospring.aop.advice.NoOpBeforeAdvice;
import com.prospring.aop.common.DefaultSimpleBean;
import com.prospring.aop.common.SimpleBean;
import com.prospring.aop.pointcut.TestPointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.TargetClassAware;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;
import java.util.function.Consumer;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-19 오전 10:44
 **/


public class ProxyPerformanceDemo {

    public static void main(String[] args) {
        SimpleBean target = new DefaultSimpleBean();
        Advisor advisor = new DefaultPointcutAdvisor(new TestPointcut(), new NoOpBeforeAdvice());

        runCglibTests(advisor, target);
        rungCglibFrozenTests(advisor, target);
        runJdkTests(advisor, target);
    }

    private static void runJdkTests(Advisor advisor, SimpleBean target) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(SimpleBean.class); //CGLIB
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        System.out.println("Running JDK Tests ");
        SimpleBean proxy = (SimpleBean) proxyFactory.getProxy();
        test(proxy);
    }

    private static void rungCglibFrozenTests(Advisor advisor, SimpleBean target) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setProxyTargetClass(true); //CGLIB
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setFrozen(true);
        System.out.println("Running CGLIB (FRONZEN) Tests ");
        SimpleBean proxy = (SimpleBean) proxyFactory.getProxy();
        test(proxy);
    }

    private static void runCglibTests(Advisor advisor, SimpleBean target) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setProxyTargetClass(true); //CGLIB
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setTarget(target);
        SimpleBean proxy = (SimpleBean) proxyFactory.getProxy();
        System.out.println("Running CGLIB (Standard) Tests");
        test(proxy);
    }

    private static void test(SimpleBean proxy) {
        try {
            tempate(simpleBean -> simpleBean.advised(), proxy, SimpleBean.class.getMethod("advised"));
            tempate(SimpleBean::unadvised, proxy, SimpleBean.class.getMethod("unadvised"));
            Advised advised = (Advised) proxy;
            tempate(TargetClassAware::getTargetClass, advised, TargetClassAware.class.getMethod("getTargetClass"));
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }

    private static<T> void tempate(Consumer<T> consumer, T simpleBean, Method method) {
        long before = 0;
        long after = 0;
        System.out.println("Testing " + method.getName() + " Method");
        before = System.currentTimeMillis();
        for(int i=0; i<50000; i++) {
            consumer.accept(simpleBean);
        }
        after = System.currentTimeMillis();
        System.out.println("Took " + (after - before) + " ms");
    }


}
