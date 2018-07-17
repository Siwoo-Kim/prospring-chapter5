package com.prospring.aop.advice;

import com.prospring.aop.common.SecureBean;
import com.prospring.aop.common.SecurityManager;
import com.prospring.aop.common.UserInfo;
import com.prospring.aop.util.ProxyFactoryUtils;
import org.junit.Test;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-13 오후 12:00
 **/


public class SecurityAdvice implements MethodBeforeAdvice {
    private SecurityManager securityManager;

    public SecurityAdvice() {
        this.securityManager = new SecurityManager();
    }

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        UserInfo userInfo = securityManager.getLoggedOnUser();

        if(ObjectUtils.isEmpty(userInfo)) {
            System.out.println("No Your authenticated");
            throw new SecurityException("You must login before attemping to invoke the method: " + method.getName());
        }else if ("Siwoo".equals(userInfo.getUserName())) {
            //ignore
        }else {
            System.out.println("Not know you: " + userInfo.getUserName());
            throw new SecurityException("You must login before attemping to invoke the method: " + method.getName());
        }
    }

    @Test
    public void demo() {
        SecurityManager securityManager = new SecurityManager();
        SecureBean secureBean = ProxyFactoryUtils.getProxy(new SecureBean(), new SecurityAdvice());
        securityManager.login("Siwoo", "1234");
        String result = secureBean.fetchPassword();
        assertEquals(result, "Password is 1234");
        securityManager.logout();

        securityManager.login("nono","1234");
        secureBean.fetchPassword();
        fail();
    }
}
