package com.prospring.aop.advice;


import com.prospring.aop.common.Documentarist;
import org.aspectj.lang.JoinPoint;
import org.junit.Test;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-23 오전 11:38
 **/


public class AuditAdvice {

    public void before(JoinPoint joinpoint) {
        System.out.println("Executing: "
                + joinpoint.getSignature().getDeclaringTypeName() + " "
                + joinpoint.getSignature().getName());
    }


    @Test
    public void proxyFactory() {
        GenericXmlApplicationContext context =
                new GenericXmlApplicationContext("classpath:/spring/aop-context.xml");

        Documentarist documentaristOne = context.getBean("documentarist", Documentarist.class);
        documentaristOne.execute();
    }
}
