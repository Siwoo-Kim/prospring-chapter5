package com.prospring.aop.advice;

import com.prospring.aop.common.KeyGenerator;
import com.prospring.aop.util.ProxyFactoryUtils;
import org.junit.Test;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

import static com.prospring.aop.common.KeyGenerator.WEAK_KEY;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-17 오전 10:33
 **/


public class WeakKeyCheckAdvice implements AfterReturningAdvice {
    private static final String TARGET_METHOD = "getKey";

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        if(checkTarget(target, method)) {
            long longValue = (long) returnValue;

            if(longValue == WEAK_KEY) {
                throw new SecurityException("Key generator genterated a weak key, Try again");
            }
        }
    }

    private boolean checkTarget(Object target, Method method) {
        return target instanceof KeyGenerator && TARGET_METHOD.equals(method.getName());
    }

    @Test
    public void demo() {
        KeyGenerator proxy = ProxyFactoryUtils.getProxy(new KeyGenerator(), new WeakKeyCheckAdvice());
        for(int i=0; i<10; i++) {
            try {
                long key = proxy.getKey();
                System.out.println(key);
            }catch (Exception e) {
                System.out.println("Weak key generated");
            }
        }
    }
}
