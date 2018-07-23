package com.prospring.aop.pointcut;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-19 오전 10:42
 **/


public class TestPointcut extends StaticMethodMatcherPointcut {
    private static final String TARGET_METHOD = "advised";

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return TARGET_METHOD.equals(method.getName());
    }
}
