package com.prospring.aop.advice;

import com.prospring.aop.common.Guitarist;
import org.junit.Test;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

import static org.junit.Assert.fail;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-13 오전 11:41
 **/


public class SimpleBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        if("sing".equals(method.getName())) {
            execute(method, (Guitarist) o);
        }
    }

    private void execute(Method method, Guitarist target) {
        System.out.println(method.getName()+ " is invoked as advice");
        if(!StringUtils.hasText(target.getName())) {
            throw new IllegalArgumentException("Not set name before singing");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void demo() {
        Guitarist target = new Guitarist();
        target.setName("Siwoo Kim");
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new SimpleBeforeAdvice());
        proxyFactory.setTarget(target);
        Guitarist proxy = (Guitarist) proxyFactory.getProxy();
        proxy.sing();

        target.setName("");
        proxyFactory.setTarget(target);
        proxy = (Guitarist) proxyFactory.getProxy();
        proxy.sing();
        fail();
    }
}
