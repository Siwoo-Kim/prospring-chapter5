package com.prospring.aop.advice;

import com.prospring.aop.common.ErrorBean;
import com.prospring.aop.util.ProxyFactoryUtils;
import org.junit.Test;
import org.springframework.aop.ThrowsAdvice;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-17 오후 2:54
 **/


public class SimpleThrowsAdvice implements ThrowsAdvice {

    public void afterThrowing(Exception e) {
        System.out.println("Capture generic exception");
        System.out.println("Caught: " + e.getClass().getName());
    }

    public void afterThrowing(IllegalArgumentException e) {
        System.out.println("Capture IllegalArgumentException");
        System.out.println("Caught: " + e.getClass().getName());
    }

    @Test
    public void demo1() {
        ErrorBean proxy = ProxyFactoryUtils.getProxy(new ErrorBean(), new SimpleThrowsAdvice());

        try {
            proxy.errorProneMethod();
        }catch (Exception e) {
            //Ignore
        }

        try {
            proxy.otherErrorProneMethod();
        }catch (Exception e) {
            //Ignore
        }

    }
}
