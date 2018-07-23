package com.prospring.aop.common;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-23 오전 11:37
 **/


public class Documentarist {
    protected GrammyGuitarist guitarist;

    public void setGuitarist(GrammyGuitarist guitarist) {
        this.guitarist = guitarist;
    }

    public void execute() {
        guitarist.sing();
        guitarist.sing(new GrammySinger.Guitar());
        guitarist.talk();
    }
}
