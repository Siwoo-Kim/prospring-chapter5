package com.prospring.aop.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-23 오후 1:22
 **/

@Component("documentarist")
public class ADocumentairst {
    protected AGrammyGuitarist singer;

    public void execute() {
        singer.sing();
        GrammySinger.Guitar guitar = new GrammySinger.Guitar();
        guitar.setBrand("Gibson");
        singer.sing(guitar);
        singer.talk();
    }

    @Autowired
    @Qualifier("siwoo")
    public void setSinger(AGrammyGuitarist singer) {
        this.singer = singer;
    }
}
