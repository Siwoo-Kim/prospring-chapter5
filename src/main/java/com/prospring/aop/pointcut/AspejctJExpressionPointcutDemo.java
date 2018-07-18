package com.prospring.aop.pointcut;

import com.prospring.aop.common.RegularSinger;
import com.prospring.aop.pointcut.SimpleStaticPointcut;
import com.prospring.aop.util.ProxyFactoryUtils;
import org.junit.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-18 오후 12:01
 **/


public class AspejctJExpressionPointcutDemo {

    @Test
    public void demo() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* *..*sing*(..))");

        RegularSinger singer = ProxyFactoryUtils.getProxy(new RegularSinger(), new DefaultPointcutAdvisor(pointcut, new SimpleStaticPointcut.SimpleAroundAdvice()));
        singer.rest();
        singer.sing2();
        singer.sing();
    }
}
