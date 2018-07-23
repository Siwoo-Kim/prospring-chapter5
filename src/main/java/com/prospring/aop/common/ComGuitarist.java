package com.prospring.aop.common;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-19 오전 11:47
 **/


public class ComGuitarist implements Singer {

    @Override
    public void sing() {
        System.out.println("sing: Gravity is working against me");
    }

    public void sing(GrammySinger.Guitar guitar){
        System.out.println("play: " + guitar.play());
    }

    public void rest() {
        System.out.println("zzz");
    }

    public void talk() {
        System.out.println("talk");
    }
}
