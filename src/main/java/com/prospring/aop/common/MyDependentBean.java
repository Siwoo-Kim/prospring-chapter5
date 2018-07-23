package com.prospring.aop.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-23 오후 12:10
 **/

@Component
public class MyDependentBean {
    @Autowired @Qualifier("myTester")
    MyTesterTarget target;

    public void setTarget(MyTesterTarget target) {
        this.target = target;
    }

    public void test() {
        target.getSomeDummy();
        target.getSomeString("siwoo!");
        target.getSomeString("not");
    }

}
