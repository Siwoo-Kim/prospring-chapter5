package com.prospring.aop.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-13 오전 11:42
 **/

@Getter @Setter
public class Guitarist {
    private String name;
    private static final String lyric = "You're gonna live forever in me";

    public void sing() {
        System.out.println(getName() + " sings: " + lyric);
    }

}
