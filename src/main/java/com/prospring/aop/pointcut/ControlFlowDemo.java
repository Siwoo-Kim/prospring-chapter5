package com.prospring.aop.pointcut;

import com.prospring.aop.advice.AnotherSimpleBeforeAdvice;
import com.prospring.aop.common.TestBean;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.util.function.Consumer;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-19 오전 11:31
 **/


public class ControlFlowDemo {

    public static void main(String[] args) {
        ControlFlowDemo ex = new ControlFlowDemo();
        ex.run();
    }

    private void run() {
        TestBean testBean = new TestBean();
        Pointcut pointcut = new ControlFlowPointcut(ControlFlowDemo.class, "test");
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new AnotherSimpleBeforeAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(testBean);
        proxyFactory.addAdvisor(advisor);

        TestBean proxy = (TestBean) proxyFactory.getProxy();
        System.out.println("Trying normal invoke");
        proxy.foo();
        System.out.println("Trying under ControlFlowDemo.test()");
        test(_proxy -> _proxy.foo(), proxy);
        test(_proxy -> {
            System.out.println(proxy + proxy.getClass().getName());
            System.out.println(proxy);
        }, proxy);
    }

    private <T> void test(Consumer<T> T, T o) {
        T.accept(o);
    }

}
