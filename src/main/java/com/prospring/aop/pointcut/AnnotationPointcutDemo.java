package com.prospring.aop.pointcut;

import com.prospring.aop.common.GrammySinger;
import com.prospring.aop.common.RegularSinger;
import com.prospring.aop.util.ProxyFactoryUtils;
import org.junit.Test;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-18 오후 12:08
 **/


public class AnnotationPointcutDemo {

    @Test
    public void demo() {
        AnnotationMatchingPointcut annotationMatchingPointcut = AnnotationMatchingPointcut
                .forMethodAnnotation(AdviceRequired.class);
        RegularSinger singer = ProxyFactoryUtils
                .getProxy(new RegularSinger(), new DefaultPointcutAdvisor(annotationMatchingPointcut, new SimpleStaticPointcut.SimpleAroundAdvice()));

        singer.sing();
        singer.sing2();
        singer.rest();
        singer.sing(new GrammySinger.Guitar());
    }

}
