package com.prospring.aop.common;

import org.junit.Test;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-23 오후 12:07
 **/

@Component("myTester")
public class MyTesterTarget {

    public void getSomeDummy() {
        System.out.println("Ignore");
    }

    public void getSomeString(String argument) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello ");
        stringBuilder.append(argument);
        DecimalFormat decimalFormat = new DecimalFormat("##,###");
        stringBuilder.append(decimalFormat.format(2020.2));
        System.out.println(stringBuilder.toString());
    }


}
