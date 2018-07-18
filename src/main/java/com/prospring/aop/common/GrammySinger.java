package com.prospring.aop.common;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-18 오전 11:20
 **/


public class GrammySinger implements Singer{


    @Override
    public void sing() {
        System.out.println("sing: Gravity is working against me");
    }

    public void sing(Guitar guitar) {
        System.out.println("play: " + guitar.play());
    }

    public void rest() {
        System.out.println("zzz");
    }

    public void talk() {
        System.out.println("blur");
    }

    public static class Guitar {
        public String play() {
            return "G C G C Am D7";
        }
    }
}
