package com.prospring.aop.common;

import com.prospring.aop.pointcut.AdviceRequired;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-18 오전 11:49
 **/


public class RegularSinger implements Singer {

    @Override
    public void sing() {
        System.out.println("Just keep me where the light is");
    }

    @AdviceRequired
    public void sing(GrammySinger.Guitar guitar) {
        System.out.println("Play: " + guitar.play());
    }

    public void sing2() {
        System.out.println("Just keep me where the light is");
    }

    public void rest() {
        System.out.println("zzz");
    }
}
