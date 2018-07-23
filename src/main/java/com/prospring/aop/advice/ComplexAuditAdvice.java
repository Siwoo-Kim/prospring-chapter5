package com.prospring.aop.advice;

import com.prospring.aop.common.Documentarist;
import com.prospring.aop.common.GrammySinger;
import com.prospring.aop.common.NewDocumentarist;
import org.aspectj.lang.JoinPoint;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-23 오후 12:33
 **/


public class ComplexAuditAdvice {

    public void before(JoinPoint joinPoint, GrammySinger.Guitar value) {
        if ("Gibson".equals(value.getBrand())) {
            System.out.println("Executing: "
                    + joinPoint.getSignature().getDeclaringTypeName() + " "
                    + joinPoint.getSignature().getName());
        }
    }

    @Test
    public void proxyFactory() {
        GenericXmlApplicationContext context =
                new GenericXmlApplicationContext("classpath:/spring/aop-context.xml");

        NewDocumentarist documentarist = context.getBean("newDocumentarist", NewDocumentarist.class);
        documentarist.execute();
    }
}
