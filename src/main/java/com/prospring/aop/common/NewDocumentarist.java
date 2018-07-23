package com.prospring.aop.common;


/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-23 오후 12:21
 **/

public class NewDocumentarist extends Documentarist {

    @Override
    public void execute() {
        guitarist.sing();
        GrammySinger.Guitar guitar = new GrammySinger.Guitar();
        guitar.setBrand("Gibson");
        guitarist.sing(guitar);
        guitarist.talk();
    }
}
