package com.prospring.aop.common;

import java.util.Random;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-17 오전 10:30
 **/


public class KeyGenerator {
    public static final long WEAK_KEY = 0xFFFFFFF0000000L;
    public static final long STRONG_KEY = 0xACDF03F590AE56L;

    private static final Random random = new Random();

    public long getKey() {
        int oneInThree = random.nextInt(3);
        if(oneInThree == 1) {
            return WEAK_KEY;
        }
        return STRONG_KEY;
    }
}
