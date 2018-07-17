package com.prospring.aop.advice;

import com.prospring.aop.common.WorkBean;
import com.prospring.aop.util.ProxyFactoryUtils;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-17 오전 10:45
 **/


public class ProfilingInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(invocation.getMethod().getName());
        Object returnValue = invocation.proceed();
        stopWatch.stop();

        dumpInfo(invocation, stopWatch.getTotalTimeMillis());
        return returnValue;
    }

    private void dumpInfo(MethodInvocation invocation, long totalTimeMillis) {
        Method method = invocation.getMethod();
        Object[] args = invocation.getArguments();
        Object target = invocation.getThis();

        System.out.println("Executed method: " + method.getName());
        System.out.println("On object of type: " + target.getClass().getName());
        System.out.println("With arguments:");
        for(int i=0; i<args.length; i++) {
            System.out.print("        > " + args[i]);
        }
        System.out.println();
        System.out.println("Took: " + totalTimeMillis + " ms");
    }

    @Test
    public void demo1() {
        WorkBean proxy = ProxyFactoryUtils.getProxy(new WorkBean(), new ProfilingInterceptor());
        proxy.doWork(1000000);
    }
}
