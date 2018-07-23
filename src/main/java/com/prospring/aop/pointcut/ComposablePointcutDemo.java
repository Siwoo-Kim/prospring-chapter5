package com.prospring.aop.pointcut;

import com.prospring.aop.advice.AnotherSimpleBeforeAdvice;
import com.prospring.aop.common.ComGuitarist;
import com.prospring.aop.common.GrammySinger;
import com.prospring.aop.util.ProxyFactoryUtils;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;

import java.lang.reflect.Method;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-19 오전 11:48
 **/


public class ComposablePointcutDemo {

    public static class SingMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return (method.getName().startsWith("si"));
        }
    }

    public static class TalkMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return "talk".equals(method.getName());
        }
    }

    public static class RestMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return method.getName().endsWith("st");
        }
    }

    public static void main(String[] args) {
        ComposablePointcut composablePointcut = new ComposablePointcut(ClassFilter.TRUE, new SingMethodMatcher());
        ComGuitarist proxy = ProxyFactoryUtils.getProxy(new ComGuitarist(), new DefaultPointcutAdvisor(composablePointcut, new AnotherSimpleBeforeAdvice()));
        test(proxy);

        composablePointcut.union(new TalkMethodMatcher());
        proxy = ProxyFactoryUtils.getProxy(new ComGuitarist(), new DefaultPointcutAdvisor(composablePointcut, new AnotherSimpleBeforeAdvice()));
        test(proxy);

        composablePointcut.intersection(new RestMethodMatcher());
        proxy = ProxyFactoryUtils.getProxy(new ComGuitarist(), new DefaultPointcutAdvisor(composablePointcut, new AnotherSimpleBeforeAdvice()));
        test(proxy);
    }

    private static void test(ComGuitarist proxy) {
        proxy.rest();
        proxy.sing();
        proxy.sing(new GrammySinger.Guitar());
        proxy.talk();
    }

}
