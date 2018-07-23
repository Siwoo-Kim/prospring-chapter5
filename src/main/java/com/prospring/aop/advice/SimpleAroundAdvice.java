package com.prospring.aop.advice;

import com.prospring.aop.common.GrammySinger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-23 오후 12:46
 **/


public class SimpleAroundAdvice {

    public Object around(ProceedingJoinPoint joinPoint, GrammySinger.Guitar value) throws Throwable {
        Signature signature = joinPoint.getSignature();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(signature.getDeclaringTypeName() + ", " + signature.getName() + ", argument : " + value.getBrand());
        System.out.println("Before Executing " + stringBuilder);
        Object result = joinPoint.proceed();
        System.out.println("After Executing " + signature);
        return result;
    }

}
