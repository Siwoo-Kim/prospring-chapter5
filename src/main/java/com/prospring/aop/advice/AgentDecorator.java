package com.prospring.aop.advice;

import com.prospring.aop.common.Agent;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.junit.Assert.assertEquals;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-13 오전 11:14
 **/

public class AgentDecorator implements MethodInterceptor{

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        String result = (String) methodInvocation.proceed();
        return "James " + result + "!";
    }

    @Test
    public void demo1() {
        Agent target = new Agent();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new AgentDecorator());
        proxyFactory.setTarget(target);
        Agent proxy = (Agent) proxyFactory.getProxy();
        assertEquals(target.speak(), "Bond");
        assertEquals(proxy.speak(), "James Bond!");
    }
}
