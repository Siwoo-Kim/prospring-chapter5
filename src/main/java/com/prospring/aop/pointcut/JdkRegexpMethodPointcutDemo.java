package com.prospring.aop.pointcut;

import com.prospring.aop.common.RegularSinger;
import com.prospring.aop.pointcut.SimpleStaticPointcut;
import com.prospring.aop.util.ProxyFactoryUtils;
import org.junit.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-18 오전 11:50
 **/


public class JdkRegexpMethodPointcutDemo {

    @Test
    public void demo() {
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPattern(".*sing.*");
        RegularSinger singer = ProxyFactoryUtils.getProxy(new RegularSinger(), new DefaultPointcutAdvisor(pointcut, new SimpleStaticPointcut.SimpleAroundAdvice()));
        singer.sing();
        singer.rest();
        singer.sing2();
    }

}
