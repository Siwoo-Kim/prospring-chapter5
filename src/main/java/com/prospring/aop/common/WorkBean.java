package com.prospring.aop.common;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-17 오전 10:44
 **/


public class WorkBean {

    public void doWork(int numOfTimes) {
        for(int i=0; i<numOfTimes; i++) {
            work();
        }
    }

    private void work() {
        System.out.print("*");
    }
}
