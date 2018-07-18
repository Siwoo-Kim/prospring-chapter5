package com.prospring.aop.common;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-18 오전 10:44
 **/


public class GreatSinger implements Singer {

    @Override
    public void sing() {
        System.out.println("I shot the sheriff");
    }
}
