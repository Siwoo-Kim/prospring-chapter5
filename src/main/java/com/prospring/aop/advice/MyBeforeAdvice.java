package com.prospring.aop.advice;

import com.prospring.aop.common.MyDependentBean;
import org.aspectj.lang.JoinPoint;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-23 오후 12:08
 **/


public class MyBeforeAdvice {

    public void before(JoinPoint joinPoint) {
        System.out.println("Before executing...");
        if("not".equals(joinPoint.getArgs()[0])) {
            throw new IllegalArgumentException();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test() {
        GenericXmlApplicationContext c =
                new GenericXmlApplicationContext("classpath:/spring/aop-context.xml");

        MyDependentBean bean = c.getBean(MyDependentBean.class);
        bean.test();
    }

}
