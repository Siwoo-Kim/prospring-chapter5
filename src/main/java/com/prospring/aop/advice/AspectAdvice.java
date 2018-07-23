package com.prospring.aop.advice;

import com.prospring.aop.common.ADocumentairst;
import com.prospring.aop.common.GrammySinger;
import com.prospring.aop.common.MyDependentBean;
import com.prospring.aop.common.MyTesterTarget;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.security.spec.ECField;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-23 오후 1:24
 **/

@Component
@Aspect
public class AspectAdvice {


    @Pointcut("execution(* com..*MyTesterTarget.getSome*(String)) && args(value)")
    public void myTesterPointcut(String value) { }

    @Pointcut("bean(myTester)")
    public void isMyTester() { }

    @Around("myTesterPointcut(value) && isMyTester()")
    public Object aroundTester(ProceedingJoinPoint joinPoint, String value) throws Throwable {
        if("not".equals(value)) {
            throw new IllegalArgumentException();
        }

        StringBuilder stringBuilder = new StringBuilder();
        Signature signature = joinPoint.getSignature();
        stringBuilder.append(signature.getDeclaringTypeName()+ ", " + signature.getName() + " with args: " + value);
        System.out.println("Before :" +stringBuilder);
        Object result = joinPoint.proceed();
        System.out.println("After :" +stringBuilder);
        return result;
    }



    @Pointcut("execution(* com..*sing*(com.prospring.aop.common.GrammySinger.Guitar)) && args(value)")
    public void singPointcut(GrammySinger.Guitar value){ }

    @Pointcut("bean(siwoo*)")
    public void isSiwoo() {

    }

    @Before("singPointcut(value) && isSiwoo()")
    public void before(JoinPoint joinPoint, GrammySinger.Guitar value) {
        if("Gibson".equals(value.getBrand())) {
            StringBuilder stringBuilder = new StringBuilder();
            Signature signature = joinPoint.getSignature();
            stringBuilder.append(signature.getDeclaringTypeName()+ ", " + signature.getName() + " with args: " + value.getBrand());
            System.out.println("Execution: " + stringBuilder);
        }
    }

    @Around("singPointcut(value) && isSiwoo()")
    public Object around(ProceedingJoinPoint joinPoint, GrammySinger.Guitar value) throws Throwable {
        StringBuilder stringBuilder = new StringBuilder();
        Signature signature = joinPoint.getSignature();
        stringBuilder.append(signature.getDeclaringTypeName()+ ", " + signature.getName() + " with args: " + value.getBrand());

        System.out.println("Before Execution: " + stringBuilder);
        Object result = joinPoint.proceed();
        System.out.println("After Exectuion: " + stringBuilder);
        return result;
    }

    @Configuration
    @EnableAspectJAutoProxy(proxyTargetClass = true)
    @ComponentScan(basePackages = "com.prospring")
    public static class SpringConfiguration {

    }

    @Test(expected = IllegalArgumentException.class)
    public void test() {
        ApplicationContext c = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        ADocumentairst aDocumentairst = c.getBean(ADocumentairst.class);
        aDocumentairst.execute();

        MyDependentBean myDependentBean = c.getBean(MyDependentBean.class);
        myDependentBean.test();
    }
}
