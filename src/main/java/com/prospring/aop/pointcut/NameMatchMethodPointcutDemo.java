package com.prospring.aop.pointcut;

import com.prospring.aop.common.GrammySinger;
import com.prospring.aop.pointcut.SimpleStaticPointcut;
import com.prospring.aop.util.ProxyFactoryUtils;
import org.junit.Test;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-18 오전 11:22
 **/


public class NameMatchMethodPointcutDemo {

    @Test
    public void demo() {
        NameMatchMethodPointcut nameMatchMethodPoincut = new NameMatchMethodPointcut();
        nameMatchMethodPoincut.addMethodName("sing");
        nameMatchMethodPoincut.addMethodName("rest");

        GrammySinger grammySinger = ProxyFactoryUtils
                .getProxy(new GrammySinger(), new DefaultPointcutAdvisor(nameMatchMethodPoincut, new SimpleStaticPointcut.SimpleAroundAdvice()));
        grammySinger.rest();
        grammySinger.sing();
        grammySinger.sing(new GrammySinger.Guitar());
        grammySinger.talk();
    }
}
